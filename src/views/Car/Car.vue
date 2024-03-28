<template>
    <div class="content-class" v-if="showContent===contentList[0]">
        <div class="content-search-class">
            <el-input
                v-model="searchText"
                placeholder="Please enter your search"
                clearable
                :prefix-icon="Search"
                @clear="getTableData"
            />
            <el-space style="margin-left: 20px;">
                <el-select v-model="brandId" placeholder="select">
                    <el-option v-for="(item, index) in brandOptions" :key="index" :label="item.brandName" :value="item.brandId" />
                </el-select> 
            </el-space>
            <el-button type="primary" @click="getTableData">search</el-button>
            <el-button type="primary" @click="showContent = contentList[1]">add</el-button>
        </div>
        <div class="content-table-class">
            <el-table
                ref="multipleTableRef"
                :data="tableData"
                style="width: 100%;"
                :header-cell-style="{'text-align':'center'}"
                :cell-style="{'text-align':'center'}"
                height="100%"
                highlight-current-row 
            >
                <el-table-column fixed type="index" label="S N" width="55" />
                <!-- show-overflow-tooltip -->
                <el-table-column prop="carName" label="name" min-width="110" />
                <el-table-column prop="level" label="level" min-width="110" />
                <el-table-column prop="environmentProtection" label="Environmental protection standard" min-width="110" />
                <el-table-column prop="maxPower" label="Maximum power（kW）" min-width="110" />
                <el-table-column prop="maxTorque" label="Maximum torque（N·m）" min-width="110" />
                <el-table-column prop="engine" label="engine" min-width="110" />
                <el-table-column prop="gearbox" label="gearbox" min-width="110" />
                <el-table-column prop="size" label="Length x width x height（mm）" min-width="110" />
                <el-table-column prop="bodyStructure" label="Body structure" min-width="110" />
                <el-table-column prop="maxSpeed" label="Maximum speed（km/h）" min-width="110" />
                <el-table-column prop="wltc" label="Combined fuel consumption（L/100km）" min-width="110" />
                <el-table-column prop="warranty" label="Vehicle warranty" min-width="110" />
                <el-table-column label="image"  min-width="110">
                    <template #default="scope">
                        <el-image
                            style="width: 100px; height: 100px"
                            :src="`${imgHttpPre}${scope.row.image}`"
                            fit="contain"></el-image>    
                    </template>
                </el-table-column>
                <el-table-column prop="price" label="yuan" min-width="110" />
                <el-table-column fixed="right" label="Operation" width="200">
                    <template #default="scope">
                        <operate-button @click="toEditPage(scope.$index, scope.row)" :btnName="'Modify'"></operate-button>
                        <operate-button @click="toDelete(scope.$index, scope.row)" :btnName="'Delete'"></operate-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-pagination
            v-model:current-page="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="[10, 20, 30, 40]"
            background
            small
            layout="total, sizes, prev, pager, next, jumper"
            :total="dataTotal"
            @size-change="getTableData"
            @current-change="getTableData"
            />
        <el-dialog v-model="dialogVisible" title="tips" width="400px" draggable>
            <span>Confirm to delete the car？</span>
            <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">cancel</el-button>
                <el-button type="primary" @click="deleteData">confirm</el-button>
            </span>
            </template>
        </el-dialog>
    </div>
    <div class="content-class" v-else-if="showContent === contentList[1]">
        <add-view @back="toTablePage" :brands="brandOptions"></add-view>
    </div>
    <div class="content-class" v-else-if="showContent === contentList[2]">
        <edit-view @back="toTablePage" :row="curRow" :brands="brandOptions"></edit-view>
    </div>
</template>
<script setup lang="ts">
import OperateButton from '@/components/OperateButton.vue'; 
import AddView from './components/AddView.vue';
import EditView from './components/EditView.vue';
// import TestView from './components/TestView.vue';
import { getCurrentInstance, onMounted, ref } from 'vue' 
import { ElMessage } from 'element-plus'
import type{ ElTable} from 'element-plus'
import { Search } from '@element-plus/icons-vue'  
import { useCarsStore } from '@/stores/cars'
import type { Data } from '@/stores/cars'
import { useBrandsStore } from '@/stores/brands'
import type { Data as BrandData } from '@/stores/brands'
const { appContext } = getCurrentInstance() as any;
const carsStore = useCarsStore();
const brandsStore = useBrandsStore();
const imgHttpPre = appContext.config.globalProperties.$imgHttpPre 

const searchText = ref('')
const brandId = ref()
const currentPage = ref(1)
const pageSize = ref(10)
const dataTotal = ref(0)
let tableData = ref<Data[]>([])
const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const contentList = ref<string[]>(['table', 'add', 'edit'])
let showContent = ref<string>(contentList.value[0])
const curRow = ref<Data>()
const dialogVisible = ref(false)

onMounted(() => {
    getBrandList()
    getTableData()
})
const brandOptions = ref<Array<BrandData>>([])
const getBrandList = async()=>{
    const reqData = {
        searchText: '',
        page: 0,
        rows: 0,
        type: 1
    }
    const res = await brandsStore.getTableDataAction(reqData)
    if(res){
        brandOptions.value = res.data.dataList
    } else {
        brandOptions.value = []
    }
}
const getTableData = async() => {
    const reqData = {
        searchText: searchText.value,
        page: currentPage.value,
        rows: pageSize.value,
        brandId: brandId.value,
        priceFilter: 0,
        typeFilter: '0'
    }
    const res = await carsStore.getTableDataAction(reqData)
    if(res){
        dataTotal.value = res.data.count
        tableData.value = res.data.dataList
    } else {
        dataTotal.value = 0
        tableData.value = []
    }
} 
const toTablePage = (refresh = false) => {
    showContent.value = contentList.value[0]
    if(refresh) getTableData()
}
const toEditPage = (index: number, row: Data) => {
    curRow.value = row
    showContent.value = contentList.value[2]
}
const toDelete = (index: number, row: Data) => {
    curRow.value = row
    dialogVisible.value = true
} 
const deleteData = async() => {
    const res = await carsStore.deleteDataAction({carId: curRow.value?.carId as number})
    if(res){
        ElMessage.success(res.message); 
        dialogVisible.value = false
        getTableData()
    }
}
</script>
<style lang="scss" scoped>
.content-class{
    .content-search-class{
        display: flex;
        margin-bottom: 10px;
        .el-input{
            width: 30%;
        }
        .el-button{
            margin-left: 10px;
            margin-right: 10px;
        }
    } 
    
    .el-pagination{
        float: right;
    }
    
}
</style>
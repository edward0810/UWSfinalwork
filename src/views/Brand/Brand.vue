<template>
    <div class="content-class" v-if="showContent===contentList[0]">
        <div class="content-search-class">
            <el-input
                v-model="searchText"
                placeholder="Please enter search content"
                clearable
                :prefix-icon="Search"
                @clear="getTableData"
            />
            <el-space style="margin-left: 20px;">
                <el-select v-model="carType">
                    <el-option label="Car" :value="0" />
                    <el-option label="Motorcycle" :value="1" />
                    <el-option label="Van" :value="2" />
                    <el-option label="Motorhome" :value="3" />
                </el-select> 
            </el-space>
            <el-button type="primary" @click="getTableData">Search</el-button>
            <el-button type="primary" @click="showContent = contentList[1]">Add</el-button>
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
                <el-table-column prop="brandName" label="Brand name" min-width="110" />
                <el-table-column label="Image"  min-width="110">
                    <template #default="scope">
                        <el-image
                            style="width: 100px; height: 100px"
                            :src="`${imgHttpPre}${scope.row.image}`"
                            fit="contain"></el-image>    
                    </template>
                </el-table-column>
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
        <el-dialog v-model="dialogVisible" title="Prompt" width="400px" draggable>
            <span>Confirm deletion of this brand?</span>
            <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">Cancel</el-button>
                <el-button type="primary" @click="deleteData">Confirm</el-button>
            </span>
            </template>
        </el-dialog>
    </div>
    <div class="content-class" v-else-if="showContent === contentList[1]">
        <add-view @back="toTablePage"></add-view>
    </div>
    <div class="content-class" v-else-if="showContent === contentList[2]">
        <edit-view @back="toTablePage" :row="curRow"></edit-view>
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
import { useBrandsStore } from '@/stores/brands'
import type { Data } from '@/stores/brands'
const { appContext } = getCurrentInstance() as any;
const brandsStore = useBrandsStore();
const imgHttpPre = appContext.config.globalProperties.$imgHttpPre 

const searchText = ref('')
const carType = ref(0)
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
    getTableData()
})
const getTableData = async() => {
    const reqData = {
        searchText: searchText.value,
        page: currentPage.value,
        rows: pageSize.value,
        type: carType.value
    }
    const res = await brandsStore.getTableDataAction(reqData)
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
    const res = await brandsStore.deleteDataAction({brandId: curRow.value?.brandId as number})
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
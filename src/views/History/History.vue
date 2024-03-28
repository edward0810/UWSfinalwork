<template>
    <div class="content-class" v-if="showContent===contentList[0]">
        <div class="content-table-class">
            <el-image v-for="(item, index) in tableData" :key="index"
                style="width: 300px; height: 300px;margin-right: 30px; margin-bottom: 30px"
                :src="`${imgHttpPre}${item.image}`"
                fit="contain"
                @click="toDetailPage(item)"></el-image> 
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
            <span>Are you sure to delete the car?</span>
            <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">cancel</el-button>
                <el-button type="primary" @click="deleteData">confirm</el-button>
            </span>
            </template>
        </el-dialog>
    </div>
    <div class="content-class" v-else-if="showContent === contentList[2]">
        <detail-view @back="toTablePage" :row="curRow"></detail-view>
    </div>
</template>
<script setup lang="ts">
import OperateButton from '@/components/OperateButton.vue'; 
import DetailView from './components/DetailView.vue';
// import TestView from './components/TestView.vue';
import { getCurrentInstance, onMounted, ref } from 'vue' 
import { ElMessage } from 'element-plus'
import type{ ElTable} from 'element-plus'
import { Search } from '@element-plus/icons-vue'  
import { useCarsStore } from '@/stores/cars'
import { useRecordsStore } from '@/stores/records'
import type { Data } from '@/stores/cars'
const { appContext } = getCurrentInstance() as any;
const carsStore = useCarsStore();
const recordsStore = useRecordsStore();
const imgHttpPre = appContext.config.globalProperties.$imgHttpPre 

const searchText = ref('')
const brandId = ref(3)
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
        rows: pageSize.value
    }
    const res = await recordsStore.getHistoryDataAction(reqData)
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
const toDetailPage = (row: Data) => {
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
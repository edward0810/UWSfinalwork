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
            <el-button type="primary" @click="getTableData">search</el-button>
            <el-button type="primary" @click="showContent = contentList[1]">Add an account</el-button>
        </div>
        <div class="content-table-class">
            <el-table
                ref="multipleTableRef"
                :data="tableData"
                style="width: 100%;"
                height="100%"
                :header-cell-style="{'text-align':'center'}"
                :cell-style="{'text-align':'center'}"
                highlight-current-row 
            >
                <el-table-column fixed type="index" label="SN" width="55" />
                <el-table-column prop="userName" label="account" min-width="110" />
                <el-table-column prop="password" label="password" min-width="110"/>
                <el-table-column prop="createTime" label="Creation time" min-width="160" />
                <el-table-column fixed="right" label="Operation" width="260">
                    <template #default="scope">
                        <operate-button @click="toEditPage(scope.$index, scope.row)" :btnName="'edit'"></operate-button>
                        <operate-button @click="toEditPwdPage(scope.$index, scope.row)" :btnName="'reset'"></operate-button>
                        <operate-button @click="toDelete(scope.$index, scope.row)" :btnName="'delete'"></operate-button>
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
            <span>Confirm to delete the account？</span>
            <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">cancel</el-button>
                <el-button type="primary" @click="deleteData">confirm</el-button>
            </span>
            </template>
        </el-dialog>
    </div>
    <div class="content-class" v-else-if="showContent === contentList[1]">
        <add-view @back="toTablePage"></add-view>
    </div>
    <div v-else-if="showContent === contentList[2]">
        <edit-view @back="toTablePage" :row="curRow"></edit-view>
    </div>
    <div v-else-if="showContent === contentList[3]">
        <edit-pwd @back="toTablePage" :row="curRow"></edit-pwd>
    </div>
    
</template>
<script setup lang="ts">
import OperateButton from '@/components/OperateButton.vue'; 
import AddView from './components/AddView.vue';
import EditView from './components/EditView.vue';
import EditPwd from './components/EditPwdView.vue';
import { onMounted,ref } from 'vue'
import { ElMessage } from 'element-plus'
import type{ ElTable } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
import { useUsersStore } from '@/stores/users'; 
import type{ Data } from '@/stores/users'; 
const usersStore = useUsersStore(); 

const dialogVisible = ref(false)
const curRow = ref<Data>()
const searchText = ref('')
const currentPage = ref(1)
const pageSize = ref(10)
const dataTotal = ref(0)
let tableData = ref<Data[]>([])
const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const contentList = ref<string[]>(['table', 'add', 'edit', 'editPwd'])
let showContent = ref<string>(contentList.value[0])

onMounted(() => {
    getTableData()
})
const getTableData = async() => {
    const reqData = {
        searchText: searchText.value,
        page: currentPage.value,
        rows: pageSize.value
    }
    const res = await usersStore.getTableDataAction(reqData)
    if(res){
        dataTotal.value = res.data.count
        tableData.value = res.data.dataList
    } else {
        dataTotal.value = 0
        tableData.value = []
    }
} 
const deleteData = async() => {
    const res = await usersStore.deleteDataAction({userIds: curRow.value?.userId+''})
    if(res){
        ElMessage.success(res.message); 
        dialogVisible.value = false
        getTableData()
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
const toEditPwdPage = (index: number, row: Data) => {
    curRow.value = row
    showContent.value = contentList.value[3]
}
const toDelete = (index: number, row: Data) => {
    curRow.value = row
    dialogVisible.value = true
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
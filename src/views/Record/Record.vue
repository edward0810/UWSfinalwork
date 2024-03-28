<template>
    <div>
        <div class="title">
            news
        </div>
        <div class="content">
            <el-image v-for="(item, index) in tableDataNews" :key="index"
                style="width: 300px; height: 300px;margin-right: 40px;"
                :src="item.image"
                fit="contain"
                @click="toNewPage(item.url)"></el-image>  
        </div>
        <div class="title">
            <span style="vertical-align: middle"> Browsing history </span>
            <el-tooltip
                class="box-item"
                effect="dark"
                content="See more"
                placement="bottom"
            >
                <el-icon color="#4c4c4c" :size="18" @click="toHistoryPage" style="vertical-align: middle"><DArrowRight /></el-icon>
            </el-tooltip>
        </div>
        <div class="content">
            <el-image v-for="(item, index) in tableDataHistory" :key="index"
                style="width: 300px; height: 300px;margin-right: 40px;"
                :src="`${imgHttpPre}${item.image}`"
                fit="contain"></el-image>  
        </div>
    </div>
</template>
<script setup lang="ts">
import { getCurrentInstance, onMounted, ref } from 'vue' 
import { useRecordsStore } from '@/stores/records'
import type { Data } from '@/stores/records'
import { useRouter } from 'vue-router'
import { DArrowRight, Search } from '@element-plus/icons-vue'  
const { appContext } = getCurrentInstance() as any;
const recordsStore = useRecordsStore();
const router = useRouter();
const imgHttpPre = appContext.config.globalProperties.$imgHttpPre 


let tableDataNews = ref<Array<Data>>([])
let tableDataHistory = ref<Array<Data>>([])

onMounted(() => {
    getTableData()
})

const toHistoryPage = () => {
    router.push('history')
}

const toNewPage = (url: string | undefined)=>{
    // window.location.href = url
    window.open(url)
}
const getTableData = async() => {
    const reqData = {}
    const resNews = await recordsStore.getNewsDataAction(reqData);
    if(resNews){
        tableDataNews.value = resNews.data.dataList
    } else {
        tableDataNews.value = []
    }
    const resHistory = await recordsStore.getHistoryDataAction({page: 1, rows: 3});
    if(resHistory){
        tableDataHistory.value = resHistory.data.dataList
    } else {
        tableDataHistory.value = []
    }
} 
</script>
<style lang="scss" scoped>
.title{
    margin-top: 30px;
}
.content{
    margin-top: 20px;
    margin-bottom: 50px;
}
</style>

<template>
  <el-config-provider>
    <RouterView />
  </el-config-provider>
</template>

<script setup lang="ts">
import { ElConfigProvider } from 'element-plus';
import zhCn from "element-plus/lib/locale/lang/zh-cn";
// import { onMounted } from 'vue';
// onMounted(()=> {
//   let loading = document.getElementById('preloader');
//     if (loading !== null) {
//        document.body.removeChild(loading);
//     }
// })
</script>

<style lang="scss">
@import '@/assets/styles/reset.scss';
@import '@/assets/styles/iconfont.scss';
@import '@/assets/styles/common.scss';
</style>

<template>
  <el-container>
    <el-header>
      <home-header></home-header>
    </el-header> 
    <el-main>
      <home-control></home-control>
      <home-main></home-main>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import HomeHeader from './components/HomeHeader.vue';
import HomeControl from './components/HomeControl.vue';
import HomeMain from './components/HomeMain.vue';
import { onMounted } from 'vue';
onMounted(()=> {
  let loading = document.getElementById('preloader');
    if (loading !== null) {
       document.body.removeChild(loading);
    }
})
</script>

<style scoped lang="scss">
.el-container{
  min-width: 850px;
  min-height: 700px;
}
.el-header{
  box-shadow: 0 8px 24px -2px rgb(0 0 0 / 5%);
  background-color: #1c53d9; 
  // background: linear-gradient(to right, #fff, rgb(199, 251, 251), rgb(252, 227, 255), rgb(241, 255, 216));
  color: #fff;
} 
.el-main{
  display: flex;
  flex-direction: column;
  height: calc( 100vh - 50px - 14px );
}
</style>
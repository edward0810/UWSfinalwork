<template>
  <div class="status-wrapper">
    <img src="@/assets/images/500.png" alt="">
    <p>Service exception</p>
    <router-link to="/">
      <el-button type="primary">Back to home page</el-button>
    </router-link>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
onMounted(()=> {
  let loading = document.getElementById('preloader');
    if (loading !== null) {
       document.body.removeChild(loading);
    }
})
</script>

<style scoped lang="scss">

</style>
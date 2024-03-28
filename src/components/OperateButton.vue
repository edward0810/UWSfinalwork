<template>
    <div class="operate-btn0-class">
        <div class="operate-btn1-class">
            <div class="operate-btn2-class">{{prop.btnName}}</div>
        </div>
    </div>
</template>
<script setup lang="ts">
import { defineProps } from 'vue';
const prop = defineProps({
    btnName: {
        type: String,
        default: ''
    }
})
</script>
<style lang="scss" scoped>
.operate-btn0-class{
    display: inline-block;
    margin: 0 5px;
    width: fit-content;
    padding: 1px;
    border-radius: 14px;
    background: linear-gradient(180deg, #c4d0ee 0%, #caceda 40%, #c5c5c5 100%);
    &:hover{
        cursor: pointer;
    }
    &:active{
        transform: scale(0.99);
    }
}
.operate-btn1-class{
    width: fit-content;
    padding: 0 2px 3px 2px;
    border-radius: 12px;
    background: linear-gradient(180deg, #fff 0%,#fff 50%, #e9e9e9 100%);
    &:active{
        transform: scale(0.98);
    }
}
.operate-btn2-class{
    text-align: center;
    width: fit-content;
    padding: 0 10px;
    height: 22px;
    line-height: 23px;
    letter-spacing: 1px;
    border-radius: 10px;
    background: linear-gradient(180deg, #fff 0%,#fff 90%, #ececec 100%);
    // box-shadow: 0 4px 4px 0 #EAEAEA;
} 
</style>
<template>
    <div class="control-class clearfix">
        <!--  animation_${index} -->
        <div class="control-btn-class" :class="`${item.path === route.path.split('/')[1] ? 'control-btn-selected' : ''}`"  v-for="(item,index) in menuItemList" 
        :key="item.path" @click="router.push(item.path)">
            <div class="control-span-class">{{item.meta?.title}}</div>
        </div>
    </div>
</template>
<script setup lang="ts">
import _ from 'lodash'
import { useRoute, useRouter } from 'vue-router';
import { useUsersStore } from '@/stores/users'
import { storeToRefs } from 'pinia'
const router = useRouter();
const route = useRoute();
const usersStore = useUsersStore()
const { menuItemList } = storeToRefs(usersStore)
// let refs = ref(null);
// onMounted(() => {
//     let { $refs } = (getCurrentInstance() as any).proxy;
//     refs.value = $refs;
// })
</script>
<style lang="scss" scoped>
.control-class{
    padding: 10px;
    box-sizing: border-box;
    width: 100%;
    .control-btn-class{
        width: fit-content;
        height: 30px;
        line-height: 30px;
        float: right;
        color: #34475a;
        padding: 1px;
        margin-left: 20px;
        border-radius: 4px;
        background: #fff;
        box-shadow: 0 0 3px 0px #dedede;
        
        &:hover{
            color: #7ca5e7;
            cursor: pointer;
            background: linear-gradient(180deg, #FFFFFF 0%, #7ca5e7 100%);
        }
        &:active{
            // color: #0447b3 !important;
            // background: linear-gradient(180deg, #FFFFFF 0%, #0447b3 100%) !important;
            transform: scale(0.95);
        }
        .control-span-class{
            width: fit-content;
            padding: 0px 22px;
            background: #fff;
            border-radius: 3px;
        }
    } 
    .animation_0{
        opacity: 0;
        animation: fadeInOne 1.5s ease-in-out;
        animation-fill-mode: forwards;
    }
    .animation_1{
        opacity: 0;
        animation: fadeIn 1.5s ease-in-out;
        animation-delay: 0.5s;
        animation-fill-mode: forwards;
    }
    .animation_2{
        opacity: 0;
        animation: fadeIn 1.5s ease-in-out;
        animation-delay: 1s;
        animation-fill-mode: forwards;
    }
    .control-btn-selected{
        color: #0459e0 !important;
        background: linear-gradient(180deg, #FFFFFF 0%, #0459e0 100%) !important;
    } 
}
@keyframes fadeInOne{
    0%{
        transform: translate(-300px, 0);
        opacity: 0;
    }
    80%{
        transform: translate(10px, 0);
        opacity: 0.8;
    }
    90%{
        transform: translate(-5px, 0);
        opacity: 0.9;
    }
    100%{
        // transform: translate(0, 0);
        opacity: 1;
    }
}
@-webkit-keyframes fadeInOne{
    0%{
        transform: translate(-300px, 0);
        opacity: 0;
    }
    80%{
        transform: translate(10px, 0);
        opacity: 0.8;
    }
    90%{
        transform: translate(-5px, 0);
        opacity: 0.9;
    }
    100%{
        // transform: translate(0, 0);
        opacity: 1;
    }
}
@-moz-keyframes fadeInOne{
    0%{
        transform: translate(-300px, 0);
        opacity: 0;
    }
    80%{
        transform: translate(10px, 0);
        opacity: 0.8;
    }
    90%{
        transform: translate(-5px, 0);
        opacity: 0.9;
    }
    100%{
        // transform: translate(0, 0);
        opacity: 1;
    }
}
@keyframes fadeIn{
    0%{
        transform: translate(-300px, 0);
        opacity: 0;
    }
    80%{
        transform: translate(20px, 0);
        opacity: 0.8;
    }
    90%{
        transform: translate(-5px, 0);
        opacity: 0.9;
    }
    100%{
        // transform: translate(0, 0);
        opacity: 1;
    }
}
@-webkit-keyframes fadeIn{
    0%{
        transform: translate(-300px, 0);
        opacity: 0;
    }
    80%{
        transform: translate(20px, 0);
        opacity: 0.8;
    }
    90%{
        transform: translate(-5px, 0);
        opacity: 0.9;
    }
    100%{
        // transform: translate(0, 0);
        opacity: 1;
    }
}
@-moz-keyframes fadeIn{
    0%{
        transform: translate(-300px, 0);
        opacity: 0;
    }
    80%{
        transform: translate(20px, 0);
        opacity: 0.8;
    }
    90%{
        transform: translate(-5px, 0);
        opacity: 0.9;
    }
    100%{
        // transform: translate(0, 0);
        opacity: 1;
    }
}
</style>
<template>
    <el-tooltip content="Return list">
        <el-icon class="content-close-class" @click="backToF()">
            <CircleCloseFilled />
        </el-icon>
    </el-tooltip>
    <div style="height: 30px"></div>
    <div style="display: flex">
        <div style="width: 400px; height: 400px; margin: 0 30px 20px">
            <el-image
                style="width: 100%; height: 100%;background: #eee"
                :src="`${imgHttpPre}${row.image}`"
                fit="contain"></el-image>
            <div style="width: 80%; display: flex; margin: 0 auto;margin-top: 20px; justify-content: space-evenly">
                <el-tooltip
                    class="box-item"
                    effect="dark"
                    content="Favourite"
                    placement="bottom"
                >
                    <img style="width: 20px; height: 20px" src="@/assets/images/saved0.png" fit="cover" @click="add()"/>
                </el-tooltip>
                <el-tooltip
                    class="box-item"
                    effect="dark"
                    content="Write a "
                    placement="bottom"
                >
                    <img style="width: 20px; height: 20px" src="@/assets/images/comment.png" fit="cover" @click="dialogFormVisible = true"/>
                </el-tooltip> 
            </div> 
        </div>
        <div style="width: calc( 100% - 400px )">
            <el-descriptions column="2" :title="row.carName">
                <el-descriptions-item label="level">{{row.level}}</el-descriptions-item>
                <!-- <el-descriptions-item label="brand">{{row.brandName}}</el-descriptions-item> -->
                <el-descriptions-item label="Environmental protection standard">{{row.environmentProtection}}</el-descriptions-item>
                <el-descriptions-item label="Maximum power">{{row.maxPower}}</el-descriptions-item>
                <el-descriptions-item label="Maximum torque">{{row.maxTorque}}<el-tag size="small">N·m</el-tag></el-descriptions-item>
                <el-descriptions-item label="engine">{{row.engine}}</el-descriptions-item>
                <el-descriptions-item label="gearbox">{{row.gearbox}}</el-descriptions-item>
                <el-descriptions-item label="Length x width x height">{{row.size}}<el-tag size="small">mm</el-tag></el-descriptions-item>
                <el-descriptions-item label="Body structure">{{row.bodyStructure}}</el-descriptions-item>
                <el-descriptions-item label="Maximum speed">{{row.maxSpeed}}<el-tag size="small">km/h</el-tag></el-descriptions-item>
                <el-descriptions-item label="Combined fuel consumption">{{row.wltc}}<el-tag size="small">L/100km</el-tag></el-descriptions-item>
                <el-descriptions-item label="Vehicle warranty">{{row.warranty}}</el-descriptions-item>
                <el-descriptions-item label="Price">{{row.price}}<el-tag size="small">yuan</el-tag></el-descriptions-item> 
            </el-descriptions>
        </div>
    </div>
    <el-dialog v-model="dialogFormVisible" width="600px" title="comment" center draggable>
        <el-form
            ref="ruleFormRef"
            :model="ruleForm"
            :rules="rules"
            label-width="60px"
            class="content-add-class"
            >
            <el-form-item label=" " prop="comment">
                <el-input
                v-model="ruleForm.comment"
                :rows="5"
                type="textarea"
                placeholder="Please enter a comment"
                />
            </el-form-item>
        </el-form>
        <template #footer>
        <span class="dialog-footer">
            <el-button type="primary" @click="submitForm(ruleFormRef)" auto-insert-space>
            confirm
            </el-button>
        </span>
        </template>
    </el-dialog>
</template>
<script setup lang="ts">
// import OperateButton from '@/components/OperateButton.vue'; 
import CircleCloseFilled from '@/components/icons/CircleCloseFilled.vue';
import { getCurrentInstance, reactive, ref, defineEmits, defineProps, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import type{ FormInstance, FormRules } from 'element-plus'
import { useCarsStore } from '@/stores/cars'
import type { Data } from '@/stores/cars'
import { useRecordsStore } from '@/stores/records'
import type { Data as RecordData, CommentData } from '@/stores/records'
// import type { Data } from '@/stores/brands'
const recordsStore = useRecordsStore();
const carsStore = useCarsStore();
const emit = defineEmits(['back'])
// const prop = withDefaults(defineProps<{
//     row?: Data
// }>(),{})
const prop = defineProps<{
    row?: Data | undefined | any
}>()
const { appContext, proxy } = getCurrentInstance() as any;
const imgTypeA = appContext.config.globalProperties.$imgTypeA;
const imgTypeLen = appContext.config.globalProperties.$imgTypeLen;
const imgHttpPre = appContext.config.globalProperties.$imgHttpPre
// const brandName = ref('') 
// for(let i=0;i<prop.brands.length;i++){
//     if(prop.brands[i].brandId === (prop.row as Data).brandId){
//         brandName.value = prop.brands[i].brandName;
//         break;
//     }
// }

onMounted(() => {
    addHistory()
})

const dialogFormVisible = ref(false)
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<RecordData>({
    carId: (prop.row as Data).carId,
    commentStr: ''
})
const rules = reactive<FormRules>({
    commentStr: [{ required: true, message: 'Please enter a comment', trigger: 'change' }]
})
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate(async(valid) => {
    if (valid) {
      const res = await recordsStore.insertCommentDataAction({carId: ruleForm.carId, comment: ruleForm.commentStr} as CommentData)
      if(res){
        ElMessage.success(res.message); 
        dialogFormVisible.value = false
        formEl.resetFields()
      }
    } else {
      console.log('error submit!')
      return false
    }
  })
}

const addHistory = async() => {
    const res = await recordsStore.insertHistoryDataAction({carId: (prop.row as Data).carId})
}

const backToF = (refresh = false) => {
  emit('back', refresh);
}

const add = async() => {
    const res = await recordsStore.insertSavedDataAction({carId: (prop.row as Data).carId})
    if(res){
        ElMessage.success(res.message);
    }
}


</script>
<style lang="scss" scoped>
.content-add-class{
    width: 60%;
    margin: 0 auto;
    .content-title-class{
        widows: 100%; 
        text-align: center;
        line-height: 60px;
    }
    .el-button{
        margin: 0 auto;
    }
}
</style>
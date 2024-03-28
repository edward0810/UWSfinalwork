<template>
    <el-tooltip content="Cancel addition, return to the list.">
        <el-icon class="content-close-class" @click="resetForm(ruleFormRef)">
            <CircleCloseFilled />
        </el-icon>
    </el-tooltip>
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="content-add-class"
        >
        <h1 class="content-title-class" tag="b">Add brand</h1>
        <el-form-item label="Brand name" prop="brandName">
            <el-input
            v-model="ruleForm.brandName"
            type="text"
            placeholder="Please enter the brand name."
            />
        </el-form-item>
        <el-form-item label="Type" prop="type">
            <el-select v-model="ruleForm.type" filterable placeholder="Please select a type">
                <el-option
                    v-for="item in options"
                    :key="item.key"
                    :label="item.label"
                    :value="item.key">
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="Image" prop="file">
            <el-upload
                class="upload-demo"
                action="" 
                :on-remove="handleRemove"    
                :on-change="handleChange"
                :file-list="fileList"
                :auto-upload="false"
                :http-request="uploadFile"
                :limit="1"
                :on-exceed="handleExceed"
                ref="uploadRef"
                list-type="picture">
                <el-button type="primary" >Click to upload<i class="el-icon-upload el-icon--right"></i></el-button>
                <div slot="tip" class="el-upload__tip">It can only be uploaded. {{imgTypeA.join('、')}} File, and not exceed {{imgTypeLen}} KB</div>
            </el-upload>
        </el-form-item>
    </el-form>
    <div style="text-align: center;">
        <el-button
        type="primary"
        @click="submitForm(ruleFormRef)"
        auto-insert-space
        >Confirm</el-button>
    </div>
</template>
<script setup lang="ts">
// import OperateButton from '@/components/OperateButton.vue'; 
import CircleCloseFilled from '@/components/icons/CircleCloseFilled.vue';
import { getCurrentInstance, reactive, ref, defineEmits } from 'vue'
import { ElMessage } from 'element-plus'
import type{ FormInstance, FormRules } from 'element-plus'
import { useBrandsStore } from '@/stores/brands'
import type { Data } from '@/stores/brands'
const brandsStore = useBrandsStore();
const emit = defineEmits(['back'])
const { appContext, proxy } = getCurrentInstance() as any;
const imgTypeA = appContext.config.globalProperties.$imgTypeA;
const imgTypeLen = appContext.config.globalProperties.$imgTypeLen;

const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<Data>({
    brandName: '',
    type: 0,
    file: ''
})
const rules = reactive<FormRules>({
  brandName: [{ required: true, message: 'Please enter the brand name', trigger: 'change' }]
})

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  backToF()
  formEl.resetFields()
}
const backToF = (refresh = false) => {
  emit('back', refresh);
}
const options = [
    {
        key: 0,
        label: 'Car'
    },
    {
        key: 1,
        label: 'Motorcycle'
    },
    {
        key: 2,
        label: 'Van'
    },
    {
        key: 3,
        label: 'Motorhome'
    }
]
const fileList = ref([]);
const hasFile = ref(false)
const handleExceed = (file: any, fileL: any) => {
    ElMessage.warning('Just need to upload one photo.');
}
const handleRemove = (file: any, fileL: never[]) => {
    fileList.value = fileL;
    hasFile.value = false;
}
const handleChange = (file: any, fileL: never[]) => {
    // console.log('handleChange',file,this.ruleForm)
    fileList.value = fileL
    Object.assign(ruleForm, {'file': file})
    if(!checkUploadTx(file))
    handleRemove(file, [])
}
const checkUploadTx = (file: { name: string; size: number; }) => {
    // console.log('file.type==',file.type)
    let fileName = new Array();
    fileName = file.name.split(".");
    const suffix = fileName[fileName.length - 1].toLowerCase()
    const isJPG = imgTypeA.includes(suffix)
    const isLt2M = file.size / 1024 < imgTypeLen;
    if (!isJPG) {
        ElMessage.warning(`Uploading profile picture can only be ${imgTypeA.join('、')} format!`);
    }
    if (!isLt2M) {
        ElMessage.warning(`The size of the uploaded profile picture cannot exceed ${imgTypeLen}KB!`);
    }
    return isJPG && isLt2M;
}
const uploadFile = (file: { file: string | { readonly size: number; readonly type: string; arrayBuffer: () => Promise<ArrayBuffer>; slice: (start?: number | undefined, end?: number | undefined, contentType?: string | undefined) => Blob; stream: () => ReadableStream<Uint8Array>; text: () => Promise<string>; } | undefined; }) => {
    ruleForm['file'] = file.file
    hasFile.value = true
}
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate(async(valid) => {
    if (valid) {
        await proxy.$refs["uploadRef"].submit();
        if(hasFile.value){
            const res = await brandsStore.insertDataAction(ruleForm)
            if(res){
                ElMessage.success(res.message);
                backToF(true)
                formEl.resetFields()
            }
        } else {
            ElMessage.error("Please upload a photo.")
        }
    } else {
      console.log('error submit!')
      return false
    }
  })
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
<template>
    <el-tooltip content="Cancel the modification and return to the list">
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
        <h1 class="content-title-class" tag="b">Modify</h1>
        <el-form-item label="brand" prop="brandId">
            <el-select v-model="ruleForm.brandId" filterable placeholder="Please select brand">
                <el-option
                    v-for="item in brands"
                    :key="item.brandId"
                    :label="item.brandName"
                    :value="item.brandId">
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="Car model name" prop="carName">
            <el-input
            v-model="ruleForm.carName"
            type="text"
            placeholder="Please enter car model name"
            />
        </el-form-item>
        <el-form-item label="level" prop="level">
            <el-input
            v-model="ruleForm.level"
            type="text"
            placeholder="Please enter the class, for example: medium and large car, medium SUV"
            />
        </el-form-item>
        <el-form-item label="Environmental protection standard" prop="environmentProtection">
            <el-input
            v-model="ruleForm.environmentProtection"
            type="text"
            placeholder="Please enter environmental standards, such as Euro I, Euro II"
            />
        </el-form-item>
        <el-form-item label="Maximum power" prop="maxPower">
            <el-input
            v-model="ruleForm.maxPower"
            type="text"
            placeholder="Please enter the maximum power"
            ><template #append>kW</template></el-input>
        </el-form-item>
        <el-form-item label="Maximum torque" prop="maxTorque">
            <el-input
            v-model="ruleForm.maxTorque"
            type="text"
            placeholder="Please enter the maximum torque"
            ><template #append>N·m</template></el-input>
        </el-form-item>
        <el-form-item label="engine" prop="engine">
            <el-input
            v-model="ruleForm.engine"
            type="text"
            placeholder="Please enter engine"
            />
        </el-form-item>
        <el-form-item label="gearbox" prop="gearbox">
            <el-input
            v-model="ruleForm.gearbox"
            type="text"
            placeholder="Please enter gearbox"
            />
        </el-form-item>
        <el-form-item label="Length x width x height" prop="size">
            <el-input
            v-model="ruleForm.size"
            type="text"
            placeholder="Please enter length x width x height"
            ><template #append>mm</template></el-input>
        </el-form-item>
        <el-form-item label="Body structure" prop="bodyStructure">
            <el-input
            v-model="ruleForm.bodyStructure"
            type="text"
            placeholder="Please enter the body structure"
            />
        </el-form-item>
        <el-form-item label="Maximum speed" prop="maxSpeed">
            <el-input
            v-model="ruleForm.maxSpeed"
            type="text"
            placeholder="Please enter maximum speed"
            ><template #append>km/h</template></el-input>
        </el-form-item>
        <el-form-item label="Combined fuel consumption" prop="wltc">
            <el-input
            v-model="ruleForm.wltc"
            type="text"
            placeholder="Please enter combined fuel consumption"
            ><template #append>L/100km</template></el-input>
        </el-form-item>
        <el-form-item label="Vehicle warranty" prop="warranty">
            <el-input
            v-model="ruleForm.warranty"
            type="text"
            placeholder="Please enter vehicle warranty"
            />
        </el-form-item>
        <el-form-item label="Price" prop="price">
            <el-input
            v-model="ruleForm.price"
            type="text"
            placeholder="Please enter price"
            ><template #append>yuan</template></el-input>
        </el-form-item>
        <el-form-item label="image" prop="file">
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
                <div slot="tip" class="el-upload__tip">Upload only {{imgTypeA.join('、')}} File, and not more than {{imgTypeLen}} KB</div>
            </el-upload>
        </el-form-item>
    </el-form>
    <div style="text-align: center;">
        <el-button
        type="primary"
        @click="submitForm(ruleFormRef)"
        auto-insert-space
        >confirm</el-button>
    </div>
</template>
<script setup lang="ts">
// import OperateButton from '@/components/OperateButton.vue'; 
import CircleCloseFilled from '@/components/icons/CircleCloseFilled.vue';
import { getCurrentInstance, reactive, ref, defineEmits, defineProps } from 'vue'
import { ElMessage } from 'element-plus'
import type{ FormInstance, FormRules } from 'element-plus'
import { useCarsStore } from '@/stores/cars'
import type { Data } from '@/stores/cars'
import { useBrandsStore } from '@/stores/brands'
import type { Data as BrandData } from '@/stores/brands'
const brandsStore = useBrandsStore();
const carsStore = useCarsStore();
const emit = defineEmits(['back'])
// const prop = defineProps({
//     row: {},
//     brands: []
// }) 
const prop = withDefaults(defineProps<{
    row?: Data,
    brands?: BrandData[]
}>(),{})
const { appContext, proxy } = getCurrentInstance() as any;
const imgTypeA = appContext.config.globalProperties.$imgTypeA;
const imgTypeLen = appContext.config.globalProperties.$imgTypeLen;
const imgHttpPre = appContext.config.globalProperties.$imgHttpPre 

const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<Data>({
    carId: (prop.row as Data).carId,
    brandId: (prop.row as Data).brandId,
    carName: (prop.row as Data).carName,
    level: (prop.row as Data).level,
    environmentProtection: (prop.row as Data).environmentProtection,
    maxPower: (prop.row as Data).maxPower,
    maxTorque: (prop.row as Data).maxTorque,
    engine: (prop.row as Data).engine,
    gearbox: (prop.row as Data).gearbox,
    size: (prop.row as Data).size,
    bodyStructure: (prop.row as Data).bodyStructure,
    maxSpeed: (prop.row as Data).maxSpeed,
    wltc: (prop.row as Data).wltc,
    warranty: (prop.row as Data).warranty,
    price: (prop.row as Data).price,
    file: (prop.row as Data).image
})
const fileList = ref<Data[]>([]);
if((prop.row as Data).image){
    fileList.value = [
        {
            name: (prop.row as Data).image,
            url: `${imgHttpPre}${(prop.row as Data).image}`
        }
    ]
}
const rules = reactive<FormRules>({
  brandId: [{ required: true, message: 'Please select brand', trigger: 'change' }],
  carName: [{ required: true, message: 'Please enter car model name', trigger: 'change' }],
  level: [{ required: true, message: 'Please enter the level', trigger: 'change' }],
  environmentProtection: [{ required: true, message: 'Please enter environmental standards', trigger: 'change' }],
  maxPower: [{ required: true, message: 'Please enter the maximum power', trigger: 'change' }],
  maxTorque: [{ required: true, message: 'Please enter the maximum torque', trigger: 'change' }],
  engine: [{ required: true, message: 'Please enter engine', trigger: 'change' }],
  gearbox: [{ required: true, message: 'Please enter gearbox', trigger: 'change' }],
  size: [{ required: true, message: 'Please enter length x width x height', trigger: 'change' }],
  bodyStructure: [{ required: true, message: 'Please enter the body structure', trigger: 'change' }],
  maxSpeed: [{ required: true, message: 'Please enter maximum speed', trigger: 'change' }],
  wltc: [{ required: true, message: 'Please enter combined fuel consumption', trigger: 'change' }],
  warranty: [{ required: true, message: 'Please enter vehicle warranty', trigger: 'change' }],
  price: [{ required: true, message: 'Please enter price', trigger: 'change' }]
})

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  backToF()
  formEl.resetFields()
}
const backToF = (refresh = false) => {
  emit('back', refresh);
}
const hasFile = ref(false)
const handleExceed = (file: any, fileL: any) => {
    ElMessage.warning('Just upload a photo');
}
const handleRemove = (file: any, fileL: { [x: string]: unknown; brandId?: string | number | undefined; carName?: string | undefined; level?: string | undefined; environmentProtection?: string | undefined; file?: string | { readonly size: number; readonly type: string; arrayBuffer: () => Promise<ArrayBuffer>; slice: (start?: number | undefined, end?: number | undefined, contentType?: string | undefined) => Blob; stream: () => ReadableStream<Uint8Array>; text: () => Promise<string>; } | undefined; }[]) => {
    fileList.value = fileL;
    hasFile.value = false;
}
const handleChange = (file: any, fileL: {
[x: string]: unknown;
// import OperateButton from '@/components/OperateButton.vue'; 
brandId?: string | number | undefined; carName?: string | undefined; level?: string | undefined; environmentProtection?: string | undefined; file?: string | { readonly size: number; readonly type: string; arrayBuffer: () => Promise<ArrayBuffer>; slice: (start?: number | undefined, end?: number | undefined, contentType?: string | undefined) => Blob; stream: () => ReadableStream<Uint8Array>; text: () => Promise<string>; } | undefined;
}[]) => {
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
        ElMessage.warning(`Upload profile picture can only be ${imgTypeA.join('、')} Format!`);
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
        if(hasFile.value || fileList.value.length > 0){
            const res = await carsStore.updateDataAction(ruleForm)
            if(res){
                ElMessage.success(res.message);
                backToF(true)
                formEl.resetFields()
            }
        } else {
            ElMessage.error("Please upload photo")
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
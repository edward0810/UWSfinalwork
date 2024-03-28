<template>
    <el-tooltip content="Cancel the modification and return to the list">
        <el-icon class="content-close-class" @click="resetForm(ruleFormRef)"><CircleCloseFilled /></el-icon>
    </el-tooltip>
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="70px"
        class="content-add-class"
        >
        <h1 class="content-title-class" tag="b">Password reset</h1>
        <el-form-item label="account" prop="userName">
            <el-input
            v-model="ruleForm.userName"
            type="text"
            disabled
            placeholder="Please enter your account"
            />
        </el-form-item>
        <el-form-item label="password" prop="password">
            <el-input
            v-model="ruleForm.password"
            type="text"
            placeholder="Please enter password"
            />
        </el-form-item> 
    </el-form>
    <div style="text-align: center;">
        <el-button
        type="primary"
        @click="submitForm(ruleFormRef)"
        auto-insert-space
        :loading="loading"
        >confirm</el-button>
    </div>
</template>
<script setup lang="ts">
import CircleCloseFilled from '@/components/icons/CircleCloseFilled.vue';
import { reactive, ref, defineEmits, defineProps } from 'vue'
import { ElMessage } from 'element-plus'
import type{ FormInstance, FormRules } from 'element-plus'
import { useUsersStore } from '@/stores/users'; 
import type{ Data } from '@/stores/users'; 
const usersStore = useUsersStore(); 
  
const emit = defineEmits(['back'])
const prop = withDefaults(defineProps<{
    row?: Data
}>(),{})
const loading = ref(false)
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<Data>({
    userId: (prop.row as Data)?.userId,
    userName: (prop.row as Data)?.userName,
    password: (prop.row as Data)?.password
})
const rules = reactive<FormRules>({
  password: [{ required: true, message: 'Please enter password', trigger: 'change' }]
})
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  backToF()
  formEl.resetFields()
}
const backToF = (refresh = false) => {
  emit('back', refresh);
}
const submitForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate(async(valid) => {
        if (valid) {
            loading.value = true
            const res = await usersStore.updatePwdAction( ruleForm)
            if(res){
                ElMessage.success(res.message); 
                backToF(true)
                formEl.resetFields()
            }
            loading.value = false
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
<template>
    <el-tooltip content="Cancel the addition and return to the list">
        <el-icon class="content-close-class" @click="resetForm(ruleFormRef)"><CircleCloseFilled /></el-icon>
    </el-tooltip>
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="70px"
        class="content-add-class"
        >
        <h1 class="content-title-class" tag="b">add</h1>
        <el-form-item label="account" prop="userName">
            <el-input
            v-model="ruleForm.userName"
            type="text"
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
        
        <!-- <el-form-item label="limit" prop="treeSelectVal">
            <el-tree-select
                v-model="ruleForm.treeSelectVal"
                :data="authArr"
                multiple
                :default-expand-all="true"
                show-checkbox
                check-on-click-node
                clearable 
                placeholder="Please select permissions"
            />
        </el-form-item>  -->
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
import CircleCloseFilled from '@/components/icons/CircleCloseFilled.vue';
import { reactive, ref, defineEmits } from 'vue'
import { ElMessage } from 'element-plus'
import type{ FormInstance, FormRules } from 'element-plus' 
import { useUsersStore } from '@/stores/users'; 
import type{ Data } from '@/stores/users';  
const usersStore = useUsersStore(); 
const emit = defineEmits(['back'])
const authArr = [
    {
        value: 1,
        label: 'total',
        children: [
            {
                value: 2,
                label: 'Historical measurement data'
            },
            {
                value: 3,
                label: 'Real-time measurement data'
            },
            {
                value: 4,
                label: 'Measuring equipment management'
            }
        ]
    }
]
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<Data>({
    userName: '',
    password: '',
    authStrArr: '',
    // treeSelectVal: []
})
const rules = reactive<FormRules>({
  userName: [{ required: true, message: 'Please enter your account', trigger: 'change' }],
  password: [{ required: true, message: 'Please enter password', trigger: 'change' }],
//   treeSelectVal: [{ required: true, message: 'Please select permissions', trigger: 'change' }]
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
            // ruleForm.authStrArr = (ruleForm.treeSelectVal as string[]).join(',')
            const res = await usersStore.insertDataAction(ruleForm)
            if(res){
                ElMessage.success(res.message);
                backToF(true)
                formEl.resetFields()
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
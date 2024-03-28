<template>
  <div class="home-header">
    <!-- clearfix <img class="home-header-logo" src="@/assets/images/logo.png" fit="contain"/> -->
    <div class="home-header-title">car</div>
    <div>
      <el-input
        v-model="searchVal"
        placeholder="Please enter the content"
        class="input-with-select"
      >
        <template #prepend>
          <el-select v-model="typeVal" placeholder="Type" style="width: 115px">
            <el-option label="car" value="0">
              <div class="option-class">
                <img src="@/assets/images/type_0.png" fit="cover"/>
                <span>car</span>
              </div>
            </el-option>
            <el-option label="Motorcycle" value="1">
              <div class="option-class">
                <img src="@/assets/images/type_1.png" fit="cover"/>
                <span>Motorcycle</span>
              </div>
            </el-option>
            <el-option label="Van" value="2">
              <div class="option-class">
                <img src="@/assets/images/type_2.png" fit="cover"/>
                <span>Van</span>
              </div>
            </el-option>
            <el-option label="Motorhome" value="3">
              <div class="option-class">
                <img src="@/assets/images/type_3.png" fit="cover"/>
                <span>Motorhome</span>
              </div>
            </el-option>
          </el-select>
        </template>
        <template #append>
          <el-button :icon="Search" />
        </template>
      </el-input>
    </div>
    <div class="home-header-right">
      <span class="home-header-welcome">Welcome！</span>
      <el-dropdown>
        <el-space class="home-header-space">
          <img class="home-user-logo" src="@/assets/images/user.png" fit="contain"/>
        {{ infos.userName }}
        </el-space>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="dialogFormVisible = true">Change password</el-dropdown-item>
            <el-dropdown-item v-if="infos.isAdmin === 1" @click="$router.push('/user')">User management</el-dropdown-item>
            <el-dropdown-item @click="handleLogout">logout</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
    
  </div>
  <el-dialog v-model="dialogFormVisible" width="400px" title="Change password" center draggable>
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="70px"
        class="content-add-class"
        >
        <el-form-item label="pwd" prop="password">
            <el-input
            v-model="ruleForm.password"
            type="text" 
            placeholder="Please enter your password"
            />
        </el-form-item>
        <el-form-item label="New " prop="newPassword">
            <el-input
            v-model="ruleForm.newPassword"
            type="text"
            placeholder="Please enter a new password"
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
import { getCurrentInstance, onMounted, reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { Search } from '@element-plus/icons-vue' 
import type { FormInstance, FormRules } from 'element-plus';
import { useRouter } from 'vue-router';
import { useUsersStore } from '@/stores/users'
import { storeToRefs } from 'pinia'
const { appContext } = getCurrentInstance() as any;
const router = useRouter();
const usersStore = useUsersStore()
const { infos, platVersion: platVersionOld } = storeToRefs(usersStore)
const platVersionNew = appContext.config.globalProperties.$platVersion 
onMounted(() => {
  // Check whether it is the latest version
  if(platVersionOld.value !== platVersionNew) {
    handleLogout()
  }
})
const searchVal = ref('')
const typeVal = ref('')
const dialogFormVisible = ref(false)
interface Data { 
  password : string
  newPassword : string
}
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<Data>({
  password: '',
  newPassword: ''
})
const rules = reactive<FormRules>({
  password: [{ required: true, message: 'Please enter your password', trigger: 'change' }],
  newPassword: [{ required: true, message: 'Please enter a new password', trigger: 'change' }]
})
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate(async(valid) => {
    if (valid) {
      const res = await usersStore.resetPwdAction(ruleForm)
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
// const resetForm = (formEl: FormInstance | undefined) => {
//   if (!formEl) return
//   dialogFormVisible.value = false
//   formEl.resetFields()
// }
const handleLogout = () => {
  usersStore.clearSessionID()
  const plat = import.meta.env.VITE_NODE_BASE_URL
  setTimeout(()=>{
    window.location.replace(`${plat}login`)
  }, 500)
}
</script>

<style scoped lang="scss">
.home-header{
  width: 100%;
  font-size: 16px;
  display: flex;
  align-items: center;
  height: 100%;
  line-height: 50px;
  justify-content: space-between;
  .home-header-logo {
    height: 30px;
    margin: 10px 0;
    float: left;
  }
  .home-header-right{
    // float: right;
    .home-header-space{
      margin-left: 10px;
      &:hover{
          cursor: pointer;
      }
      .home-user-logo{
        height: 30px;
      }
    }
    .el-dropdown{
      color: #fff;
      line-height: 50px;
    }
  }
  .home-header-title {
    // width: 100%;
    // text-align: left;
    font-size: 20px;
    letter-spacing: 2px;
    // margin-left: 200px;
    // margin-right: 200px;
    box-sizing: content-box;
  } 
  
}
.content-add-class{
    width: 90%;
    margin: 0 auto;
    margin-top: 20px;
}
.option-class{
  display: flex;
  img{
    width: 30px; 
    height: 30px;
    margin-right: 20px;
  }
}
</style>
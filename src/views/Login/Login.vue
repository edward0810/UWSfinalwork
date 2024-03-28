<template>
  <div class="page">
    <div class="login">
      <div class="main">
        <span v-if="register" class="main-title">register</span>
        <img v-else style="width: 47%;" src="@/assets/images/title.png" fit="contain"/>
        <el-form
          ref="ruleFormRef"
          :model="ruleForm"
          :rules="rules"
          label-width=""
          class="login-form"
          >
          <el-form-item label="" prop="userName">
            <el-input
              v-model="ruleForm.userName"
              type="text"
              placeholder="InPut account"
            >
              <template #prefix>
                <img class="input-img-class" src="@/assets/images/userName.png" fit="contain" />
              </template>
            </el-input>
          </el-form-item>
          <el-form-item label="" prop="password">
            <el-input
              v-model="ruleForm.password"
              type="password"
              placeholder="please enter the password"
              show-password
              @keyup.enter="submitForm(ruleFormRef)"
            >
              <template #prefix>
                <img class="input-img-class" src="@/assets/images/password.png" fit="contain" />
              </template>
            </el-input>
          </el-form-item>
          <div v-if="register" @click="submitForm(ruleFormRef)" class="btn-img-class register-btn">register</div>
          <img v-else @click="submitForm(ruleFormRef)" class="btn-img-class" src="@/assets/images/login-btn.png" fit="contain" />
        </el-form>
        <div v-if="register" class="register-class" @click="register = false">return</div>
        <div v-else class="register-class" @click="register = true">register account</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, getCurrentInstance, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import type { FormInstance, FormRules } from 'element-plus'
import { useRouter } from 'vue-router';
import { useUsersStore } from '@/stores/users'

const { appContext } = getCurrentInstance() as any;

const router = useRouter();
const usersStore = useUsersStore()

const register = ref(false)

interface User {
  userName: string
  password: string
}
onMounted(()=> {
  let loading = document.getElementById('preloader');
    if (loading !== null) {
       document.body.removeChild(loading);
    }
})
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<User>({
  userName: '',
  password: '',
})
const rules = reactive<FormRules>({
  userName: [{ required: true, message: 'Please enter your account', trigger: 'change' }],
  password: [{ required: true, message: 'Please enter your password', trigger: 'change' }],
})
const platVersion = appContext.config.globalProperties.$platVersion

// console.log('platVersion==',platVersion)
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate(async(valid) => {
    if (valid) {
      if(register.value){
        const res = await usersStore.regUserAction(ruleForm)
        if(res){
          ElMessage.success(res.message);
          register.value = false;
          formEl.resetFields()
        }
      } else {
        const res = await usersStore.loginAction(ruleForm)
        if(res){
          usersStore.updatePlatVersion(platVersion);
          usersStore.updateSessionID(res.data.sessionID);
          usersStore.updateInfos({
            userName: ruleForm.userName,
            isAdmin: res.data.isAdmin,
            authStrArr: [1,2,3,4,5,6,7],
            // authStrArr: res.data.isAdmin===1?[1,2,3,4,5,6,7]:res.data.authStrArr,
            // authStrArr: res.data.authStrArr,
            authStr: res.data.authStr
          });
          router.push('/');
          ElMessage.success(res.message);
        }
      }
    } else {
      console.log('error submit!')
      return false
    }
  })
}
</script>

<style scoped lang="scss">
.page {
  width: 100vw;
  height: 100vh;
  background: url('@/assets/images/bg.png') no-repeat center;
  background-size: 100% 100%;
  position: relative;  
  .login {
    // width: 41%;
    // height: 59%;
    width: 590px;
    height: 450px;
    background: #fff;
    box-shadow: 0 0 4px 1px rgb(0 51 153 / 60%);
    border-radius: 30px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    animation: fadeIn 1.5s ease-in-out;
    .main{
      width: 58%;
      text-align: center;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      .main-title{
        font-size: 40px;
        font-weight: bold;
        letter-spacing: 3px;
        background: linear-gradient(to right, #357EEF,#357EEF,#348BF6, #4DB3FD);
        -webkit-background-clip: text;
        color: transparent;
      }
      .register-btn{
        background: linear-gradient(to right, #357EEF, #4DB3FD);
        height: 54px;
        border-radius: 30px;
        line-height: 54px;
        font-size: 26px;
        color: #fff;
      }
      .login-form{
        margin-top: 26px;
        .el-input{
          // width: 50% !important;
          height: 50px;
          margin-top: 14px;
          // border-radius: 25px !important;
          .input-img-class{
            width: 26px;
            height: 26px;
            margin: 0 18px;
          }
        }
        .btn-img-class{
          width: 100%;
          margin-top: 30px;
        }
        .el-button{
          width: 100%;
          padding: 0 !important;
          margin-top: 40px;
          .btn-img-class{
            width: 100%;
          }
          
        }
      }
    }
  } 
}
.register-class{
  cursor: pointer;
  text-decoration: underline;
  margin-top: 10px;
  &:hover{
    color: #1c53d9;
  }
}
@keyframes fadeIn{
    0%{
        transform: translate(-50%, -80%);
        opacity: 0;
    }
    70%{
        transform: translate(-50%, -49%);
        opacity: 0.7;
    }
    80%{
        transform: translate(-50%, -51%);
        opacity: 0.8;
    }
    90%{
        transform: translate(-50%, -49.5%);
        opacity: 0.9;
    }
    100%{
        transform: translate(-50%, -50%);
        opacity: 1;
    }
}
@-webkit-keyframes fadeIn{
    0%{
        transform: translate(-50%, -80%);
        opacity: 0;
    }
    70%{
        transform: translate(-50%, -49%);
        opacity: 0.7;
    }
    80%{
        transform: translate(-50%, -51%);
        opacity: 0.8;
    }
    90%{
        transform: translate(-50%, -49.5%);
        opacity: 0.9;
    }
    100%{
        transform: translate(-50%, -50%);
        opacity: 1;
    }
}
@-moz-keyframes fadeIn{
    0%{
        transform: translate(-50%, -80%);
        opacity: 0;
    }
    70%{
        transform: translate(-50%, -49%);
        opacity: 0.7;
    }
    80%{
        transform: translate(-50%, -51%);
        opacity: 0.8;
    }
    90%{
        transform: translate(-50%, -49.5%);
        opacity: 0.9;
    }
    100%{
        transform: translate(-50%, -50%);
        opacity: 1;
    }
}
</style>

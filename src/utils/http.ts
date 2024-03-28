import axios from 'axios'
import type { AxiosRequestConfig, AxiosResponse } from 'axios'
import { useUsersStore } from '@/stores/users';
import { storeToRefs } from 'pinia';
import { ElMessage } from 'element-plus';
import router from '@/router';
const env = import.meta.env 

const instance = axios.create({
  baseURL: env.VITE_NODE_INTERFACE,
  timeout: 5000
});

instance.interceptors.request.use(function (config) {
  if(config.headers){
    const usersStore = useUsersStore()
    const {sessionID} = storeToRefs(usersStore)
    config.headers.J_SESSIONID = sessionID.value;
  }
  return config;
}, function (error) {
  return Promise.reject(error);
});

instance.interceptors.response.use(function (response) {
  if(response.data.code === 1){
    const usersStore = useUsersStore()
    usersStore.clearSessionID()
    ElMessage.error(response.data.message);
    // ElMessage.error('重新登录');
    const plat = env.VITE_NODE_BASE_URL
    setTimeout(()=>{
      window.location.replace(`${plat}login`);
    }, 1000)
  } else if(response.data.code === 2){
    ElMessage.error(response.data.message);
    // ElMessage.error('暂无此权限');
    setTimeout(()=>{
      router.push('/500');
    }, 2000)
  } else if (!response.data.success){
    ElMessage.error(response.data.message);
  } else {
    return response.data;
  }
  return false;
}, function (error) {
  return Promise.reject(error);
});

const instanceE = axios.create({
  baseURL: env.VITE_NODE_INTERFACE,
  timeout: 5000
});

instanceE.interceptors.request.use(function (config) {
  if(config.headers){
    const usersStore = useUsersStore()
    const {sessionID} = storeToRefs(usersStore)
    config.headers.J_SESSIONID = sessionID.value;
    config['responseType'] = 'blob'
  }
  return config;
}, function (error) {
  return Promise.reject(error);
});

instanceE.interceptors.response.use(function (response) {
  if(response.data.code === 1){
    const usersStore = useUsersStore()
    usersStore.clearSessionID()
    ElMessage.error(response.data.message);
    // ElMessage.error('重新登录');
    const plat = env.VITE_NODE_BASE_URL
    setTimeout(()=>{
      window.location.replace(`${plat}login`);
    }, 1000)
  } else if(response.data.code === 2){
    ElMessage.error(response.data.message);
    // ElMessage.error('暂无此权限');
    setTimeout(()=>{
      router.push('/500');
    }, 2000)
  } else if (response.headers["content-disposition"]){
    return response
  } else {
    const msg = response.data.message
    ElMessage.error((msg && msg.trim().length>0)?msg:'暂无可导出数据')
  } 
  // else if (!response.data.success){
  //   ElMessage.error(response.data.message);
  // } else {
  //   return response.data;
  // }
  return false as any;
}, function (error) {
  return Promise.reject(error);
});

interface Data {
  fike?: string | Blob
  [index: string]: unknown
}

interface Http {
  get: (url: string, data?: Data, config?: AxiosRequestConfig) => Promise<AxiosResponse>
  post: (url: string, data?: Data, config?: AxiosRequestConfig) => Promise<AxiosResponse>
  postE: (url: string, data?: Data, config?: AxiosRequestConfig) => Promise<AxiosResponse>
  postU: (url: string, data?: any, config?: AxiosRequestConfig) => Promise<AxiosResponse>
  put: (url: string, data?: Data, config?: AxiosRequestConfig) => Promise<AxiosResponse>
  patch: (url: string, data?: Data, config?: AxiosRequestConfig) => Promise<AxiosResponse>
  delete: (url: string, data?: Data, config?: AxiosRequestConfig) => Promise<AxiosResponse>
}

const http: Http = {
  get(url, data, config){
    return instance.get(url, {
      params: data,
      ...config
    })
  },
  post(url, data, config){
    return instance.postForm(url, data, config)
    // return instance.post(url, data, config)
  },
  postE(url, data, config){
    return instanceE.postForm(url, data, config)
    // return instance.post(url, data, config)
  },
  postU(url, data, config){
    let newParams = new FormData();
    for(let i in data){
      newParams.append(i, data[i]);
    }
    return instance.postForm(url, data, config)
    // return instance.post(url, data, config)
  },
  put(url, data, config){
    return instance.put(url, data, config)
  },
  patch(url, data, config){
    return instance.patch(url, data, config)
  },
  delete(url, data, config){
    return instance.delete(url, {
      data,
      ...config
    })
  }
}

export default http;

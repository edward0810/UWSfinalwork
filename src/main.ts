import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'

// import ElementPlus from 'element-plus'
// import 'element-plus/dist/index.css'
// import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/theme-chalk/el-message.css'

const app = createApp(App)

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

app.config.globalProperties.$platVersion = "0.0.1"  //版本
app.config.globalProperties.$imgHttpPre = "http://112.124.17.70/CarServer/download/"
app.config.globalProperties.$imgTypeA = ['jpg', 'png']
app.config.globalProperties.$imgTypeLen = 200 //KB

// for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
//   app.component(key, component)
// }

// app.use(ElementPlus, { locale: zhCn })
app.use(pinia)
app.use(router)

app.mount('#app')

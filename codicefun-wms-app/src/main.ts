import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

import App from './app.vue'
import router from './router'

import pinia from './stores'
import 'element-plus/dist/index.css'
import 'normalize.css/normalize.css'

const app = createApp(App)

app
  .use(pinia)
  .use(router)
  .use(ElementPlus, { locale: zhCn })
  .mount('#app')

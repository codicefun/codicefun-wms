import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'

import App from './app.vue'
import router from './router'

import 'element-plus/dist/index.css'
import 'normalize.css/normalize.css'

const app = createApp(App)

app
  .use(router)
  .use(ElementPlus)
  .use(createPinia())
  .mount('#app')

import { createPinia } from 'pinia'
import piniaPersistedState from 'pinia-plugin-persistedstate'

const pinia = createPinia()

pinia.use(piniaPersistedState)

export default pinia
export { default as useSettingStore } from './modules/setting'
export { default as useUserStore } from './modules/user'

import { defineStore } from 'pinia'
import type { SettingState } from '@/stores/modules/types/type'

const useSettingStore = defineStore('setting', {
    state: (): SettingState => {
      return {
        isCollapse: false
      }
    },
    actions: {
      changeIsCollapse() {
        this.isCollapse = !this.isCollapse
      }
    },
  persist: true
  }
)

export default useSettingStore

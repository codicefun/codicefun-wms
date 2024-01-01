import { defineStore } from 'pinia'
import type { SettingState } from './types/type'

const useSettingStore = defineStore('setting', {
  state(): SettingState {
    return {
      isCollapse: false
    }
  },
  getters: {
    getIsCollapse: (state) => {
      return state.isCollapse
    }
  },
  actions: {
    changeIsCollapse() {
      this.isCollapse = !this.isCollapse
    }
  },
  persist: true
})

export default useSettingStore

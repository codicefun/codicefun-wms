import { defineStore } from 'pinia'
import type { UserState } from '@/stores/modules/types/type'

const userStore = defineStore('user', {
  state: (): UserState => {
    return {
      username: '',
      token: ''
    }
  },
  persist: true
})

export default userStore

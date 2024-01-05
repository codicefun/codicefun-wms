<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import type { LoginData } from '@/request/user/type'
import { reqLogin } from '@/request/user'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores'

const formData = ref<LoginData>({} as LoginData)
const router = useRouter()
const userStore = useUserStore()

const submit = async function() {
  const resp = await reqLogin(formData.value)

  if (resp.code === 200) {
    ElMessage({
      showClose: true,
      message: '登录成功',
      type: 'success'
    })
    userStore.username = formData.value.username
    userStore.token = resp.data.token
    await router.push('/')
  } else {
    ElMessage({
      showClose: true,
      message: `登录失败: {code: ${resp.code}, message: ${resp.message}}`,
      type: 'error'
    })
  }
}

const reset = function() {
  formData.value = {} as LoginData
}
</script>

<template>
  <div class="container">
    <h2>登录</h2>
    <el-form label-position="right"
             :model="formData"
             label-width="100">
      <el-form-item label="用户名">
        <el-input v-model="formData.username" type="text" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="formData.password" type="password" />
      </el-form-item>
      <div style="display: flex; justify-content: right">
        <el-button type="primary" @click="submit">登录</el-button>
        <el-button type="warning" @click="reset">重置</el-button>
      </div>
      <div>
        <el-button type="success" @click="router.push('/register')">没有账号？注册一个</el-button>
      </div>
    </el-form>
  </div>
</template>

<style scoped>
.container {
  max-width: 400px;
  margin: 200px auto auto;
}

.container h2 {
  text-align: center;
}
</style>

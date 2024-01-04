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
    <h2>Login to</h2>
    <h2>Warehouse Management System</h2>
    <el-form label-position="right"
             :model="formData"
             label-width="100">
      <el-form-item label="Username">
        <el-input v-model="formData.username" type="text" />
      </el-form-item>
      <el-form-item label="Password">
        <el-input v-model="formData.password" type="password" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">Submit</el-button>
        <el-button type="warning" @click="reset">Reset</el-button>
      </el-form-item>
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

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import type { LoginData } from '@/request/user/type'
import { reqRegister } from '@/request/user'
import { useRouter } from 'vue-router'

const formData = ref<LoginData>({} as LoginData)
const router = useRouter()

const submit = async function() {
  const resp = await reqRegister(formData.value)

  if (resp.code === 200) {
    ElMessage({
      showClose: true,
      message: '注册成功',
      type: 'success'
    })
    await router.push('/login')
  } else {
    ElMessage({
      showClose: true,
      message: `注册失败: {code: ${resp.code}, message: ${resp.message}}`,
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
    <h2>注册</h2>
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
        <el-button type="primary" @click="submit">注册</el-button>
        <el-button type="warning" @click="reset">重置</el-button>
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

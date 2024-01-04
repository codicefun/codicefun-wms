<script setup lang="ts">
import { useUserStore } from '@/stores'

const userStore = useUserStore()
</script>

<template>
  <h1>Hi {{ userStore.username }}, 欢迎来到仓库管理系统</h1>
</template>

<style scoped>

</style>

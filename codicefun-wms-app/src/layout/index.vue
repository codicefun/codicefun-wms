<script lang="ts" setup>
import { LayoutAside, LayoutFooter, LayoutHeader, LayoutMain } from './components'
</script>

<template>
  <el-container class="layout">
    <layout-aside />
    <!-- vertical when nested with el-header or el-footer; horizontal otherwise -->
    <el-container direction="vertical">
      <layout-header />
      <layout-main />
      <layout-footer />
    </el-container>
  </el-container>
</template>

<style scoped>
.layout {
  height: 100vh;
}
</style>

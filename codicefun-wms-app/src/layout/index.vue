<script lang="ts" setup>
import { LayoutAside, LayoutFooter, LayoutHeader, LayoutMain } from './components'
</script>

<template>
  <el-container class="layout-container-demo" style="height: 100vh">
    <el-aside width="200px">
      <layout-aside />
    </el-aside>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <layout-header />
      </el-header>
      <el-main>
        <layout-main />
      </el-main>
      <el-footer>
        <layout-footer />
      </el-footer>
    </el-container>
  </el-container>
</template>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  color: var(--el-text-color-primary);
  background-color: var(--el-color-primary-light-7);
}

.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}

.layout-container-demo .el-menu {
  border-right: none;
}

.layout-container-demo .el-main {
  padding: 0;
}

.layout-container-demo .toolbar {
  right: 20px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.layout-container-demo .el-footer {
  color: var(--el-text-color-primary);
  background-color: var(--el-color-primary-light-8);
}
</style>

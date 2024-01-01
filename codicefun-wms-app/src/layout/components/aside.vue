<script setup lang="ts">
import { Document, House, Setting } from '@element-plus/icons-vue'
</script>

<template>
  <el-aside>
    <el-scrollbar>
      <el-menu active-text-color="#ffd04b"
               background-color="#545c64"
               default-active="1"
               text-color="#fff">
        <el-menu-item index="1">
          <el-icon>
            <house />
          </el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon>
            <document />
          </el-icon>
          <span>导航二</span>
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon>
            <setting />
          </el-icon>
          <span>导航三</span>
        </el-menu-item>
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<style scoped>
.el-aside {
  background-color: #545c64;
}

.el-menu {
  border-right: none;
}
</style>

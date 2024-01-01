<script lang="ts" setup>
import { Document, House, Setting } from '@element-plus/icons-vue'
import { useSettingStore } from '@/stores'

const settingStore = useSettingStore()
</script>

<template>
  <el-aside>
    <el-scrollbar>
      <el-menu :collapse="settingStore.getIsCollapse"
               active-text-color="#ffd04b"
               background-color="#545c64"
               default-active="1"
               ollapse-transition="true"
               text-color="#fff">
        <el-menu-item index="1">
          <el-icon>
            <house />
          </el-icon>
          <template #title>首页</template>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon>
            <document />
          </el-icon>
          <template #title>导航二</template>
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon>
            <setting />
          </el-icon>
          <template #title>导航三</template>
        </el-menu-item>
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<style scoped>
.el-aside {
  width: auto; /* default 300px */
  background-color: #545c64;
}

.el-menu {
  border-right: none;
}

.el-menu:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>

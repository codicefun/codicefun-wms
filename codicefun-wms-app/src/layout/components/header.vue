<script lang="ts" setup>
import { ArrowDown, Expand, Fold } from '@element-plus/icons-vue'
import { useSettingStore } from '@/stores'

const settingStore = useSettingStore()

const goUserCenter = () => {
  console.log('user center')
}

const logout = () => {
  console.log('logout')
}
</script>

<template>
  <el-header>
    <el-row style="height: 100%">
      <el-col :span="4" class="grid" style="justify-content: left">
        <el-icon>
          <expand v-if="settingStore.isCollapse" @click="settingStore.changeIsCollapse()" />
          <fold v-else @click="settingStore.changeIsCollapse()" />
        </el-icon>
      </el-col>
      <el-col :span="16" class="grid" style="justify-content: center">
        仓库管理系统
      </el-col>
      <el-col :span="4" class="grid" style="justify-content: right">
        <el-dropdown size="large">
        <span class="el-dropdown-link">
          Admin
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="goUserCenter">用户中心</el-dropdown-item>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>
  </el-header>
</template>

<style scoped>
.grid {
  display: inline-flex;
  align-items: center;
  height: 100%;
}

.el-icon {
  margin-top: 1px;
  margin-left: 8px;
}

.el-header {
  position: relative;
  text-align: right;
  color: var(--el-text-color-primary);
  background-color: var(--el-color-primary-light-7);
}

.el-dropdown-link {
  display: flex;
  align-items: center;
  cursor: pointer;
}
</style>

<script lang="ts" setup>
import { Document, House, Setting } from '@element-plus/icons-vue'
import { useSettingStore } from '@/stores'
import { useRouter } from 'vue-router'

const settingStore = useSettingStore()
const router = useRouter()
</script>

<template>
  <el-aside>
    <el-scrollbar>
      <el-menu :collapse="settingStore.isCollapse"
               active-text-color="#ffd04b"
               background-color="#545c64"
               default-active="1"
               ollapse-transition="true"
               text-color="#fff">
        <el-menu-item index="1" @click="router.push('/')">
          <el-icon>
            <house />
          </el-icon>
          <template #title>首页</template>
        </el-menu-item>
        <el-menu-item index="2" @click="router.push('/user')">
          <el-icon>
            <document />
          </el-icon>
          <template #title>用户管理</template>
        </el-menu-item>
        <el-menu-item index="3" @click="router.push('/warehouse')">
          <el-icon>
            <setting />
          </el-icon>
          <template #title>仓库管理</template>
        </el-menu-item>
        <el-menu-item index="4" @click="router.push('/goods/type')">
          <el-icon>
            <setting />
          </el-icon>
          <template #title>物品分类管理</template>
        </el-menu-item>
        <el-menu-item index="5" @click="router.push('/goods')">
          <el-icon>
            <setting />
          </el-icon>
          <template #title>物品管理</template>
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

<script setup lang="ts">
import { ref } from 'vue'
import { reqList } from '@/request/user'
import { ElMessage } from 'element-plus'
import type { User } from '@/request/user/type'

const tableData = ref<User[]>()
const current = ref<number>()
const size = ref<number>()
const total = ref<number>()
const small = ref(false)
const background = ref(false)
const disabled = ref(false)

const handleCurrentChange = (val: number) => {
  reqList({
    params: { current: val, size: size.value }
  }).then(resp => {
    if (resp.code === 200) {
      current.value = val
      tableData.value = resp.data.list
    }
  })
}

const handleSizeChange = (val: number) => {
  reqList({
    params: { current: current.value, size: val }
  }).then(resp => {
    if (resp.code === 200) {
      size.value = val
      tableData.value = resp.data.list
    }
  })
}

// Get user list
reqList().then(resp => {
  if (resp.code === 200) {
    if (resp.data.total === 0) {
      ElMessage({
        showClose: true,
        message: '没有数据',
        type: 'warning'
      })
    } else {
      current.value = resp.data.current
      size.value = resp.data.size
      total.value = resp.data.total
      tableData.value = resp.data.list
    }
  }
})
</script>

<template>
  <el-scrollbar>
    <el-table size="large" :data="tableData" height="75vh" style="width: 100%;">
      <el-table-column prop="id" label="ID" width="100" />
      <el-table-column prop="username" label="用户名" width="100" />
      <el-table-column prop="nickname" label="昵称" width="100" />
      <el-table-column prop="age" label="年龄" width="100" />
      <el-table-column prop="sex" label="性别" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.sex === 1 ? '' : 'danger'">
            {{ scope.row.sex === 1 ? '男' : '女' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号码" width="120" />
      <el-table-column prop="role" label="角色" width="120">
        <template #default="scope">
          {{ scope.row.role === 0 ? '超级管理员' : (scope.row.role === 1 ? '管理员' : '普通用户') }}
        </template>
      </el-table-column>
      <el-table-column prop="isValid" label="是否有效">
        <template #default="scope">
          <el-tag :type="scope.row.isValid === 'Y' ? 'success' : 'danger'">
            {{ scope.row.isValid === 'Y' ? '有效' : '无效' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button link type="primary">编辑</el-button>
          <el-button link type="warning">
            {{ scope.row.isValid === 'Y' ? '封禁' : '解封' }}
          </el-button>
          <el-button link type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-scrollbar>
  <div class="demo-pagination-block">
    <el-pagination
      :current-page="current"
      :page-size="size"
      :page-sizes="[5, 10, 20]"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
    />
  </div>
</template>

<style scoped>
.el-scrollbar {
  height: auto;
}

.demo-pagination-block {
  margin-top: 10px;
}

.demo-pagination-block {
  margin-bottom: 16px;
}
</style>

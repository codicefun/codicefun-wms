<script setup lang="ts">
import { ref } from 'vue'
import { reqDeleteById, reqTypeList } from '@/request/record'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Record } from '@/request/record/type'
import moment from 'moment'

const tableData = ref<Record[]>()

const current = ref<number>()
const size = ref<number>()
const total = ref<number>()

const updateData = async () => {
  const resp = await reqTypeList({ params: { current: current.value, size: size.value } })

  if (resp.code === 200) {
    total.value = resp.data.total
    tableData.value = resp.data.list
  }
}

const handleCurrentChange = async (val: number) => {
  const resp = await reqTypeList({ params: { current: val, size: size.value } })

  if (resp.code === 200) {
    current.value = val
    tableData.value = resp.data.list
  }
}

const handleSizeChange = async (val: number) => {
  const resp = await reqTypeList({ params: { current: current.value, size: val } })

  if (resp.code === 200) {
    size.value = val
    tableData.value = resp.data.list
  }
}

const deleteRow = async (id: number) => {
  try {
    await ElMessageBox.confirm(
      '确定要恢复这条记录?',
      'Warning',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    )
    const resp = await reqDeleteById(id)

    if (resp.code === 200) {
      await updateData()
      ElMessage({
        showClose: true,
        type: 'success',
        message: '恢复成功'
      })
    }
  } catch (e) {
    ElMessage({
      showClose: true,
      type: 'info',
      message: '恢复取消'
    })
  }
}

const dateFormat = (row: Record) => {
  const date = row.time

  return moment(date).format('YYYY-MM-DD HH:mm:ss')
}

// Get data in the beginning
reqTypeList().then(resp => {
  if (resp.code === 200) {
    current.value = resp.data.current
    size.value = resp.data.size
    total.value = resp.data.total
    tableData.value = resp.data.list
  }
})
</script>

<template>
  <el-scrollbar>
    <el-table size="large" :data="tableData" style="width: 100%;">
      <el-table-column prop="id" label="ID" width="100" />
      <el-table-column prop="operation" label="操作" width="100" />
      <el-table-column prop="warehouse" label="仓库" width="200" />
      <el-table-column prop="goods" label="货物" width="100" />
      <el-table-column prop="type" label="分类" width="100" />
      <el-table-column prop="amount" label="数量" width="100" />
      <el-table-column prop="username" label="用户" width="100" />
      <el-table-column prop="time" label="时间" :formatter="dateFormat" width="200" />
      <el-table-column fixed="right" label="操作" width="100">
        <template #default="{row}">
          <el-button link type="danger" @click="deleteRow(row.id)">恢复</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-scrollbar>
  <div class="demo-pagination-block">
    <el-pagination
      :current-page="current"
      :page-size="size"
      :page-sizes="[5, 10, 20]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
    />
  </div>
</template>

<style scoped>
.el-scrollbar {
  height: 70vh;
}

.demo-pagination-block {
  margin-top: 10px;
}

.demo-pagination-block {
  margin-bottom: 16px;
}
</style>

<script setup lang="ts">
import { ref } from 'vue'

const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles'
}

const tableData = ref(Array.from({ length: 5 }).fill(item))
</script>

<template>
  <el-scrollbar>
    <el-table :data="tableData">
      <el-table-column label="Date" prop="date" width="140" />
      <el-table-column label="Name" prop="name" width="120" />
      <el-table-column label="Address" prop="address" />
    </el-table>
  </el-scrollbar>
</template>

<style scoped>
</style>

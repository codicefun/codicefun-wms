<script setup lang="ts">
import { ref } from 'vue'

const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles'
}

const tableData = ref(Array.from({ length: 20 }).fill(item))
</script>

<template>
  <el-main>
    <el-scrollbar>
      <el-table :data="tableData">
        <el-table-column label="Date" prop="date" width="140" />
        <el-table-column label="Name" prop="name" width="120" />
        <el-table-column label="Address" prop="address" />
      </el-table>
    </el-scrollbar>
  </el-main>
</template>

<style scoped>
.el-main {
  padding: 0;
  height: 100%;
}
</style>

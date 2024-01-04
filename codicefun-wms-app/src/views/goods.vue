<script setup lang="ts">
import { ref } from 'vue'
import { reqCreate, reqDeleteById, reqList, reqUpdateById } from '@/request/goods'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Goods } from '@/request/goods/type'
import { reqTypeList } from '@/request/goodstype'
import { reqWarehouseList } from '@/request/warehouse'
import type { GoodsType } from '@/request/goodstype/type'
import type { Warehouse } from '@/request/warehouse/type'

const tableData = ref<Goods[]>()
const formData = ref<Goods>({} as Goods)
const formTitle = ref<string>()

const current = ref<number>()
const size = ref<number>()
const total = ref<number>()

const dialogVisible = ref(false)
const typeOption = ref<GoodsType[]>()
const warehouseOption = ref<Warehouse[]>()
const type = ref('')
const warehouse = ref('')

const updateData = async () => {
  const resp = await reqList({ params: { current: current.value, size: size.value } })

  if (resp.code === 200) {
    total.value = resp.data.total
    tableData.value = resp.data.list
  }
}

const handleCurrentChange = async (val: number) => {
  const resp = await reqList({ params: { current: val, size: size.value } })

  if (resp.code === 200) {
    current.value = val
    tableData.value = resp.data.list
  }
}

const handleSizeChange = async (val: number) => {
  const resp = await reqList({ params: { current: current.value, size: val } })

  if (resp.code === 200) {
    size.value = val
    tableData.value = resp.data.list
  }
}

const showEditDialog = (row: Goods) => {
  dialogVisible.value = true
  formData.value = { ...row }
  warehouse.value = formData.value.warehouse
  type.value = formData.value.type
  formTitle.value = '修改物品信息'
}

const edit = async () => {
  dialogVisible.value = false
  formData.value.warehouse = warehouse.value
  formData.value.type = type.value
  const resp = await reqUpdateById(formData.value.id, formData.value)

  if (resp.code === 200) {
    // Update table data
    await updateData()
    ElMessage({
      showClose: true,
      message: '修改成功',
      type: 'success'
    })
  }
}

const showCreateDialog = () => {
  dialogVisible.value = true
  formData.value = {} as Goods
  warehouse.value = ''
  type.value = ''
  formTitle.value = '新增物品信息'
}

const create = async () => {
  dialogVisible.value = false
  formData.value.warehouse = warehouse.value
  formData.value.type = type.value
  const resp = await reqCreate(formData.value)

  if (resp.code === 200) {
    // Update table data
    await updateData()
    ElMessage({
      showClose: true,
      message: '新增成功',
      type: 'success'
    })
  }
}

const deleteRow = async (id: number) => {
  try {
    await ElMessageBox.confirm(
      '确定要删除这行数据吗?',
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
        message: '删除成功'
      })
    }
  } catch (e) {
    ElMessage({
      showClose: true,
      type: 'info',
      message: '删除取消'
    })
  }
}

// Get data in the beginning
reqList().then(resp => {
  if (resp.code === 200) {
    current.value = resp.data.current
    size.value = resp.data.size
    total.value = resp.data.total
    tableData.value = resp.data.list
  }
})

reqTypeList({ params: { size: 100 } }).then(resp => {
  if (resp.code === 200) {
    typeOption.value = resp.data.list
  }
})
reqWarehouseList({ params: { size: 100 } }).then(resp => {
  if (resp.code === 200) {
    warehouseOption.value = resp.data.list
  }
})
</script>

<template>
  <el-row>
    <el-col :span="24">
      <el-button type="primary" @click="showCreateDialog">新增</el-button>
    </el-col>
  </el-row>
  <el-scrollbar>
    <el-table size="large" :data="tableData" style="width: 100%;">
      <el-table-column prop="id" label="ID" width="100" />
      <el-table-column prop="name" label="名称" width="100" />
      <el-table-column prop="warehouse" label="仓库" width="200" />
      <el-table-column prop="type" label="分类" width="100" />
      <el-table-column prop="amount" label="数量" width="100" />
      <el-table-column prop="description" label="描述" width="300" />
      <el-table-column fixed="right" label="操作" width="120">
        <template #default="{row}">
          <el-button link type="primary" @click="showEditDialog(row)">编辑</el-button>
          <el-button link type="danger" @click="deleteRow(row.id)">删除</el-button>
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
  <el-dialog
    v-model="dialogVisible"
    :title="formTitle"
    width="30%"
  >
    <el-form
      label-width="100px"
      :model="formData"
      style="max-width: 460px">
      <el-form-item label="名称">
        <el-input v-model="formData.name" />
      </el-form-item>
      <el-form-item label="仓库">
        <el-select v-model="warehouse" placeholder="请选择">
          <el-option v-for="item in warehouseOption" :key="item.name" :label="item.name" :value="item.name" />
        </el-select>
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="type" placeholder="请选择">
          <el-option v-for="item in typeOption" :key="item.name" :label="item.name" :value="item.name" />
        </el-select>
      </el-form-item>
      <el-form-item label="数量">
        <el-input type="number" v-model="formData.amount" />
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="formData.description" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button v-if="formTitle?.startsWith('修改')" type="primary" @click="edit">确认</el-button>
        <el-button v-else type="primary" @click="create">确认</el-button>
      </span>
    </template>
  </el-dialog>
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

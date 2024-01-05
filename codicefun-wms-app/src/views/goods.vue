<script setup lang="ts">
import { ref } from 'vue'
import { reqChangeAmount, reqCreate, reqDeleteById, reqList, reqUpdateById } from '@/request/goods'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Goods } from '@/request/goods/type'
import { reqTypeList } from '@/request/goodstype'
import { reqWarehouseList } from '@/request/warehouse'
import type { GoodsType } from '@/request/goodstype/type'
import type { Warehouse } from '@/request/warehouse/type'
import type { AmountForm } from '@/views/type'
import type { User } from '@/request/user/type'
import { reqUserList } from '@/request/user'

// Constants
const tableData = ref<Goods[]>()
const allFormData = ref<Goods>({} as Goods)
const allFormTitle = ref<string>()
const amountFormData = ref<AmountForm>({} as AmountForm)
const amountFormTitle = ref<string>()

const current = ref<number>()
const size = ref<number>()
const total = ref<number>()

const dialogVisible = ref(false)
const amountDialogVisible = ref(false)
const typeOption = ref<GoodsType[]>()
const warehouseOption = ref<Warehouse[]>()
const userOption = ref<User[]>()
const type = ref('')
const warehouse = ref('')
const user = ref<number>()

// Functions
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
  allFormData.value = { ...row }
  warehouse.value = allFormData.value.warehouse
  type.value = allFormData.value.type
  allFormTitle.value = '修改物品信息'
}

const edit = async () => {
  dialogVisible.value = false
  allFormData.value.warehouse = warehouse.value
  allFormData.value.type = type.value
  const resp = await reqUpdateById(allFormData.value.id, allFormData.value)

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
  allFormData.value = {} as Goods
  warehouse.value = ''
  type.value = ''
  allFormTitle.value = '新增物品信息'
}

const showIncreaseDialog = (row: Goods) => {
  amountDialogVisible.value = true
  amountFormTitle.value = '入库'
  allFormData.value = { ...row }
}

const showDecreaseDialog = (row: Goods) => {
  amountDialogVisible.value = true
  amountFormTitle.value = '出库'
  allFormData.value = { ...row }
}

const create = async () => {
  dialogVisible.value = false
  allFormData.value.warehouse = warehouse.value
  allFormData.value.type = type.value
  const resp = await reqCreate(allFormData.value)

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

const increase = async () => {
  amountDialogVisible.value = false
  const resp = await reqChangeAmount(allFormData.value.id, {
    amount: Number(amountFormData.value.amount) + Number(allFormData.value.amount),
    userid: user.value
  })

  if (resp.code === 200) {
    await updateData()
    ElMessage({
      showClose: true,
      message: '入库成功',
      type: 'success'
    })
  }
}

const decrease = async () => {
  const amount = Number(allFormData.value.amount) - Number(amountFormData.value.amount)

  if (amount < 0) {
    ElMessage({
      showClose: true,
      message: '库存不够',
      type: 'error'
    })

    return Promise.reject(new Error('库存不够'))
  }

  const resp = await reqChangeAmount(allFormData.value.id, {
    amount,
    userid: user.value
  })

  if (resp.code === 200) {
    amountDialogVisible.value = false
    await updateData()
    ElMessage({
      showClose: true,
      message: '出库成功',
      type: 'success'
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
reqUserList({ params: { size: 100, state: 0, role: 2 } }).then(resp => {
  if (resp.code === 200) {
    userOption.value = resp.data.list
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
    <el-table size="large" height="70vh" :data="tableData" style="width: 100%;">
      <el-table-column prop="id" label="ID" width="100" />
      <el-table-column prop="name" label="名称" width="100" />
      <el-table-column prop="warehouse" label="仓库" width="200" />
      <el-table-column prop="type" label="分类" width="100" />
      <el-table-column prop="amount" label="数量" width="100" />
      <el-table-column prop="loss" label="损耗" width="100" />
      <el-table-column prop="description" label="描述" width="300" />
      <el-table-column fixed="right" label="操作" width="220">
        <template #default="{row}">
          <el-button link type="primary" @click="showEditDialog(row)">编辑</el-button>
          <el-button link type="primary" @click="showIncreaseDialog(row)">入库</el-button>
          <el-button link type="primary" @click="showDecreaseDialog(row)">出库</el-button>
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
    :title="allFormTitle"
    width="30%"
  >
    <el-form
      label-width="100px"
      :model="allFormData"
      style="max-width: 460px">
      <el-form-item label="名称">
        <el-input v-model="allFormData.name" />
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
        <el-input type="number" v-model="allFormData.amount" />
      </el-form-item>
      <el-form-item label="损耗">
        <el-input type="number" v-model="allFormData.loss" />
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="allFormData.description" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button v-if="allFormTitle?.startsWith('修改')" type="primary" @click="edit">确认</el-button>
        <el-button v-else type="primary" @click="create">确认</el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog
    v-model="amountDialogVisible"
    :title="amountFormTitle"
    width="30%"
  >
    <el-form
      label-width="100px"
      :model="amountFormData"
      style="max-width: 460px">
      <el-form-item label="数量">
        <el-input type="number" v-model="amountFormData.amount" />
      </el-form-item>
      <el-form-item label="申请人">
        <el-select v-model="user" placeholder="请选择">
          <el-option v-for="item in userOption" :key="item.id" :label="item.username" :value="item.id" />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="amountDialogVisible = false">取消</el-button>
        <el-button v-if="amountFormTitle === '入库'" type="primary" @click="increase">确认</el-button>
        <el-button v-else type="primary" @click="decrease">确认</el-button>
      </span>
    </template>
  </el-dialog>
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

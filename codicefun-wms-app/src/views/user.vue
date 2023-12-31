<script setup lang="ts">
import { ref } from 'vue'
import { reqChangeState, reqCreate, reqDeleteById, reqUserList, reqUpdateById } from '@/request/user'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { User } from '@/request/user/type'

const tableData = ref<User[]>()
const formData = ref<User>({} as User)
const formTitle = ref<string>()

const current = ref<number>()
const size = ref<number>()
const total = ref<number>()
const dialogVisible = ref(false)

const updateData = async () => {
  const resp = await reqUserList({ params: { current: current.value, size: size.value } })

  if (resp.code === 200) {
    total.value = resp.data.total
    tableData.value = resp.data.list
  }
}

const handleCurrentChange = async (val: number) => {
  const resp = await reqUserList({ params: { current: val, size: size.value } })

  if (resp.code === 200) {
    current.value = val
    tableData.value = resp.data.list
  }
}

const handleSizeChange = async (val: number) => {
  const resp = await reqUserList({ params: { current: current.value, size: val } })

  if (resp.code === 200) {
    size.value = val
    tableData.value = resp.data.list
  }
}

const showEditDialog = (row: User) => {
  dialogVisible.value = true
  formData.value = { ...row }
  formTitle.value = '修改用户信息'
}

const edit = async () => {
  dialogVisible.value = false
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

const changeState = async (id: number) => {
  const resp = await reqChangeState(id)

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
  formData.value = {} as User
  formTitle.value = '新增用户信息'
}

const create = async () => {
  dialogVisible.value = false
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
reqUserList().then(resp => {
  if (resp.code === 200) {
    current.value = resp.data.current
    size.value = resp.data.size
    total.value = resp.data.total
    tableData.value = resp.data.list
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
      <el-table-column prop="username" label="用户名" width="100" />
      <el-table-column prop="nickname" label="昵称" width="100" />
      <el-table-column prop="age" label="年龄" width="100" />
      <el-table-column prop="sex" label="性别" width="100">
        <template #default="{row}">
          <el-tag :type="row.sex === 1 ? '' : 'danger'">
            {{ row.sex === 1 ? '男' : '女' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号码" width="120" />
      <el-table-column prop="role" label="角色" width="120">
        <template #default="{row}">
          {{ row.role === 0 ? '超级管理员' : (row.role === 1 ? '管理员' : '用户') }}
        </template>
      </el-table-column>
      <el-table-column prop="isValid" label="状态" width="100">
        <template #default="{row}">
          <el-tag :type="row.state === 0 ? 'success' : 'danger'">
            {{ row.state === 0 ? '正常' : '封禁' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{row}">
          <el-button link type="primary" @click="showEditDialog(row)">编辑</el-button>
          <el-button link type="warning" @click="changeState(row.id)">
            {{ row.state === 0 ? '封禁' : '解封' }}
          </el-button>
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
      <el-form-item label="用户名">
        <el-input v-model="formData.username" />
      </el-form-item>
      <el-form-item v-if="formTitle?.startsWith('新增')" label="密码">
        <el-input type="password" v-model="formData.password" />
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="formData.nickname" />
      </el-form-item>
      <el-form-item label="年龄">
        <el-input type="number" v-model="formData.age" />
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="formData.sex">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="手机号码">
        <el-input v-model="formData.phone" />
      </el-form-item>
      <el-form-item label="角色">
        <el-radio-group v-model="formData.role">
          <el-radio :label="0">超级管理员</el-radio>
          <el-radio :label="1">管理员</el-radio>
          <el-radio :label="2">用户</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="状态">
        <el-radio-group v-model="formData.state">
          <el-radio :label="0">正常</el-radio>
          <el-radio :label="1">封禁</el-radio>
        </el-radio-group>
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
  height: auto;
}

.demo-pagination-block {
  margin-top: 10px;
}

.demo-pagination-block {
  margin-bottom: 16px;
}
</style>

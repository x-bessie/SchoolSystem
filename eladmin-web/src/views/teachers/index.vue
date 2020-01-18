<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <el-input v-model="query.value" clearable placeholder="输入搜索内容" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <el-select v-model="query.type" clearable placeholder="类型" class="filter-item" style="width: 130px">
          <el-option v-for="item in queryTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
        </el-select>
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="教师id" prop="userId">
            <el-input v-model="form.userId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="工号">
            <el-input v-model="form.teacherid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="form.sex" placeholder="请选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.phone" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="身份证">
            <el-input v-model="form.idnum" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column v-if="columns.visible('id')" prop="id" label="id" />
        <el-table-column v-if="columns.visible('userId')" prop="userId" label="教师id" /><el-table-column v-if="columns.visible('teacherid')" prop="teacherid" label="工号" />
        <el-table-column v-if="columns.visible('name')" prop="name" label="姓名" />
        <el-table-column v-if="columns.visible('age')" prop="age" label="年龄" />
        <el-table-column v-if="columns.visible('sex')" prop="sex" label="性别" />
        <el-table-column v-if="columns.visible('address')" prop="address" label="地址" />
        <el-table-column v-if="columns.visible('phone')" prop="phone" label="电话" />
        <el-table-column v-if="columns.visible('idnum')" prop="idnum" label="身份证" />
        <el-table-column v-permission="['admin','teachersInfo:edit','teachersInfo:del']" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudTeachersInfo from '@/api/teachersInfo'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: '教师：信息', url: 'api/teachersInfo', sort: 'id,desc', crudMethod: { ...crudTeachersInfo }})
const defaultForm = { id: null, userId: null, name: null, age: null, sex: null, address: null, phone: null, idnum: null }
export default {
  name: 'TeachersInfo',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin', 'teachersInfo:add'],
        edit: ['admin', 'teachersInfo:edit'],
        del: ['admin', 'teachersInfo:del']
      },
      rules: {
        userId: [
          { required: true, message: '教师id不能为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'userId', display_name: '教师id' },
        { key: 'name', display_name: '姓名' },
        { key: 'teacherid', display_name: '工号' }
      ]
    }
  },
  methods: {
    // 获取数据前设置好接口地址
    [CRUD.HOOK.beforeRefresh]() {
      const query = this.query
      if (query.type && query.value) {
        this.crud.params[query.type] = query.value
      }
      return true
    }
  }
}
</script>

<style scoped>

</style>

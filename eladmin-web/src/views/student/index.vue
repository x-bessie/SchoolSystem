<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <el-input
          v-model="query.value"
          clearable
          placeholder="输入搜索内容"
          style="width: 200px;"
          class="filter-item"
          @keyup.enter.native="crud.toQuery"
        />
        <el-select
          v-model="query.type"
          clearable
          placeholder="类型"
          class="filter-item"
          style="width: 130px"
        >
          <el-option
            v-for="item in queryTypeOptions"
            :key="item.key"
            :label="item.display_name"
            :value="item.key"
          />
        </el-select>
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog
        :close-on-click-modal="false"
        :before-close="crud.cancelCU"
        :visible.sync="crud.status.cu > 0"
        :title="crud.status.title"
        width="500px"
      >
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="学号" prop="username">
            <el-input v-model="form.username" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="form.sex" placeholder="请选择性别">
              <el-option label="男" value="男" />
              <el-option label="女" value="女" />
            </el-select>
          </el-form-item>
          <el-form-item label="专业" prop="profession">
            <el-input v-model="form.profession" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="身份证">
            <el-input v-model="form.idnum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="兴趣爱好" prop="hobby">
            <el-input v-model="form.hobby" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="导师" prop="guiderteacher">
            <el-input v-model="form.guiderteacher" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="家长姓名">
            <el-input v-model="form.parentname" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="家长电话">
            <el-input v-model="form.parentnum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="辅导员" prop="teachername">
            <el-input v-model="form.teachername" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.tellnum" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table
        ref="table"
        v-loading="crud.loading"
        :data="crud.data"
        size="small"
        style="width: 100%;"
        @selection-change="crud.selectionChangeHandler"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column
          v-if="columns.visible('username')"
          prop="username"
          label="学号"
          width="100px"
        />
        <el-table-column v-if="columns.visible('name')" prop="name" label="姓名" />
        <el-table-column v-if="columns.visible('sex')" prop="sex" label="性别" />
        <el-table-column v-if="columns.visible('profession')" prop="profession" label="专业" />
        <el-table-column v-if="columns.visible('idnum')" prop="idnum" label="身份证" />
        <el-table-column v-if="columns.visible('address')" prop="address" label="地址" />
        <el-table-column v-if="columns.visible('email')" prop="email" label="邮箱" />
        <el-table-column v-if="columns.visible('hobby')" prop="hobby" label="兴趣爱好" />
        <el-table-column v-if="columns.visible('parentname')" prop="parentname" label="家长姓名" />
        <el-table-column v-if="columns.visible('parentnum')" prop="parentnum" label="家长电话" />
        <el-table-column v-if="columns.visible('guiderteacher')" prop="guiderteacher" label="导师" />
        <el-table-column v-if="columns.visible('teachername')" prop="teachername" label="辅导员" />
        <el-table-column v-if="columns.visible('tellnum')" prop="tellnum" label="电话" />
        <el-table-column
          v-permission="['admin','studentInfo:edit','studentInfo:del']"
          label="操作"
          width="150px"
          align="center"
        >
          <template slot-scope="scope">
            <udOperation :data="scope.row" :permission="permission" />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudStudentInfo from '@/api/studentInfo'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import { isvalidPhone } from '@/utils/validate'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: '学生管理', url: 'api/studentInfo', sort: 'id,desc', crudMethod: { ...crudStudentInfo }})
const defaultForm = { name: null, sex: null, age: null, cid: null, idnum: null, address: null, email: null, hobby: null, userId: null, id: null, guiderTeacher: '陈四', parentName: null, parentNum: null, teacherName: null, tellNum: null }
export default {
  name: 'StudentInfo',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  data() {
    // 自定义验证
    // const validPhone = (rule, value, callback) => {
    //   if (!value) {
    //     callback(new Error('请输入电话号码'))
    //   } else if (!isvalidPhone(value)) {
    //     callback(new Error('请输入正确的11位手机号码'))
    //   } else {
    //     callback()
    //   }
    // }
    return {
      permission: {
        add: ['admin', 'studentInfo:add'],
        edit: ['admin', 'studentInfo:edit'],
        del: ['admin', 'studentInfo:del']
      },
      rules: {
        username: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        // email: [
        //   { required: true, message: '请输入邮箱地址', trigger: 'blur' },
        //   { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
        // ],
        name: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '性别不能为空', trigger: 'blur' }
        ],
        profession: [
          { required: true, message: '专业不能为空', trigger: 'blur' }
        ],
        guiderteacher: [
          { required: true, message: '导师不能为空', trigger: 'blur' }
        ],
        teachername: [
          { required: true, message: '辅导员不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'name', display_name: '姓名' },
        { key: 'profession', display_name: '专业' },
        { key: 'username', display_name: '学号' }
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

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
          <el-form-item label="课程id" prop="classId">
            <el-input v-model="form.classId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程代码" prop="classCode">
            <el-input v-model="form.classCode" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="教师id">
            <el-input v-model="form.teacherId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="教师名称">
            <el-input v-model="form.teacherName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="分数" prop="grade">
            <el-input v-model="form.grade" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程名称">
            <el-input v-model="form.courseName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="学号" prop="username">
            <el-input v-model="form.username" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="姓名" prop="studentName">
            <el-input v-model="form.studentName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="时间">
            <el-date-picker v-model="form.pdate" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="form.status" placeholder="状态">
              <el-option label="0 未激活" value="0" />
              <el-option label="1 激活" value="0" />
            </el-select>
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
        <el-table-column v-if="columns.visible('classId')" prop="classId" label="课程id" />
        <el-table-column v-if="columns.visible('classCode')" prop="classCode" label="课程代码" />
        <el-table-column v-if="columns.visible('teacherId')" prop="teacherId" label="教师id" />
        <el-table-column v-if="columns.visible('teacherName')" prop="teacherName" label="教师名称" />
        <el-table-column v-if="columns.visible('grade')" prop="grade" label="分数" />
        <el-table-column v-if="columns.visible('courseName')" prop="courseName" label="课程名称" />
        <el-table-column v-if="columns.visible('username')" prop="username" label="学号" />
        <el-table-column v-if="columns.visible('studentName')" prop="studentName" label="姓名" />
        <el-table-column v-if="columns.visible('pdate')" prop="pdate" label="时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.pdate) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('status')" prop="status" label="状态" />
        <el-table-column v-permission="['admin','gradeInfo:edit','gradeInfo:del']" label="操作" width="150px" align="center">
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
import crudGradeInfo from '@/api/gradeInfo'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: '学生成绩', url: 'api/gradeInfo', sort: 'id,desc', crudMethod: { ...crudGradeInfo }})
const defaultForm = { id: null, classId: null, classCode: null, teacherId: null, teacherName: null, grade: null, courseName: null, pdate: null, status: null }
export default {
  name: 'GradeInfo',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin', 'gradeInfo:add'],
        edit: ['admin', 'gradeInfo:edit'],
        del: ['admin', 'gradeInfo:del']
      },
      rules: {
        classId: [
          { required: true, message: '课程id不能为空', trigger: 'blur' }
        ],
        classCode: [
          { required: true, message: '课程代码不能为空', trigger: 'blur' }
        ],
        grade: [
          { required: true, message: '分数不能为空', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '学号不能为空', trigger: 'blur' }
        ],
        studentName: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'classCode', display_name: '课程代码' },
        { key: 'courseName', display_name: '课程名称' }
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

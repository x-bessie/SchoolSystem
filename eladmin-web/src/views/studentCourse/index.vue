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
          <!-- <el-form-item label="学生id" prop="studentId">
            <el-input v-model="form.studentId" style="width: 370px;" />
          </el-form-item> -->
          <el-form-item label="学号" prop="studentCode">
            <el-input v-model="form.studentCode" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="学生姓名" prop="studentName">
            <el-input v-model="form.studentName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程代码" prop="courseCode">
            <el-input v-model="form.courseCode" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程名称" prop="courseName">
            <el-input v-model="form.courseName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="时间">
            <el-date-picker v-model="form.pdate" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="学期">
            <el-select v-model="form.tearm" placeholder="请选择学期">
              <el-option label="1" value="1" />
              <el-option label="2" value="2" />
            </el-select>
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
        <el-table-column v-if="columns.visible('id')" prop="id" label="id" />
        <!-- <el-table-column v-if="columns.visible('studentId')" prop="studentId" label="学生id" /> -->
        <el-table-column v-if="columns.visible('studentCode')" prop="studentCode" label="学号" />
        <el-table-column v-if="columns.visible('studentName')" prop="studentName" label="学生姓名" />
        <el-table-column v-if="columns.visible('courseCode')" prop="courseCode" label="课程代码" />
        <el-table-column v-if="columns.visible('courseName')" prop="courseName" label="课程名称" />
        <el-table-column v-if="columns.visible('pdate')" prop="pdate" label="时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.pdate) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('tearm')" prop="tearm" label="学期" />
        <el-table-column
          v-permission="['admin','studentCourseInfo:edit','studentCourseInfo:del']"
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
import crudStudentCourseInfo from '@/api/studentCourseInfo'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: '课程：学生课程', url: 'api/studentCourseInfo', sort: 'id,desc', crudMethod: { ...crudStudentCourseInfo }})
const defaultForm = { studentId: null, studentCode: null, studentName: null, courseCode: null, courseName: null, pdate: null, tearm: null, id: null }
export default {
  name: 'StudentCourseInfo',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin', 'studentCourseInfo:add'],
        edit: ['admin', 'studentCourseInfo:edit'],
        del: ['admin', 'studentCourseInfo:del']
      },
      rules: {
        // studentId: [
        //   { required: true, message: '不能为空', trigger: 'blur' }
        // ],
        studentCode: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        studentName: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        courseCode: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        courseName: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ]

      },
      queryTypeOptions: [
        { key: 'studentCode', display_name: '学号' }
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

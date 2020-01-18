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
          <el-form-item label="课程名称" prop="name">
            <el-input v-model="form.name" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
            <el-date-picker v-model="form.createTime" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程时长" prop="classtime">
            <el-input v-model="form.classtime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="周数" prop="classNum">
            <el-input v-model="form.classNum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="任课教师" prop="classTeacher">
            <el-input v-model="form.classTeacher" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="教师Id" prop="teacherId">
            <el-input v-model="form.teacherId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程代码" prop="classCode">
            <el-input v-model="form.classCode" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="学期" prop="tearm">
            未设置字典，请手动设置 Select
          </el-form-item>
          <el-form-item label="学年" prop="schoolYear">
            <el-input v-model="form.schoolYear" style="width: 370px;" />
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
        <el-table-column v-if="columns.visible('name')" prop="name" label="课程名称" />
        <el-table-column v-if="columns.visible('createTime')" prop="createTime" label="创建时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('classtime')" prop="classtime" label="课程时长" />
        <el-table-column v-if="columns.visible('classNum')" prop="classNum" label="周数" />
        <el-table-column v-if="columns.visible('classTeacher')" prop="classTeacher" label="任课教师" />
        <el-table-column v-if="columns.visible('teacherId')" prop="teacherId" label="教师Id" />
        <el-table-column v-if="columns.visible('classCode')" prop="classCode" label="课程代码" />
        <el-table-column v-if="columns.visible('tearm')" prop="tearm" label="学期" />
        <el-table-column v-if="columns.visible('schoolYear')" prop="schoolYear" label="学年" />
        <el-table-column v-permission="['admin','courseInfo:edit','courseInfo:del']" label="操作" width="150px" align="center">
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
import crudCourseInfo from '@/api/courseInfo'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: '课程管理', url: 'api/courseInfo', sort: 'id,desc', crudMethod: { ...crudCourseInfo }})
const defaultForm = { id: null, name: null, createTime: null, classtime: null, classNum: null, classTeacher: null, teacherId: null, classCode: null, tearm: null, schoolYear: null }
export default {
  name: 'CourseInfo',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin', 'courseInfo:add'],
        edit: ['admin', 'courseInfo:edit'],
        del: ['admin', 'courseInfo:del']
      },
      rules: {
        name: [
          { required: true, message: '课程名称不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        classtime: [
          { required: true, message: '课程时长不能为空', trigger: 'blur' }
        ],
        classNum: [
          { required: true, message: '周数不能为空', trigger: 'blur' }
        ],
        classTeacher: [
          { required: true, message: '任课教师不能为空', trigger: 'blur' }
        ],
        teacherId: [
          { required: true, message: '教师Id不能为空', trigger: 'blur' }
        ],
        classCode: [
          { required: true, message: '课程代码不能为空', trigger: 'blur' }
        ],
        tearm: [
          { required: true, message: '学期不能为空', trigger: 'blur' }
        ],
        schoolYear: [
          { required: true, message: '学年不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'name', display_name: '课程名称' },
        { key: 'classCode', display_name: '课程代码' },
        { key: 'schoolYear', display_name: '学年' }
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

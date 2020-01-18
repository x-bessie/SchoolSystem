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
          <el-form-item label="studentId" prop="studentId">
            <el-input v-model="form.studentId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="courseId" prop="courseId">
            <el-input v-model="form.courseId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="pdate">
            <el-input v-model="form.pdate" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="tearm">
            <el-input v-model="form.tearm" style="width: 370px;" />
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
        <el-table-column v-if="columns.visible('studentId')" prop="studentId" label="studentId" />
        <el-table-column v-if="columns.visible('courseId')" prop="courseId" label="courseId" />
        <el-table-column v-if="columns.visible('pdate')" prop="pdate" label="pdate">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.pdate) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-if="columns.visible('tearm')" prop="tearm" label="tearm" />
        <el-table-column v-permission="['admin','studentCourseInfo:edit','studentCourseInfo:del']" label="操作" width="150px" align="center">
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
import crudStudentCourseInfo from '@/api/studentCourseInfo'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: '课程：学生课程', url: 'api/studentCourseInfo', sort: 'id,desc', crudMethod: { ...crudStudentCourseInfo }})
const defaultForm = { studentId: null, courseId: null, pdate: null, tearm: null, id: null }
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
        studentId: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        courseId: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'studentId', display_name: 'studentId' }
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

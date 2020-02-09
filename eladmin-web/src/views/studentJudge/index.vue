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
          <el-form-item label="学号" prop="username">
            <el-input v-model="form.username" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程id" prop="classId">
            <el-input v-model="form.classId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="教师id" prop="teacherId">
            <el-input v-model="form.teacherId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="教师名称" prop="teacherName">
            <el-input v-model="form.teacherName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="评价">
            <el-input v-model="form.memo" :rows="3" type="textarea" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间">
            <el-input v-model="form.createtime" style="width: 370px;" />
          </el-form-item>
          <!-- <el-form-item label="状态">
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item> -->
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <!-- <el-table-column v-if="columns.visible('id')" prop="id" label="id" /> -->
        <el-table-column v-if="columns.visible('username')" prop="username" label="学号" />
        <el-table-column v-if="columns.visible('classId')" prop="classId" label="课程id" />
        <el-table-column v-if="columns.visible('teacherId')" prop="teacherId" label="教师id" />
        <el-table-column v-if="columns.visible('teacherName')" prop="teacherName" label="教师名称" />
        <el-table-column v-if="columns.visible('memo')" prop="memo" label="评价" />
        <el-table-column v-if="columns.visible('createtime')" prop="createtime" label="创建时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createtime) }}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column v-if="columns.visible('status')" prop="status" label="状态" /> -->
        <el-table-column v-permission="['admin','commentInfo:edit','commentInfo:del']" label="操作" width="150px" align="center">
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
import crudCommentInfo from '@/api/commentInfo'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

// crud交由presenter持有
const defaultCrud = CRUD({ title: '教师评价', url: 'api/commentInfo', sort: 'id,desc', crudMethod: { ...crudCommentInfo }})
const defaultForm = { id: null, username: null, classId: null, teacherId: null, teacherName: null, memo: null, createtime: null, status: null }
export default {
  name: 'CommentInfo',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin', 'commentInfo:add'],
        edit: ['admin', 'commentInfo:edit'],
        del: ['admin', 'commentInfo:del']
      },
      rules: {
        username: [
          { required: true, message: '学号不能为空', trigger: 'blur' }
        ],
        classId: [
          { required: true, message: '课程id不能为空', trigger: 'blur' }
        ],
        teacherId: [
          { required: true, message: '教师id不能为空', trigger: 'blur' }
        ],
        teacherName: [
          { required: true, message: '教师名称不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'username', display_name: '学号' },
        { key: 'teacherName', display_name: '教师名称' }
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

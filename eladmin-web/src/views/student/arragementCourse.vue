<template>
  <div class="app-container">
    <!--工具栏-->
    <!--对话框表单-->
    <el-dialog title="对教师评价" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
      <span slot="footer" class="dialog-footer">
        <el-form
          ref="form"
          :model="teacherData"
          :inline="true"
          stripe
          size="small"
          style="width: 70%;"
        >
          <el-form-item label="课程名称">
            <el-input
              v-model="teacherData.course_name"
              :disabled="true"
              placeholder="课程名称"
              style="width: 300px;"
            />
          </el-form-item>
          <el-form-item label="授课教师">
            <el-input
              v-model="teacherData.class_teacher"
              :disabled="true"
              placeholder="授课教师"
              style="width: 300px;"
            />
          </el-form-item>
          <el-form-item label="评价">
            <el-input v-model="teacherData.memo" style="width: 300px;" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit(teacherData)">提交</el-button>
            <el-button @click="dialogVisible = false">关闭</el-button>
          </el-form-item>
        </el-form>
      </span>
    </el-dialog>
    <div class="head-container">
      <!--学生课程表表格渲染-->
      <el-table ref="table" :data="tableData" size="small" style="width: 100%;">
        <el-table-column prop="course_name" label="课程名称" width="180" />
        <el-table-column prop="classtime" label="课程时长(min)" width="180" />
        <el-table-column prop="class_num" label="课程时长周数" width="180" />
        <el-table-column prop="class_teacher" label="任课教师" width="180" />
        <el-table-column prop="class_code" label="课程代码" width="180" />
        <el-table-column prop="school_year" label="学年" width="180" />
        <el-table-column prop="tearm" label="学期" width="180" />
        <el-table-column label="操作" width="150">
          <template scope="scope">
            <el-button size="small" @click="judgeTeachers(scope.$index, scope.row)">对教师评价</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { getStudentCourseMessage } from '@/api/studentCourseInfo'
import { InsertCommentByStudent } from '@/api/commentInfo'
import moment from 'moment'

export default {
  data() {
    return {
      dialogVisible: false,
      // 课程数据
      tableData: [{
        username: '',
        course_name: '',
        course_id: '',
        classtime: '',
        class_num: '',
        class_teacher: '',
        teacher_id: '',
        class_code: '',
        school_year: '',
        tearm: '',
        create_time: ''
      }],
      // 弹窗数据
      teacherData: [{
        username: '',
        id: '',
        class_id: '',
        course_name: '',
        // name: '',
        // teacher_name: '',
        class_teacher: '',
        memo: ''
      }]
      // // 评价数据
      // commentData: {
      //   username: '',
      //   class_id: '',
      //   teacher_id: '',
      //   teacher_name: '',
      //   memo: ''
      // }
    }
  },
  mounted() {
    this.getarrangement()
  },
  methods: {
    getarrangement() {
      getStudentCourseMessage().then(res => {
        this.tableData = res
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => { })
    },
    judgeTeachers: function(index, row) {
      var _this = this
      _this.teacherData = row
      _this.dialogVisible = true
    },
    onSubmit(data) {
      // console.log(data)
      if (this.teacherData.memo) {
        InsertCommentByStudent({
          username: data.username,
          class_id: data.course_id,
          teacher_name: data.class_teacher,
          teacher_id: data.teacher_id,
          memo: data.memo
        }).then(res => {
          // console.log(res)
        })
      } else {
        this.$message.warning({
          message: '请输入评价',
          center: true,
          duration: 1000
        })
      }
    },
    dateFormat:
      function(row, column) {
        var date = row[column.property]
        if (date == undefined) {
          return ''
        }
        return moment(date).format('YYYY-MM-DD HH:mm:ss')
      }

  }
}
</script>

<style scoped>
</style>

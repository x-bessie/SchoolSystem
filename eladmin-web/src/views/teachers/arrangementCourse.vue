<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--对话框表单-->
      <el-dialog title="课程学生" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
        <span slot="footer" class="dialog-footer">
          <el-table ref="table" :data="studentData" stripe size="small" style="width: 100%;">
            <el-table-column prop="student_name" label="学生姓名" width="321" />
            <el-table-column prop="username" label="学号" width="321" />
          </el-table>
          <el-button @click="dialogVisible = false">关闭</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" :data="tableData" stripe size="small" style="width: 100%;">
        <el-table-column prop="name" label="课程名称" width="180" />
        <el-table-column prop="classtime" label="课程时长(min)" width="130" />
        <el-table-column prop="class_num" label="课程时长周数" width="130" />
        <el-table-column prop="class_code" label="课程代码" width="150" />
        <el-table-column prop="create_time" label="课程创建时间" width="180" :formatter="dateFormat" />
        <el-table-column prop="school_year" label="学年" width="180" />
        <el-table-column prop="tearm" label="学期" width="150" />
        <el-table-column label="操作" width="150">
          <template scope="scope">
            <el-button size="small" @click="seeStudent(scope.$index, scope.row)">查看学生</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import { getTeacherCourseMessage, getTeacherCourseToSeeStudent } from '@/api/teachersInfo'
import moment from 'moment'

export default {
  data() {
    return {
      dialogVisible: false,
      tableData: [{
        name: '数据结构',
        classtime: '120',
        class_num: '17',
        class_code: 'SF001',
        create_time: '2020-1-17',
        school_year: '2018-2019',
        tearm: '1',
        teacher_id: '',
        class_teacher: ''
      }],
      // 课程中的学生
      studentData: [{
        student_name: '',
        username: ''
      }]
    }
  },
  mounted() {
    this.getarrangement()
  },
  methods: {
    getarrangement() {
      getTeacherCourseMessage().then(res => {
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
    seeStudent(index, row) {
      this.dialogVisible = !this.dialogVisible

      getTeacherCourseToSeeStudent({
        teacher_id: row.teacher_id,
        class_code: row.class_code
      }).then(res => {
        this.studentData = res
      })
      // console.log(index, row)
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

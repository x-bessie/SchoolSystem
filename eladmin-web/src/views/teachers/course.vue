<template>
  <div class="app-container">
    <div class="head-container">
      <p style="color:#909399">请选择相应课程</p>
      <el-select
        v-model="options.name"
        clearable
        placeholder="请选择"
        @focus="getSelect"
        @change="getSlectForm(options.name)"
      >
        <el-option
          v-for="item in options"
          :key="item.name"
          :label="item.name"
          :value="item.class_code"
        />
      </el-select>
      <!--对话框表单-->
      <el-dialog title="成绩登记" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
        <span slot="footer" class="dialog-footer">
          <el-form
            ref="form"
            :model="studentGrade"
            :inline="true"
            stripe
            size="small"
            style="width: 70%;"
          >
            <el-form-item label="课程名称">
              <el-input
                v-model="studentGrade.name"
                :disabled="true"
                placeholder="课程名称"
                style="width: 300px;"
              />
            </el-form-item>
            <el-form-item label="学号">
              <el-input
                v-model="studentGrade.username"
                :disabled="true"
                placeholder="学号"
                style="width: 300px;"
              />
            </el-form-item>
            <el-form-item label="姓名">
              <el-input
                v-model="studentGrade.student_name"
                :disabled="true"
                placeholder="姓名"
                style="width: 300px;"
              />
            </el-form-item>
            <el-form-item label="成绩">
              <el-input v-model="studentGrade.grade" style="width: 300px;" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit(studentGrade)">提交</el-button>
              <el-button @click="dialogVisible = false">关闭</el-button>
            </el-form-item>
          </el-form>
        </span>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" :data="gradeStudentList" stripe size="small" style="width: 100%;">
        <el-table-column prop="username" label="学号" />
        <el-table-column prop="student_name" label="姓名" />
        <el-table-column prop="name" label="课程名称" />
        <el-table-column prop="class_code" label="课程代码" />
        <!-- <el-table-column prop="grade" label="分数" width="180" /> -->
        <el-table-column label="操作">
          <template scope="scope">
            <el-button size="small" @click="addGrade(scope.$index, scope.row)">评分</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { getTeacherCourse, InsertStudentGrade, getCourseGrade, updateStudentGrade } from '@/api/gradeInfo'
import { getTeacherCourseToSeeStudent } from '@/api/teachersInfo'
import moment from 'moment'

export default {
  data() {
    return {
      options: [],
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
      }],
      gradeStudentList: [{
        id: '',
        teacher_id: '',
        class_teacher: '',
        class_code: 'SF001',
        name: '示例数据',
        username: '1940129247',
        student_name: '蔡虚坤'
      }],
      studentGrade: [{
        id: '',
        class_code: '',
        // 教师id改为教师工号
        teacher_id: '',
        name: '',
        class_teacher: '',
        username: '',
        student_name: '',
        grade: '',
        status: ''
      }],
      oneStudentGradeList: []

    }
  },
  mounted() {
    this.getarrangement()
  },
  methods: {
    // select 选择器中的内容
    getSelect() {
      getTeacherCourse().then(res => {
        this.options = res
      })
    },
    // select 选择器选择后的请求接口
    getSlectForm(val) {
      getTeacherCourseToSeeStudent({
        class_code: val,
        teacher_id: this.tableData[0].teacher_id
      }).then(res => {
        this.gradeStudentList = res
      })
    },
    getarrangement() {
      getTeacherCourse().then(res => {
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
    addGrade: function(index, row) {
      var _this = this
      _this.studentGrade = row
      _this.dialogVisible = true
    },
    onSubmit(data) {
      // console.log(data)
      if (this.studentGrade.grade) {
        getCourseGrade({
          username: data.username,
          class_code: data.class_code
        }).then(res => {
          this.oneStudentGradeList = res[0]
          // 如果有数据
          if (res[0]) {
            updateStudentGrade({
              // class_id: data.id,
              class_code: data.class_code,
              teacher_id: data.teacher_id,
              teacher_name: data.class_teacher,
              grade: data.grade,
              course_name: data.name,
              username: data.username,
              student_name: data.student_name,
              status: '1',
              id: res[0].id
            }).then(res => {
              this.$message.success({
                message: '更新成功！',
                center: true,
                duration: 1000,
                onClose: () => {
                  this.dialogVisible = false
                }
              })
            })
          } else {
            InsertStudentGrade({
              // class_id: data.id,
              class_code: data.class_code,
              teacher_id: data.teacher_id,
              teacher_name: data.class_teacher,
              grade: data.grade,
              course_name: data.name,
              username: data.username,
              student_name: data.student_name,
              status: '1'
            }).then(res => {
              this.$message.success({
                message: '登记成功！',
                center: true,
                duration: 1000,
                onClose: () => {
                  this.dialogVisible = false
                }
              })
            })
          }
        })
      } else {
        this.$message.warning({
          message: '请输入成绩',
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

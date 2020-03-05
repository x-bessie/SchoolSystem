<template>
  <div class="app-container">
    <!--工具栏-->
    <!--对话框表单-->
    <el-dialog title="对教师评价" :visible.sync="dialogVisible" :before-close="handleClose">
      <span slot="footer" class="dialog-footer">
        <el-form
          ref="form"
          :model="teacherData"
          :inline="true"
          stripe
          size="small"
          style="width: 80%;"
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
              style="width: 300px"
            />
          </el-form-item>
          <el-form-item label="评价指标">
            <el-input
              type="textarea"
              :disabled="true"
              placeholder="1.备课充分，讲课认真，不随意掉停课，对学生要求严格。2.讲课内容准确熟练，教学重点难点突出。3.理论联系实际，能激发学生求职欲（专业技能熟练，示范准确，指导有力）4.教学语言精炼生动，教学仪态自然大方 5.注重启发，鼓励质疑，并给予思路引导"
              style="min-height: 140px; width:300px;"
              label-width="60px"
            />
          </el-form-item>
          <el-form-item label="评价">
            <!-- <el-input v-model="teacherData.memo" style="width: 300px;" /> -->
            <el-select v-model="teacherData.memo" placeholder="评价分数" style="width: 300px;">
              <el-option :label="90" :value="90" />
              <el-option :label="80" :value="80" />
              <el-option :label="70" :value="70" />
              <el-option :label="60" :value="60" />
              <el-option :label="50" :value="50" />
            </el-select>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="onSubmit(teacherData)">提交</el-button>
        <el-button @click="dialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
    <div class="head-container">
      <!--学生课程表表格渲染-->
      <el-table ref="table" :data="tableData" size="small" style="width: 100%;">
        <el-table-column prop="course_name" label="课程名称" style="180" />
        <el-table-column prop="classtime" label="课程时长(min)" style="150" />
        <el-table-column prop="class_num" label="课程时长周数" style="130" />
        <el-table-column prop="class_teacher" label="任课教师" style="180" />
        <el-table-column prop="class_code" label="课程代码" style="180" />
        <el-table-column prop="school_year" label="学年" style="180" />
        <el-table-column prop="tearm" label="学期" style="120" />
        <el-table-column label="操作" style="150">
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
import { InsertCommentByStudent, queryCommentByStudent } from '@/api/commentInfo'
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
        memo: '',
        status: ''
      }],
      // 评价数据
      commentData: {
      }
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
        queryCommentByStudent({
          class_id: data.course_id,
          teacher_name: data.class_teacher,
          status: '1'
        }).then(res => {
          console.log(res.length)
          if (res[0] && res[0].status === 1) {
            this.$message.warning({
              message: '已经提交评价，请勿再次提交',
              center: true,
              duration: 1000,
              onClose: () => {
                this.dialogVisible = false
              }
            })
          } else if (res.length === 0) {
            InsertCommentByStudent({
              username: data.username,
              class_id: data.course_id,
              teacher_name: data.class_teacher,
              teacher_id: data.teacher_id,
              memo: data.memo
            }).then(res => {
              // console.log(res)
              this.$message.success({
                message: '评价成功！',
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

<style scoped>
</style>

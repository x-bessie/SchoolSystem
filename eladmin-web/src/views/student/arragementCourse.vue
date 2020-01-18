<template>
  <div class="app-container">
    <!--工具栏-->
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
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import { getStudentCourseMessage } from '@/api/studentCourseInfo'
import moment from 'moment'

export default {
  data() {
    return {
      tableData: [{
        course_name: '',
        classtime: '',
        class_num: '',
        class_teacher: '',
        class_code: '',
        school_year: '',
        tearm: '',
        create_time: ''
      }]
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

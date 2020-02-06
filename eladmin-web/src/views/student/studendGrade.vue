<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--学生课程表表格渲染-->
      <el-table ref="table" :data="tableData" size="small" style="width: 100%;">
        <el-table-column prop="course_name" label="课程名称" width="321" />
        <el-table-column prop="teacher_name" label="授课教师" width="321" />
        <el-table-column prop="class_code" label="课程代码" width="321" />
        <el-table-column prop="grade" label="分数" />
      </el-table>
    </div>
  </div>
</template>
<script>
import { getCourseGradeByStudent } from '@/api/gradeInfo'
import moment from 'moment'

export default {
  data() {
    return {
      // 课程数据
      tableData: [{
        course_name: '示例数据',
        teacher_name: '示例数据',
        class_code: '示例数据',
        grade: '60'
      }]
    }
  },
  mounted() {
    this.getarrangement()
  },
  methods: {
    getarrangement() {
      getCourseGradeByStudent().then(res => {
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

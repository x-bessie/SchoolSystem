<template>
  <div class="app-container">
    <div class="head-container">
      <!--表格渲染-->
      <el-table ref="table" :data="tableData" stripe size="small" style="width: 100%;">
        <el-table-column prop="class_name" label="课程名称" />
        <el-table-column prop="class_id" label="课程代码" />
        <el-table-column prop="avg_memo" label="平均评分" />
      </el-table>
    </div>
  </div>
</template>

<script>
import { getTeachersGrade } from '@/api/teachersInfo'
import moment from 'moment'

export default {
  data() {
    return {
      tableData: [{
        class_name: '',
        class_id: '',
        avg_memo: ''
      }]
    }
  },
  mounted() {
    this.getarrangement()
  },
  methods: {
    getarrangement() {
      getTeachersGrade().then(res => {
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

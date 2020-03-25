<template>
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="create_time" label="日期" :formatter="dateFormat" />
    <el-table-column prop="title" label="标题" />
    <el-table-column prop="content" label="内容" />
  </el-table>
</template>

<script>
import { queryMessageInfo } from '@/api/messageInfo'
import moment from 'moment'

export default {
  data() {
    return {
      tableData: [{
        create_time: '2019-01-02',
        title: '系统公告',
        content: '系统正在更新中'
      }]
    }
  },
  mounted() {
    this.getMessageInfo()
  },
  methods: {
    getMessageInfo() {
      queryMessageInfo().then(res => {
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

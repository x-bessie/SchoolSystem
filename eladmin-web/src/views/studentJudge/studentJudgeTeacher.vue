<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--学生课程表表格渲染-->
      <el-table ref="table" :data="tableData" size="medium" style="width: 100%;">
        <el-table-column prop="username" label="学号" width="180" />
        <l-table-column prop="course_name" label="课程名称" width="180" />
        <el-table-column prop="teacherName" label="教师名称" width="180" />
        <el-table-column prop="memo" label="评价" width="180" />
        <el-table-column prop="createtime" label="创建时间" width="180" />
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudCommentInfo from '@/api/commentInfo'

export default {
  name: 'CommentInfo',
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
      form: [{
        username: '',
        course_Code: '',
        course_name: '',
        classtime: '',
        class_num: '',
        class_teacher: '',
        teacher_id: '',
        class_code: '',
        school_year: '',
        tearm: '',
        create_time: ''
      }]
    }
  },
  mounted() {
    // this.getarrangement()
  },
  methods: {
    // getarrangement() {
    //   getStudentCourseMessage().then(res => {
    //     this.tableData = res
    //   })
    // }
  }
}
</script>

<style scoped>
</style>

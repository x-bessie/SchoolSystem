<template>
  <div class="app-container">
    <!--工具栏-->
    <!-- <div class="head-container"> -->
      <!--表单组件-->
      <el-form ref="form" :model="form" size="small" label-width="80px">
          <el-form-item label="姓名">
            <el-input v-model="form.name" :disabled="true" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="性别">
            <el-input v-model="form.sex" placeholder="性别" :disabled="true" style="width: 370px;"/>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="班级">
            <el-input v-model="form.cid" :disabled="true" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="身份证">
            <el-input v-model="form.IDNum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="兴趣爱好">
            <el-input v-model="form.Hobby" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="导师">
            <el-input v-model="form.guider_teacher"  :disabled="true" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="辅导员">
            <el-input v-model="form.teacher_name" :disabled="true"  style="width: 370px;" />
          </el-form-item>
          <el-form-item label="家长姓名">
            <el-input v-model="form.parent_name" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="家长电话">
            <el-input v-model="form.parent_num" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.tell_num" style="width: 370px;" />
          </el-form-item>
          <!-- <el-form-item>
            <el-button type="primary" @click="onSubmit">修改</el-button>
            <el-button>取消</el-button>
          </el-form-item> -->
        </el-form>
    <!-- </div> -->
    <!-- <el-form-item label="活动区域" prop="region">
      <el-select v-model="ruleForm.region" placeholder="请选择活动区域" v-if="editStatus">
        <el-option label="区域一" value="shanghai"></el-option>
        <el-option label="区域二" value="beijing"></el-option>
      </el-select>
      <div v-else>是</div>
    </el-form-item> -->
  </div>
</template>

<script>
import { getStudentInfoByName } from '@/api/studentInfo'

export default {
  data() {
    return {
      rules: {
        age: [
          { required: true, message: '年龄不能为空', trigger: 'blur'},
          { type: 'number', message: '年龄必须为数字值', trigger: 'blur'}
        ],
        tell_num:[
          { required: true, message: '电话不能为空', trigger: 'blur'},
          { type: 'number', message: '电话必须为数字值', trigger: 'blur'}
        ],
        IDNum:[
          { required: true, message: '身份证不能为空', trigger: 'blur'}
        ],
        address:[
          { required: true, message: '地址不能为空', trigger: 'blur'}
        ],
        email:[
          { required: true, message: '邮箱不能为空', trigger: 'blur'},
          { type: 'email', message: '请输入正确的邮箱地址' , trigger: 'blur'}
        ],
        parent_name:[
          { required: true, message: '家长姓名不能为空', trigger: 'blur'},
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'}
        ],
        parent_num:[
          { required: true, message: '家长电话不能为空', trigger: 'blur'},
          { type: 'number', message: '家长电话必须为数字值', trigger: 'blur'}
        ]
      },
      form:{
        name: ""
      },
      editStatus: false
    }
  },
  mounted() {
    this.getStudentInfoByName()
  },
  methods: {
    getStudentInfoByName() {
      getStudentInfoByName().then(res => {
        this.form = res[0]
        console.log(this.form)
      })
    }

  }
}
</script>

<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--学生个人信息-->
      <!--表单组件-->
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.name" :disabled="true" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.sex" placeholder="性别" :disabled="true" style="width: 370px;" />
        </el-form-item>
        <!-- <el-form-item label="班级">
          <el-input v-model="form.cid" :disabled="true" style="width: 370px;" />
        </el-form-item> -->
        <el-form-item label="身份证" prop="IDNum">
          <el-input v-model="form.IDNum" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="兴趣爱好" prop="Hobby">
          <el-input v-model="form.Hobby" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="导师">
          <el-input v-model="form.guider_teacher" :disabled="true" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="辅导员">
          <el-input v-model="form.teacher_name" :disabled="true" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="家长姓名" prop="parent_name">
          <el-input v-model="form.parent_name" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="家长电话" prop="parent_num">
          <el-input v-model="form.parent_num" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="电话" prop="tell_num">
          <el-input v-model="form.tell_num" style="width: 370px;" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">修改</el-button>
          <el-button type="primary" @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- <el-form-item label="活动区域" prop="region">
      <el-select v-model="ruleForm.region" placeholder="请选择活动区域" v-if="editStatus">
      </el-select>
      <div v-else>是</div>
    </el-form-item>-->
  </div>
</template>

<script>
import { getStudentInfoByName, updateStudentInfos } from '@/api/studentInfo'
import { isvalidPhone } from '@/utils/validate'
export default {
  data() {
    // 自定义验证
    const validPhone = (rules, value, callback) => {
      console.log('ok')
      if (!value) {
        callback(new Error('请输入电话号码'))
      } else if (!isvalidPhone(value)) {
        callback(new Error('请输入正确的11位手机号码'))
      } else {
        callback()
      }
    }
    return {
      rules: {
        age: [
          { required: true, message: '年龄不能为空', trigger: 'blur' },
          { type: 'number', message: '年龄必须为数字值', trigger: 'blur' }
        ],
        tell_num: [
          { required: true, trigger: 'blur', validator: validPhone }
        ],
        IDNum: [
          { required: true, message: '身份证不能为空', trigger: 'blur' },
          { min: 18, max: 18, message: '请输入正确身份证号码', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '地址不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
        ],
        parent_name: [
          { required: true, message: '家长姓名不能为空', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        parent_num: [
          { required: true, trigger: 'blur', validator: validPhone }
        ]
      },
      form: {
        id: '',
        name: '',
        sex: '',
        cid: '',
        IDNum: '',
        address: '',
        email: '',
        Hobby: '',
        guider_teacher: '',
        teacher_name: '',
        parent_name: '',
        parent_num: '',
        tell_num: '',
        user_id: '',
        username: ''
      },
      editStatus: false
    }
  },
  mounted() {
    this.getStudentInfoByName()
  },
  created() {
    this.getStudentInfoByName()
  },
  methods: {
    cancel() {
      this.resetForm()
      this.getStudentInfoByName()
    },
    getStudentInfoByName() {
      getStudentInfoByName().then(res => {
        this.form = res[0]
        // console.log(this.form)
      })
    },
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          updateStudentInfos({
            IDNum: this.form.IDNum,
            address: this.form.address,
            email: this.form.email,
            Hobby: this.form.Hobby,
            parent_name: this.form.parent_name,
            parent_num: this.form.parent_num,
            tell_num: this.form.tell_num,
            username: this.form.username
          }).then(res => {
            // this.editSuccessNotify()
          })
          alert('编辑成功!')
          this.getStudentInfoByName()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    editSuccessNotify() {
      this.$notify({
        title: '编辑成功',
        type: 'success',
        duration: 2500
      })
      this.getStudentInfoByName()
    },
    resetForm() {
      this.$refs['form'].resetFields()
      // this.form = {
      //   Idnum: '',
      //   address: '',
      //   phone: ''
      // }
    }
  }
}
</script>

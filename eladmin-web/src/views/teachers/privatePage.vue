<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--教师个人信息-->
      <!--表单组件-->
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.name" :disabled="true" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.sex" placeholder="性别" :disabled="true" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="身份证" prop="Idnum">
          <el-input v-model="form.Idnum" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" style="width: 370px;" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">修改</el-button>
          <el-button type="primary" @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { getTeachersInfoByUserName, updateTeacherInfo } from '@/api/teachersInfo'
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
        Idnum: [
          { required: true, message: '身份证不能为空', trigger: 'blur' },
          { min: 18, max: 18, message: '请输入正确身份证号码', trigger: 'blur' }
        ],
        phone: [
          { required: true, trigger: 'blur', validator: validPhone }
        ],
        address: [
          { required: true, message: '地址不能为空', trigger: 'blur' }
        ]

      },
      form: {
        id: '',
        name: '',
        sex: '',
        age: '',
        Idnum: '',
        address: '',
        phone: '',
        teacherid: '',
        user_id: ''
      },
      editStatus: false
    }
  },
  mounted() {
    this.getTeachersInfo()
  },
  created() {
    this.getTeachersInfo()
  },
  methods: {
    cancel() {
      this.resetForm()
      this.getTeachersInfo()
    },
    getTeachersInfo() {
      getTeachersInfoByUserName().then(res => {
        this.form = res[0]
        // console.log(this.form)
      })
    },
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          updateTeacherInfo({
            Idnum: this.form.Idnum,
            address: this.form.address,
            phone: this.form.phone,
            teacherid: this.form.teacherid
          }).then(res => {
            this.getTeachersInfo()
          })
          alert('编辑成功!')
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
      this.getTeachersInfo()
    },
    resetForm() {
      this.$refs['form'].resetFields()
      this.form = {
        Idnum: '',
        address: '',
        phone: ''
      }
    }
  }
}
</script>

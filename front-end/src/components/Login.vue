<template>
  <div class="login-container">
    
    <div id="login">
      <h4 id="welcome">欢迎登录</h4>
        <el-form
    ref="ruleFormRef"
    style="max-width: 600px"
    :model="ruleForm"
    status-icon
    :rules="rules"
    label-width="auto"
    class="demo-ruleForm"
  >
    <el-form-item label="Account" prop="account">
      <el-input v-model="ruleForm.account" type="text" autocomplete="off" />
    </el-form-item>
    <el-form-item label="Password" prop="password">
      <el-input
        v-model="ruleForm.password"
        type="password"
        placeholder="请输入密码"
        autocomplete="off"
      />
    </el-form-item> 

    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleFormRef)">
        Login
      </el-button>
      <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
    </el-form-item>
  </el-form>
    </div>
    </div>
    
     
</template>

<style>
</style>
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import router from '@/router'
//接受表单输入
const ruleForm=reactive({
    account:'',
    password:'',
})
//正确账号密码
const validAccount = 'zhang';
const validPassword = '123456';
//表单引用
const ruleFormRef=ref()

const rules=reactive({
    account:[
        { required: true, message: '请输入账号', trigger: 'blur' },
    ],
    password:[
        { required: true, message: '请输入密码', trigger: 'blur' },
    ],
})
const submitForm=async function(formEl){
    if(!formEl) return
    try{
        await formEl.validate()

        if(ruleForm.account===validAccount && ruleForm.password===validPassword){
            alert("登录成功")
            setTimeout(()=>{
                router.push({name:'home'})
            })
        }
        else{
            alert("账号或密码错误")
        }

    }
    catch(error){
        console.log("登录失败",error)
    }
}
const resetForm=function(formEl){
    if(!formEl) return
    formEl.resetFields()
}
</script>
<style>
.login-container{
  position:relative;
  height:100vh;
  width: 100%;
}
#login{
  position:absolute;
   width:200px;
  height:200px;
  top:0;
  left:0;
  bottom:0;
  right:0;
  margin:auto;

}
/* 让欢迎登录标题居中显示 */
.login-container h4 {
  /* text-align: center;    文本水平居中 */
  margin-bottom: 20px;
  color: #303133;
  font-size: 20px;
  font-weight: 500;
}
</style>
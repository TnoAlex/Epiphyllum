<template>
  <div class="inner_container">
    <div class="backbox">
      <div ref="loginMsg" class="loginMsg">
        <div class="textcontent"><p class="title">还没有账号?</p>
          <p>注册即代表同意用户协议.</p>
          <button id="switch1" @click="switchLogin">注册</button>
        </div>
      </div>
      <div ref="singupMsg" class="signupMsg visibility">
        <div class="textcontent"><p class="title">已经有个账号?</p>
          <p>登录可以看到所有的权限.</p>
          <button id="switch2" @click="switchRegist">登录</button>
        </div>
      </div>
    </div><!-- backbox -->
    <div ref="frontbox" class="frontbox">
      <div ref="login" class="login"><h2>登录</h2>
        <div class="inputbox">
          <el-form ref="loginForm" v-model="loginForm" class="loginForm" id="loginForm">
            <el-form-item>
              <el-col class="mb-2">
                <el-input v-model="loginForm.username" placeholder="用户名或邮箱" size="large" class="shadow"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-col class="mb-2">
                <el-input placeholder="密码" v-model="loginForm.password" size="large" class="shadow" type="password"
                          show-password></el-input>
              </el-col>
            </el-form-item>
            <el-row :gutter="20">
              <el-col :span="15">
                <div>
                  <el-input type="text" v-model="loginForm.code" name="vcode" placeholder="请输入验证码" size="large"
                            class="shadow"></el-input>
                </div>
              </el-col>
              <el-col :span="5">
                <div><img src="" ref="v_code" alt="code" @click="loadVCode" class="shadow"></div>
              </el-col>
            </el-row>
          </el-form>
        </div>
        <div>
          <el-button type="text" class="textlink">忘记密码</el-button>
          <el-button class="loginButton" size="large" round color="#35B729" @click="submitLogin">登录</el-button>
        </div>
      </div>
      <div ref="signup" class="signup hide"><h2>注册</h2>
        <div class="inputbox">
          <el-form ref="regForm" v-model="regForm" class="regForm">
            <el-form-item>
              <el-col class="mb-2">
                <el-input v-model="regForm.userAccount" placeholder="用户名" size="large" class="shadow"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-col class="mb-2">
                <el-input placeholder="密码" v-model="regForm.userPassword" size="large" class="shadow"
                          type="password"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-col class="mb-2">
                <el-input placeholder="邮箱" v-model="regForm.email" size="large" class="shadow"></el-input>
              </el-col>
            </el-form-item>
          </el-form>
        </div>
        <div>
          <el-button class="regButton" size="large" round color="#35B729">注册</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import {ElMessage} from "element-plus";

axios.defaults.baseURL = '/api'
let codetime = 0
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        password: '',
        code: '',
        timestamp: ''
      },
      regForm: {
        userAccount: '',
        userPassword: '',
        email: ''
      },
    }
  },
  beforeMount() {
    // 修改背景色
    document.querySelector('body').setAttribute('style', ' background-color: #A8A8A8;')
    this.currdatetime = new Date().getTime()
    codetime = this.currdatetime
    axios.get('/code/' + codetime)
        .then(res => {
          this.$refs.v_code.src = res.data.data
        })
  },

  beforeUnmount() {
    // 销毁背景色
    document.querySelector('body').removeAttribute('style')
  },

  methods: {
    switchLogin() {
      const refs = this.$refs
      setTimeout(function () {
        refs.loginMsg.classList.toggle("visibility")
        refs.frontbox.classList.add("moving")
        refs.singupMsg.classList.toggle("visibility")
        refs.signup.classList.toggle("hide")
        refs.login.classList.toggle("hide")
      }, 100)

    },
    switchRegist() {
      const refs = this.$refs
      setTimeout(function () {
        refs.loginMsg.classList.toggle("visibility")
        refs.frontbox.classList.remove("moving")
        refs.singupMsg.classList.toggle("visibility")
        refs.signup.classList.toggle("hide")
        refs.login.classList.toggle("hide")
      }, 100)
    },
    loadVCode() {
      codetime = this.currdatetime
      axios.get('/code/' + codetime)
          .then(res => {
            this.$refs.v_code.src = res.data.data
          })
    },
    submitLogin() {
      if (this.$data.loginForm.code.length === 0) {
        ElMessage({
          showClose: false,
          message: '请输入验证码',
          type: 'error',
          grouping: true
        })
      }
      if (this.$data.loginForm.username.length === 0) {
        ElMessage({
          showClose: false,
          message: '请输入用户名',
          type: 'error',
          grouping: true
        })
      }
      if (this.$data.loginForm.password.length === 0) {
        ElMessage({
          showClose: false,
          message: '请输入密码',
          type: 'error',
          grouping: true
        })
      }
      const parms = new URLSearchParams()
      const form = this.$data.loginForm
      parms.set('username',form.username)
      parms.set('password',form.password)
      parms.set('code',form.code)
      parms.set('timestamp',codetime)
      axios.post('/doLogin', parms)
          .then(res => {
            console.log(res.data)
          })
    },
  },
}

</script>
<style>
@import "@/css/login_style.css";
@import "@/css/font.css";
@import "element-plus/theme-chalk/index.css";

</style>
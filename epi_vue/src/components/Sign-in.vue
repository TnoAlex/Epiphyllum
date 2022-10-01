<template>
  <main>
    <!-- Container START -->
    <div class="container">
      <div class="row justify-content-center align-items-center vh-100 py-5">
        <!-- Main content START -->
        <div class="col-sm-10 col-md-8 col-lg-7 col-xl-6 col-xxl-5">
          <!-- Sign in START -->
          <div class="card card-body text-center p-4 p-sm-5">
            <!-- Title -->
            <h1 class="mb-2">Sign in</h1>
            <p class="mb-0">Don't have an account?
              <router-link to="sing-up">Click here to sign up</router-link>
            </p>
            <!-- Form START -->
            <form class="mt-sm-4">
              <!-- Email -->
              <div class="mb-3 input-group-lg"><input type="text" class="form-control"
                                                      placeholder="Enter email" v-model="LoginForm.username"></div>
              <!-- New password -->
              <div class="mb-3 position-relative">
                <!-- Password -->
                <div class="input-group input-group-lg">
                  <input class="form-control fakepassword"
                         type="password" id="psw-input" placeholder="Ente Password" v-model="LoginForm.password">
                </div>

              </div><!-- Remember me -->
              <div class="mb-3 position-relative">
                <!-- Password -->
                <div class="input-group input-group-lg">
                  <el-row :gutter="25">
                    <el-col :span="15">
                      <div>
                        <el-input type="text" v-model="LoginForm.code" name="vcode" placeholder="请输入验证码" size="large"
                                  class="shadow"></el-input>
                      </div>
                    </el-col>
                    <el-col :span="8">
                      <div><img src="" ref="v_code" alt="code" @click="loadVCode" class="shadow"></div>
                    </el-col>
                  </el-row>
                </div>
              </div><!-- Remember me -->
              <div class="mb-2 d-sm-flex justify-content-between mt-2">
                <router-link to="forgot-password">Forgot password?</router-link>
                >
              </div><!-- Button -->
              <div class="d-grid">
                <button @click="submitLogin" type="button" class="btn btn-lg btn-primary">Login</button>
              </div><!-- Copyright -->
              <p class="mb-0 mt-3">©2022 <a href="/">Epiphyllum.</a>All rights reserved</p>
            </form><!-- Form END -->
          </div><!-- Sign in START -->
        </div>
      </div><!-- Row END -->
    </div><!-- Container END -->
  </main>
</template>

<script>
import {ElMessage} from "element-plus";
import sha256 from "js-sha256";

let codetime = 0
export default {
  name: "Sign-in",
  data() {
    return {
      LoginForm: {
        username: '',
        password: '',
        code: '',
        timestamp: ''
      }
    }
  },
  beforeMount() {
    codetime = new Date().getTime().toString().substr(0, 10)
    this.$axios.get('/code/' + codetime)
        .then(res => {
          this.$refs.v_code.src = res.data.data
        })
  },
  methods: {
    loadVCode() {
      codetime = new Date().getTime().toString().substr(0, 10)
      this.$axios.get('/code/' + codetime)
          .then(res => {
            this.$refs.v_code.src = res.data.data
          })
    },
    messageBox(msg,type){
      ElMessage({
        showClose:false,
        message:msg,
        type:type,
        grouping: true
      })
    },
    async submitLogin() {
      if (this.LoginForm.code.length === 0) {
        this.messageBox("请输入验证码","error")
        return
      }
      if (this.LoginForm.username.length === 0) {
        this.messageBox("请输入用户名","error")
        return
      }
      if (this.LoginForm.password.length === 0) {
        this.messageBox("请输入密码","error")
        return
      }
      this.LoginForm.timestamp = codetime
      this.LoginForm.password = sha256.sha256(this.LoginForm.password)
      await this.$axios.post('/login', this.LoginForm)
          .then( res => {
            if(res.data.code ===200){
              this.$cookies.set("tokens", res.data.data)
            }
            else{
              this.messageBox(res.data.msg,"error")
            }
          })
          .catch(err => {
            this.messageBox(err.data.msg,"error")
          }
      )
      const url = "/doLogin/" + this.$cookies.get("tokens").accessToken
      await this.$axios.post(url)
          .then( async res => {
            if (res.data.code === 200) {
              const url = '/usd/user/info/common/' + this.$cookies.get("tokens").accessToken
              await this.$axios.post(url)
                  .then(res => {
                    if(res.data.code===200){
                      localStorage.setItem("userCommonInfo",JSON.stringify(res.data.data))
                      this.$router.push({path: '/index'})
                    }else{
                      this.messageBox(res.data.msg,"error")
                    }

                  })
                  .catch(err=>{
                    this.messageBox(err,"error")
                  })
            }
            else{
              this.messageBox(res.data.msg,"error")
            }
          })
          .catch(err=>{
            this.messageBox(err,"error")
          })

    },
  }
}
</script>

<style>
@import "../css/all.min.css";
@import "../css/css2.css";
@import "../css/style.css";
</style>
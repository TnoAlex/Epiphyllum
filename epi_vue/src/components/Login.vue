<template>
  <body>
  <div class="container">
    <div class="backbox">
      <div ref="loginMsg" class="loginMsg" >
        <div class="textcontent"><p class="title">还没有账号?</p>
          <p>注册即代表同意用户协议.</p>
          <button id="switch1" @click="switchLogin">注册</button>
        </div>
      </div>
      <div ref="singupMsg" class="signupMsg visibility" >
        <div class="textcontent"><p class="title">已经有个账号?</p>
          <p>登录可以看到所有的权限.</p>
          <button id="switch2" @click="switchRegist">登录</button>
        </div>
      </div>
    </div><!-- backbox -->
    <div  ref="frontbox" class="frontbox">
      <div ref="login" class="login" ><h2>登录</h2>
        <div class="inputbox">
          <input type="text" name="email" placeholder="  EMAIL">
          <input type="password" name="password" placeholder="  密码">
        </div>
        <p>忘记密码?</p>
        <button>登录</button>
      </div>
      <div ref="signup" class="signup hide" ><h2>注册</h2>
        <div class="inputbox">
          <input type="text" name="fullname" placeholder="  用户名">
          <input type="text" name="email" placeholder="  EMAIL"><input
              type="password" name="password" placeholder="  密码"></div>
        <button>注册</button>
      </div>
    </div><!-- frontbox --></div>
  </body>
</template>

<script>
// import axios from 'axios'

export default {
  name: "Login",

  data(){
    return {loginForm:{
      userAccount:'',
        userPassword:''
      }}
  },
  methods:{
    switchLogin(){
      const refs = this.$refs
      setTimeout(function (){
        refs.loginMsg.classList.toggle("visibility")
        refs.frontbox.classList.add("moving")
        refs.singupMsg.classList.toggle("visibility")
        refs.signup.classList.toggle("hide")
        refs.login.classList.toggle("hide")
      },100)

    },
    switchRegist(){
      const refs = this.$refs
      setTimeout(function (){
        refs.loginMsg.classList.toggle("visibility")
        refs.frontbox.classList.remove("moving")
        refs.singupMsg.classList.toggle("visibility")
        refs.signup.classList.toggle("hide")
        refs.login.classList.toggle("hide")
      },100)
    },
  }
}

</script>
<style>
@import "@/css/login_style.css";
</style>
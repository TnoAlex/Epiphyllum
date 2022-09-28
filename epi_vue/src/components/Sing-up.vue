<template>
  <main>
    <!-- Container START -->
    <div class="container">
      <div class="row justify-content-center align-items-center vh-100 py-5">
        <!-- Main content START -->
        <div class="col-sm-10 col-md-8 col-lg-7 col-xl-6 col-xxl-5">
          <!-- Sign up START -->
          <div class="card card-body rounded-3 p-4 p-sm-5">
            <div class="text-center">
              <!-- Title -->
              <h1 class="mb-2">Sign up</h1><span class="d-block">Already have an account?
              <router-link to="sing-in">Sing in</router-link>
            </span>
            </div><!-- Form START -->
            <form class="mt-4">
              <!-- Email -->
              <div class="mb-3 input-group-lg">
                <input type="text" class="form-control" placeholder="Nick Name" v-model="SingUpForm.username">
              </div>
              <div class="mb-3 input-group-lg">
                <input type="email" class="form-control" placeholder="Enter email" v-model="SingUpForm.account">
              </div><!-- New password -->
              <div class="mb-3 position-relative">
                <div class="input-group input-group-lg">
                  <input class="form-control fakepassword" type="password" id="psw-input" placeholder="Enter new password"
                         v-model="SingUpForm.password" @input="passwordcomplex">
                </div><!-- Pswmeter -->
                <el-form-item>
                  <div class="input_span">
                    <span :style="{'background-color':(score>=0&&score<25)?'#FC5F76':((score>=25&&score<35)?'#FF9900':(score>=35?'#33CC00':'#BBBBBB'))}"></span>
                    <span :style="{'background-color':((score>=25&&score<35)?'#FF9900':(score>=35?'#33CC00':'#BBBBBB'))}"></span>
                    <span :style="{'background-color':score>=35?'#33CC00':'#BBBBBB'}"></span>
                  </div>
                </el-form-item>
              </div><!-- Confirm password -->
              <div class="mb-3 input-group-lg">
                <input class="form-control" type="password" placeholder="Confirm password" v-model="ConfirmPassword">
              </div>
              <div class="mb-3 input-group-lg">
                <input class="form-control" type="text" placeholder="Phone Number" v-model="SingUpForm.phone">
              </div>
              <div class="mb-3 input-group-lg">
                <input class="form-control" type="password" placeholder="ID Number" v-model="SingUpForm.identification">
              </div>
              <div style="margin-top: 2px;margin-left: 10px;margin-bottom: 5px">
                <el-switch
                    v-model="vgender"
                    active-text="Male"
                    inactive-text="Female"
                    @change="genderFormat"
                />
              </div>
              <small>We'll never share your Info with anyone else.</small>
              <div class="d-grid mt-2">
                <button type="button" class="btn btn-lg btn-primary" @click="submitSingUpForm">Sign me up</button>
              </div><!-- Copyright -->
              <p class="mb-0 mt-3 text-center">©2022 <a href="/">Epiphyllum.</a>All rights reserved</p>
            </form>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>

import {ElMessage} from "element-plus";
import sha256 from "js-sha256";


export default {
  name: "Sing-up",
  data() {
    return {
      SingUpForm: {
        account: '',
        password: '',
        username:'',
        phone:'',
        identification:'',
        gender:''
      },
      vgender:false,
      ConfirmPassword:'',
      score:-1
    }
  },
  methods: {
    genderFormat(){
      if(this.vgender){
        this.SingUpForm.gender = 1
      }
      else{
        this.SingUpForm.gender=0
      }
    },
    messageBox(msg,type){
      ElMessage({
        showClose:false,
        message:msg,
        type:type,
        grouping: true
      })
    },
    async submitSingUpForm(){
      if(this.SingUpForm.account.length === 0){
        this.messageBox("请输入邮箱","error")
        return
      }
      if(this.SingUpForm.password.length === 0){
        this.messageBox("请输入密码","error")
        return
      }
      if(this.SingUpForm.phone.length === 0){
        this.messageBox("请输入手机号","error")
        return
      }
      if(this.SingUpForm.identification.length === 0){
        this.messageBox("请输入身份证","error")
        return
      }
      // if(this.score<25){
      //   this.messageBox("密码强度太低，请重新输入","error")
      // }
      // let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      // if(!reg.test(this.SingUpForm.identification)){
      //   this.messageBox("身份证格式错误","error")
      // }
      this.SingUpForm.password = sha256.sha256(this.SingUpForm.password)
      await this.$axios.post('/register',this.SingUpForm)
          .then(res=>{
            if(res.data.code === 200){
              this.$router.push({path:'/sing-in'})
            }
            else{
              this.messageBox(res.data.msg,"error")

            }
          })
          .catch(err=>{
            this.messageBox(err.data.msg,"error")
          })
    },
    passwordcomplex(){
      let passwordscore = 0
      let pwdArr = this.SingUpForm.password.split('');
      // pwdLen长度
      if(pwdArr.length>4&&pwdArr.length<=7){  //长度在4-7之间，加五分
        passwordscore += 5
      }else if(pwdArr.length>7){  //长度在7以上，加10分
        passwordscore += 10
      }
      // letter字母
      if(pwdArr.some(item => {return /^[a-z]$/.test(item)})){  //是否存在小写字母
        if(pwdArr.some(item => {return /^[A-Z]$/.test(item)})){  //是否存在大写字母
          passwordscore += 10   //大小写都有，加10，否则加5
        }else{
          passwordscore += 5
        }
      }else if(pwdArr.some(item => {return /^[A-Z]$/.test(item)})){
        passwordscore += 5
      }
      //num数字
      if(pwdArr.some(item => {return /^[0-9]$/.test(item)})){ //判断是否存在数字
        let count = 0
        pwdArr.forEach(item => {   //判断数字出现的次数
          if(/^[0-9]$/.test(item)){
            count++
          }
        })
        if(count>=3){  //出现大于等于3次，加10，否则加5
          passwordscore += 10;
        }else{
          passwordscore += 5;
        }
      }
      //special特殊字符
      if(pwdArr.some(item => {return /^[\^%&'*.,;=+\-?@#!$\x22]$/.test(item)})){ //判断是否存在特殊字符
        let count = 0
        pwdArr.forEach(item => {  //特殊字符出现次数
          if(/^[\^%&'*.,;=+\-?@#!$\x22]$/.test(item)){
            count++
          }
        })
        if(count>=2){
          passwordscore += 15;  //出现两次以上加15，否则加5
        }else{
          passwordscore += 5;
        }
      }
      //是否连续
      let isContinued = false;
      let countinuedCount = 0;
      for(let i =0;i<pwdArr.length;i++){
        if(pwdArr[i+1]){
          if((pwdArr[i].charCodeAt(0)+1===pwdArr[i+1].charCodeAt(0))||(pwdArr[i].charCodeAt(0)-1===pwdArr[i+1].charCodeAt(0))){  //如果相邻两个字符连续
            isContinued = true;  //开始记录连续
            countinuedCount++    //记录连续次数
          }else{
            if(isContinued){
              isContinued = false;
              passwordscore -= countinuedCount   //结束当前连续时，分数扣掉连续次数
              countinuedCount = 0
            }
          }
        }
      }
      if(countinuedCount === pwdArr.length-1){
        passwordscore = 0   //如果整个密码连续，分数为0
      }else{
        passwordscore -= countinuedCount
      }
      if(pwdArr.length<8){  //需求规定的，密码必须大于8位
        passwordscore = 0
      }
      for(let i = 0;i<pwdArr.length;i++){  //如果整个密码由单一字符构成，分数为0
        if(i ===pwdArr.length-1){
          passwordscore = 0
        }else if(pwdArr[i]!==pwdArr[i+1]){
          break
        }
      }
     this.score = passwordscore
    }
  }
}

</script>

<style>
@import "../css/all.min.css";
@import "../css/css2.css";
@import "../css/style.css";
.input_span{
  height: 8px;
  display: flex;
  float: left;
  width: 35%;
  margin-top: 10px;
}
.input_span span{
  display: inline-block;
  width: 30%;
  border-radius: 8px;
  margin-right: 3px;
  text-align: center;
  margin-top: 3px;
}
</style>
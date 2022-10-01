<template>
  <head>
    <title>Notification</title>
    <meta charset="utf-8">
  </head>
  <body>
  <HeaderNav></HeaderNav>
  <main>
    <!-- Container START -->
    <div class="container">
      <div class="row g-4">
        <!-- Main content START -->
        <div class="col-lg-8 mx-auto">
          <!-- Card START -->
          <div class="card">
            <div class="card-header py-3 border-0 d-flex align-items-center justify-content-between">
              <h1 class="h5 mb-0">通知</h1><!-- Notification action START -->
              <div class="dropdown">
                <a href="#" class="text-secondary btn btn-secondary-soft-hover py-1 px-2" id="cardNotiAction"
                   data-bs-toggle="dropdown" aria-expanded="false">
                  <i class="bi bi-three-dots"></i>
                </a>
                <!-- Card share action dropdown menu -->
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cardNotiAction">
                  <li @click="markdownAll">
                    <a class="dropdown-item" href="###"><i class="bi bi-check-lg fa-fw pe-2">
                    </i>全部标记为阅读</a>
                  </li>
                </ul>
              </div>
            </div>
            <div class="card-body p-2">
              <div v-if="NotificationList.length !==0 ">
                <ul class="list-unstyled" v-for="(item,index) in NotificationList" :key="index">
                  <!-- Notif item -->
                  <li v-if="item.status===0">
                    <div class="rounded badge-unread d-sm-flex border-0 mb-1 p-3 position-relative">
                      <!-- Avatar -->
                      <div class="avatar text-center">
                        <img class="avatar-img rounded-circle" :src="item.createrPortrait" alt=""></div><!-- Info -->
                      <div class="mx-sm-3 my-2 my-sm-0">
                        <p class="small mb-0">
                          <b>你有一条来自</b><b><span>{{item.createBy}} </span>></b><b>的新消息</b>
                        </p>
                        <p class="small">{{ item.context }}</p>
                      </div><!-- Action -->
                      <div class="d-flex ms-auto">
                        <p class="small me-5 text-nowrap"> {{timeTranslate(item.createTime)}}</p>
                        <!-- Notification action START -->
                        <div class="dropdown position-absolute end-0 top-0 mt-3 me-3">
                          <a href="###" class="z-index-1 text-secondary btn position-relative py-0 px-2"
                             id="cardNotiAction6" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-three-dots"></i>
                          </a>
                          <!-- Card share action dropdown menu -->
                          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cardNotiAction6">
                            <li @click="deleteNotification(index)">
                              <a class="dropdown-item" href="###">
                                <i class="bi bi-trash fa-fw pe-2"></i>
                                删除</a>
                            </li>
                            <li @click="markNotice(index)"><a class="dropdown-item" href="###">
                              <i class="bi bi-bell-slash fa-fw pe-2"></i>标记已读 </a>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li v-else>
                    <div class="rounded badge-unread d-sm-flex border-0 mb-1 p-3 position-relative" style="background: white">
                      <!-- Avatar -->
                      <div class="avatar text-center">
                        <img class="avatar-img rounded-circle" :src="item.createrPortrait" alt=""></div><!-- Info -->
                      <div class="mx-sm-3 my-2 my-sm-0">
                        <p class="small mb-0">
                          <b>来自</b><b><span>{{item.createBy}}</span> </b><b>的消息</b>
                        </p>
                        <p class="small">{{ item.context }}</p>
                      </div><!-- Action -->
                      <div class="d-flex ms-auto">
                        <p class="small me-5 text-nowrap"> {{timeTranslate(item.createTime)}}</p>
                        <!-- Notification action START -->
                        <div class="dropdown position-absolute end-0 top-0 mt-3 me-3">
                          <a href="###" class="z-index-1 text-secondary btn position-relative py-0 px-2"
                             id="cardNotiAction6" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-three-dots"></i>
                          </a>
                          <!-- Card share action dropdown menu -->
                          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cardNotiAction6">
                            <li @click="deleteNotification(index)">
                              <a class="dropdown-item" href="###">
                                <i class="bi bi-trash fa-fw pe-2"></i>
                                删除</a>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
              <div id="loadDiv" class="card-footer border-0 py-3 text-center position-relative d-grid pt-0">
                <el-button @click="loadMore" type="primary" round>{{ loading ? '点击加载更多' : '已经到底啦~' }}</el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  </body>


</template>
<script>
import headerNav from "@/components/UniversalComponents/headerNav";
import {ElMessage} from "element-plus";

export default {
  name: "notification",
  components: {
    'HeaderNav':headerNav
  },
  data() {
    return {
      loading: false,
      pageIndex: 1,
      NotificationList: [],
    }
  },
  beforeMount() {
    this.NotificationList.pop()
  },
  mounted(){
    this.getNotifications()
  },
  beforeRouteEnter(to,from,next){
    next(vm=>{
      if(vm.$cookies.isKey("tokens"))
        next(true)
      else{
        vm.$router.push({path:'/sing-in'})
        ElMessage({
          showClose:false,
          message:'请先登录',
          type:'error',
          grouping: true
        })
      }
    })
  },
  methods: {
    messageBox(msg,type){
      ElMessage({
        showClose:false,
        message:msg,
        type:type,
        grouping: true
      })
    },
    async getNotifications() {
      const page = this.pageIndex-1
      const url = "/usd/user/notice/"+page+"/8/"+this.$cookies.get("tokens").accessToken
      await this.$axios.post(url)
          .then(res=>{
            if(res.data.code===200){
              this.NotificationList = this.NotificationList.concat(res.data.data)
              this.loading = res.data.data.length === 8;
            }
            else{
              this.messageBox(res.data.msg,"error")
            }
          })
          .catch(err=>{
            this.messageBox(err.data.msg,"error")
          })
    },

    async loadMore() {
      let winHeight = window.innerHeight || document.documentElement.clientHeight;
      let docHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
      this.pageIndex = this.pageIndex + 1
      await this.getNotifications(this.pageIndex);
      document.documentElement.scrollTop = docHeight - winHeight - 1
    },

    async deleteNotification(index) {
      //将数据更改，然后发给后端
      const nid = this.NotificationList[index].nid
      const url = "/usd/user/notice/remove-notice/"+nid+"/"+this.$cookies.get("tokens").accessToken
      await this.$axios.post(url)
          .then(res=>{
            if(res.data.code === 200){
              this.NotificationList.splice(index, 1)
            }
            else{
              this.messageBox(res.data.msg,"error")
            }
          })
    },
    async markNotice(index) {
      const nid = this.NotificationList[index].nid
      const url = "usd/user/notice/mark-notice/"+nid+"/"+this.$cookies.get("tokens").accessToken
      await this.$axios.post(url)
          .then(res=>{
            if(res.data.code === 200){
              this.NotificationList[index].status = 1
            }else {
              this.messageBox(res.data.msg,"error")
            }
          })
    },
    async markdownAll() {
      let markList = this.NotificationList.filter(n => n.status === 0).map(n=>n.nid);
      const url = "/usd/user/notice/markall-notice/"+this.$cookies.get("tokens").accessToken
      await this.$axios.post(url,markList)
          .then(res=>{
            if(res.data.code === 200){
              this.NotificationList.forEach(
                  item=>{
                    if (markList.includes(item.nid)){
                      item.status = 1
                    }
                  }
              )
            }
            else{
              this.messageBox(res.data.msg,"error")
              }
          }).catch(err=>{
            this.messageBox(err.data.msg,"error")
          })
    },
    timeTranslate(times) {
      const dateTime = times.split('T')
      return dateTime[0]+" "+dateTime[1]
    },

  },
}
</script>

<style scoped>
@import url(@/css/font.css);
@import url(@/css/all.min.css);
@import url(@/css/style.css);
@import url(@/css/css2.css);
@import url(@/Icon/loading01/iconfont.css);

.loading {
  display: flex;
  align-items: center;
  padding-bottom: 20px;
  justify-content: center;
}

.loading .iconfont {
  margin-right: 10px;
  animation: loading 1s linear infinite;
}

@keyframes loading {
  from {
    transform: rotate(0);
  }
  from {
    transform: rotate(360deg);
  }
}
</style>
<template>
  <head>
    <title>Groups</title><!-- Meta Tags -->
    <meta charset="utf-8">
  </head>
  <body>
  <HeaderNav></HeaderNav>
  <main>
    <!-- Container START -->
    <div class="container">
      <div class="row g-4">
        <IndexLeft></IndexLeft>

        <div class="col-md-8 col-lg-6 vstack gap-4">
          <div class="card">
            <!-- Card header START -->
            <div class="card-header border-0 pb-0">
              <div class="row g-2">
                <div class="col-lg-2">
                  <!-- Card title -->
                  <h1 class="h4 card-title mb-lg-0">群组</h1>
                </div>
                <div class="col-sm-6 col-lg-3 ms-lg-auto">
                </div>
                <div class="col-sm-6 col-lg-3">
                  <!-- Button modal -->
                  <span class="btn btn-primary-soft  w-100" data-bs-toggle="modal"
                        data-bs-target="#modalCreateGroup"> <i class="fa-solid fa-plus pe-1"></i> 创建一个群组</span>
                </div>
              </div>
            </div>


            <div class="card-body">

              <ul  class="nav nav-tabs nav-bottom-line justify-content-center justify-content-md-start">
                <li @click="tabTranslate(1)" ref="joinedGroup" class="nav-item"><a class="nav-link active" data-bs-toggle="tab"
                                                                 href="#tab-1">未加入
                </a></li>
                <li @click="tabTranslate(2)" ref="unJoinedGroup" class="nav-item"><a class="nav-link" data-bs-toggle="tab"
                                                                 href="#tab-2">已加入 </a></li>
              </ul>
              <div class="tab-content mb-0 pb-0">

                <div class="tab-pane fade show active" id="tab-1">
                  <div class="row g-4">
                    <div class="col-sm-6 col-lg-4" v-for="(item,index) in groupInformation" :key="index">
                      <div class="card">
                        <div class="h-80px rounded-top"
                             :style="{backgroundImage:`url(${groupBackGroundImage})`,
                                      'background-position':'center',
                                      'background-size':'cover',
                                      'background-repeat':'no-repeat'
                                      }">
                        </div>
                        <div class="card-body text-center pt-0">
                          <div class="avatar avatar-lg mt-n5 mb-3">
                            <img
                              class="avatar-img rounded-circle border border-white border-3 bg-white"
                              :src="item.groupIco" alt="" @click="toDetail(index)"></div><!-- Info -->
                          <h5 class="mb-0"  @click="toDetail(index)">
                              {{ item.groupName }}
                          </h5>
                          <div>
                            <small>
                              <span class="iconfont icon-yuanshuju-chuangjianren"></span>{{ item.createUser }}
                            </small>
                          </div>

                          <div>
                            <small>
                              <span class="iconfont icon-yuanshuju-chuangjianshijian"></span> {{ item.createTime }}
                            </small>
                          </div>
                          <ul class="avatar-group list-unstyled align-items-center justify-content-center mb-0 mt-3">
                            <li class="avatar avatar-xs"
                                v-for="(avatarImgSrc,avatarIndex) in item.recentlyJoined.slice(0,5)" :key="avatarIndex">
                              <img class="avatar-img rounded-circle" :src="avatarImgSrc" alt="avatar">
                            </li>
                          </ul>
                        </div>
                        <div class="card-footer text-center">
                          <span class="btn btn-success-soft" style="" @click="joinGroup(index)">加入群组
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="tab-pane fade" id="tab-2">
                  <div class="row g-4">
                    <div class="col-sm-6 col-lg-4" v-for="(item,index) in groupInformation " :key="index">
                      <div class="card">
                        <div class="h-80px rounded-top"
                             :style="{backgroundImage:`url(${groupBackGroundImage})`,
                                      'background-position':'center',
                                      'background-size':'cover',
                                      'background-repeat':'no-repeat'
                                      }">
                        </div><!-- Card body START -->
                        <div class="card-body text-center pt-0">
                          <!-- Avatar -->
                          <div class="avatar avatar-lg mt-n5 mb-3"><img
                              class="avatar-img rounded-circle border border-white border-3 bg-white"
                              :src="item.groupIco" alt="" @click="toDetail(index)"></div><!-- Info -->
                          <h5 class="mb-0" @click="toDetail(index)"><span>{{ item.groupName }}</span></h5>
                          <div>
                            <small>
                              <span class="iconfont icon-yuanshuju-chuangjianren"></span> {{ item.createUser }}
                            </small>
                          </div>

                          <div>
                            <small>
                              <span class="iconfont icon-yuanshuju-chuangjianshijian"></span>{{ item.createTime }}
                            </small>
                          </div>

                          <ul class="avatar-group list-unstyled align-items-center justify-content-center mb-0 mt-3">
                            <li v-for="(avatarImgSrc,avatarIndex) in item.recentlyJoined.slice(0,5)" :key="avatarIndex"
                                class="avatar avatar-xs">
                              <img class="avatar-img rounded-circle" :src="avatarImgSrc"
                                   alt="avatar"></li>
                          </ul><!-- Avatar group END -->
                        </div><!-- Card body END -->
                        <!-- Card Footer START -->
                        <div class="card-footer text-center">
                          <span @click="leaveGroup(index)" class="btn btn-danger-soft">离开群组
                          </span>
                        </div><!-- Card Footer END -->
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </main>
  </body>
  <!--模态框  -->
  <div class="modal fade" id="modalCreateGroup" tabindex="-1" aria-labelledby="modalLabelCreateGroup"
       aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <!-- Title -->
        <div class="modal-header">
          <h5 class="modal-title" id="modalLabelCreateGroup">创建群组</h5>
          <button ref="modalClose" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <!-- Form START -->
          <form>
            <!-- Group name -->
            <div class="mb-3">
              <label class="form-label">群组名</label>
              <input ref="groupNameRef" type="text" class="form-control" placeholder="添加一个群组名">
            </div>
            <!-- Group picture -->
            <div class="mb-3">
              <label class="form-label">群组照片</label>
              <!-- Avatar upload START -->
              <div class="d-flex align-items-center">
                <div class="avatar-uploader me-3">
                  <!-- Avatar edit -->
                  <div class="avatar-edit">
                    <input ref="imgUpload" type='file' id="avatarUpload" accept=".png, .jpg, .jpeg"
                           @input="change($event)">
                    <label for="avatarUpload"></label>
                  </div><!-- Avatar preview -->
                  <div class="avatar avatar-xl position-relative">
                    <img id="avatar-preview" class="avatar-img rounded-circle border border-white border-3 shadow"
                         :src="imageUrl ? imageUrl : baseImg" alt=""></div>
                </div><!-- Avatar remove button -->
              </div><!-- Avatar upload END -->
            </div><!-- Select audience -->
            <div class="mb-3"><label class="form-label">群组简介 </label><textarea ref="groupDescriptionRef"
                                                                                   class="form-control"
                                                                                   rows="3"
                                                                                   placeholder="添加群组简介"></textarea>
            </div>
          </form><!-- Form END -->
        </div><!-- Modal footer -->
        <div class="modal-footer">
          <span class="btn btn-success-soft" @click="createGroup">立即创建</span>

        </div>
      </div>
    </div>
  </div>
</template>
<script>
import headerNav from "@/components/Parts/headerNav"
import indexLeft from '@/components/Parts/indexLeft'
import {ElMessage} from 'element-plus'
import messageBox, {debounce} from "@/utils/tools";

export default {
  name: "groups",
  components: {
    'IndexLeft': indexLeft,
    'HeaderNav': headerNav
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (vm.$cookies.isKey("tokens"))
        next(true)
      else {
        vm.$router.push({path: '/sign-in'})
        ElMessage({
          showClose: false,
          message: '请先登录',
          type: 'error',
          grouping: true
        })
      }
    })
  },
  data() {
    return {
      baseImg: require("@/static/imgs/default_group.webp"),
      groupBackGroundImage: require("@/static/imgs/default_groupBackground.jpg"),
      imageUrl: "",
      pageIndex: 1,
      tabIndex: 1,
      groupInformation: [],
    }
  },
  async beforeMount() {
   await this.getMoreGroupInformation(1)
  },
  methods: {
    toDetail(index){
      this.$router.push(
          {
            name:'groupDetail',
            params:{
              'group_info':JSON.stringify(this.groupInformation[index])
            }
          }
      )
    },
    async getMoreGroupInformation(tabIndex) {
      if (tabIndex === 2) {
        //后台发出请求
        const url = "/usd/group/user-joined/" + this.pageIndex + "/6/" + this.$cookies.get("tokens").accessToken
        await this.$axios.get(url)
            .then(res => {
              if (res.data.code === 200) {
                if (res.data.data === null) {
                  messageBox("没有更多群组了~", "warning")
                } else {
                  this.pageIndex = this.pageIndex + 1
                  this.groupInformation = this.groupInformation.concat(res.data.data)
                }
              }
            }).catch(err => {
          messageBox(err, "error")
        })
      } else if (tabIndex === 1) {
        const url ="/usd/group/unjoined-group/"+this.pageIndex+"/6/"+this.$cookies.get("tokens").accessToken
        await this.$axios.get(url)
            .then(res => {
              if (res.data.code === 200) {
                if (res.data.data === null) {
                  messageBox("没有更多群组了~", "warning")
                } else {
                  this.pageIndex = this.pageIndex + 1
                  this.groupInformation = this.groupInformation.concat(res.data.data)
                }
              }
            }).catch(err => {
          messageBox(err, "error")
        })
      }

    },
    async scrollFn(){
      let winHeight = window.innerHeight || document.documentElement.clientHeight;
      //滚出去的高度
      let st = document.documentElement.scrollTop || document.body.scrollTop;
      //文档高度
      let docHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
      if (winHeight + st >= docHeight - 3) {
        if(this.groupInformation.length>=6){
          await this.getMoreGroupInformation(this.tabIndex)
        }
       }
    },

    async tabTranslate(tabIndex) {
      this.tabIndex = tabIndex
      //首先将pageIndex清0
      this.pageIndex = 1
      this.groupInformation = []
      await this.getMoreGroupInformation(tabIndex)
    },
    async joinGroup(index) {
      const url = "/usd/group/join/"+this.groupInformation[index].gid+"/"+this.$cookies.get("tokens").accessToken
      await this.$axios.post(url)
          .then(res=>{
            if(res.data.code === 200){
              messageBox("成功加入了~")
              this.$refs.joinedGroup.click()
            }
            else{
              messageBox(res.data.msg,"error")
            }
          }).catch(err=>{
            messageBox(err,"error")
          })
    },
    async leaveGroup(index) {
      const url = "/usd/group/exit/"+this.groupInformation[index].gid+"/"+this.$cookies.get("tokens").accessToken
      await this.$axios.post(url)
          .then(res=>{
            if(res.data.code === 200){
              messageBox("已经退出了~")
              this.$refs.unJoinedGroup.click()
            }
            else{
              messageBox(res.data.msg,"error")
            }
          })
          .catch(err=>{
            messageBox(err,"error")
          })
    },
    async change(e) {
      let file = e.target.files[0]
      await this.toBase64(file).then(res => {
        this.imageUrl = res
      })

    },
    toBase64(file) {
      return new Promise(function (resolve) {
        let reader = new FileReader()
        reader.readAsDataURL(file)
        reader.onload = function () {
          resolve(this.result)
        }
      })
    },
    async createGroup() {
      let groupName = this.$refs.groupNameRef.value
      let groupDescription = this.$refs.groupDescriptionRef.value
      if (groupName === "" || groupDescription === "" || this.imageUrl === "") {
        messageBox("请将信息填写完整", "warning")
        return
      }
      let groupObj = {
        groupName: groupName,
        groupDescription: groupDescription,
        groupIco: this.imageUrl
      }
      const url = "/usd/group/add/" + this.$cookies.get("tokens").accessToken
      this.$axios.post(url, groupObj)
          .then(res => {
            if (res.data.code === 200) {
              this.$refs.groupNameRef.value = ""
              this.$refs.groupDescriptionRef.value = ""
              this.imageUrl = ""
              this.$refs.imgUpload.value = null
              this.$refs.modalClose.click()
              messageBox("创建成功", "success")
            } else {
              messageBox(res.data.msg, "error")
            }
          }).catch(err => {
        messageBox(err, "error")
      })
    }
  },
  mounted() {
    //监听滚动
    window.addEventListener("scroll", debounce(this.scrollFn,1000,false));
  },
  beforeUnmount() {
    //移除滚动
    window.removeEventListener("scroll", this.scrollFn);
  }
}
</script>

<style scoped>
@import url(@/css/all.min.css);
@import url(@/css/style.css);
@import url(@/css/css2.css);
@import url(@/Icon/createPerson/iconfont.css);
@import url(@/Icon/createTime/iconfont.css);
</style>
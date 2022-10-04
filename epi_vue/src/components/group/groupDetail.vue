<template>
  <head>
    <title>Groups</title><!-- Meta Tags -->
    <meta charset="utf-8">
  </head>
  <body style="overflow-x: visible!important;">
  <HeaderNav></HeaderNav>
  <main>
    <!-- Container START -->
    <div class="container">
      <div class="row g-4">

        <IndexLeft></IndexLeft>

        <div class="col-md-8 col-lg-9 vstack gap-4">
          <!-- Card START -->
          <div class="card">
            <!-- Card body START -->
            <div class="card-body">
              <div class="d-md-flex flex-wrap align-items-start text-center text-md-start">
                <div class="mb-2">
                  <!-- Avatar -->
                  <div class="avatar avatar-xl">
                    <img class="avatar-img border-0" :src="groupInfo.groupIco" alt="">
                  </div>
                </div>
                <div class="ms-md-4 mt-3">
                  <!-- Info -->
                  <h1 class="h5 mb-0">{{groupInfo.groupName}} <i class="bi bi-patch-check-fill text-success small"></i></h1>
                  <ul class="nav nav-divider justify-content-center justify-content-md-start">
                    <li class="nav-item"> 创建时间：{{groupInfo.createTime}}</li>
                    <li class="nav-item"> {{this.groupInfo.joinedNum}} 人 </li>
                  </ul>
                </div>

                <div class="d-flex justify-content-center justify-content-md-start align-items-center mt-3 ms-lg-auto">
                    <span @click="join" class="btn btn-sm btn-success-soft me-2" type="button"> <i
                        class="bi bi-person-check-fill pe-1"></i> 申请加入</span>
                </div>
              </div>

              <ul
                  class="avatar-group list-unstyled justify-content-center justify-content-md-start align-items-center mb-0 mt-3 flex-wrap">
                <li class="avatar avatar-xs" v-for="(item,index) in groupInfo.recentlyJoined.slice(0,10)" :key="index">
                  <img class="avatar-img rounded-circle" :src="item" alt="avatar">
                </li>
                <li class="avatar avatar-xs me-2">
                  <div class="avatar-img rounded-circle bg-primary"><span
                      class="smaller text-white position-absolute top-50 start-50 translate-middle">+{{groupInfo.nickNames.length>10?10:groupInfo.nickNames.length}}</span></div>
                </li>
                <li class="small text-center">
                  {{groupInfo.nickNames[0]}} 等最近加入群组
                </li>
              </ul>
              <!-- Join group END -->
            </div>
            <!-- Card body END -->
            <div class="card-footer pb-0">
              <ul class="nav nav-tabs nav-bottom-line justify-content-center justify-content-md-start mb-0">
                <li class="nav-item"> <a class="nav-link active" data-bs-toggle="tab" href="#group-tab-1"> 话题 </a>
                </li>
              </ul>
            </div>
          </div>
          <div class="vstack gap-4 h-20px col-lg-10 " style="margin-left: 80px" > <!--中间-->
            <div class="card card-body">
              <div class="d-flex mb-3" style="margin-bottom: 0 !important">
                <!-- Avatar -->
                <div class="avatar avatar-xs me-2">
                  <a href="#">
                    <img class="avatar-img rounded-circle" :src="userIco" alt="">
                  </a>
                </div>
                <!-- Post input -->
                <form class="w-100">
                  <textarea class="form-control pe-4 border-0" rows="2" data-autoresize="" placeholder="分享你的想法">
                  </textarea>
                </form>
              </div>
              <ul class="nav nav-pills nav-stack small fw-normal">
                <li class="nav-item">
                  <a class="nav-link bg-light py-1 px-2 mb-0" href="#!" data-bs-toggle="modal"
                     data-bs-target="#feedActionPhoto">
                    <i class="bi bi-image-fill text-success pe-2">
                    </i>贴子编辑</a>
                </li>
                <li class="nav-item dropdown ms-lg-auto">
                </li>
              </ul>
            </div>
            <div v-if="postList.length!==0">
              <div v-for="(item,index) in postList" :key="index">
                <div class="card">
                  <!-- Card header START -->
                  <div class="card-header border-0 pb-0">
                    <div class="d-flex align-items-center justify-content-between">
                      <div class="d-flex align-items-center">
                        <!-- Avatar -->
                        <div class="avatar avatar-story me-2">
                          <a href="#!">
                            <img class="avatar-img rounded-circle" :src="item.createrAvatar" alt="">
                          </a>
                        </div>
                        <!-- Info -->
                        <div>
                          <div class="nav nav-divider">
                            <h6 class="nav-item card-title mb-0">
                              <a href="#!">{{ item.createUser }}</a>
                            </h6>
                            <span class="nav-item small">{{ item.createTime }}</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!-- Card header END -->
                  <!-- Card body START -->
                  <div class="card-body" style="padding: 5px">
                    <md-editor-v3 previewOnly="true" v-model="item.text"/>
                    <div v-for="(imgSrc,imgIndex) in item.images" :key="imgIndex" style="text-align: center!important;">
                      <img style="height: 200px; margin-top: 5px; width: 65%;!important; text-align: center!important;" class="card-img" :src="imgSrc" alt="Post" >
                    </div>
                    <!-- Feed react START -->
                    <ul class="nav nav-stack py-3 small">
                      <li class="nav-item" @click="transLiked(index)">
                        <a class="nav-link active" href="#!">
                          <i class="bi bi-hand-thumbs-up-fill pe-1">
                          </i>点赞({{ item.likes }})</a>
                      </li>
                      <li class="nav-item" @click="transFavorites(index)">
                        <a class="nav-link active" href="#!">
                          <i class="bi bi-star-fill pe-1">
                          </i>收藏({{ item.favorites }})</a>
                      </li>
                      <li class="nav-item" @click="transCommentFold(index)">
                        <a class="" href="#!">
                          <i class="bi bi-chat-fill pe-1">
                          </i>{{ item.commentIsFold ? `展开评论` : '收起评论' }}</a>
                      </li>
                    </ul>
                    <!-- Feed react END -->
                    <!-- Add comment -->
                    <ul v-show="!item.commentIsFold" class="comment-wrap list-unstyled">
                      <!-- Comment item START -->
                      <div class="d-flex mb-3">
                        <!-- Avatar -->
                        <div class="avatar avatar-xs me-2">
                          <a href="###">
                            <img class="avatar-img rounded-circle" :src="userIco" alt="">
                          </a>
                        </div>
                        <!-- Comment box -->
                        <form class="w-100">
                          <el-input v-model="newComment" autosize type="textarea" placeholder="说点什么~"/>
                          <div style="text-align: right">
                            <el-button @click="commentCommit(index)" style="margin-top: 5px;" type="primary" size="small"
                                       round>提交
                            </el-button>
                          </div>
                        </form>
                      </div>
                      <!-- Comment wrap START -->
                      <li class="comment-item" v-for="(com,comIndex) in item.commentsList" :key="comIndex">
                        <div class="d-flex position-relative">
                          <!-- Avatar -->
                          <div class="avatar avatar-xs">
                            <a href="#!">
                              <img class="avatar-img rounded-circle" :src="com.createRavatar" alt="">
                            </a>
                          </div>
                          <div class="ms-2">
                            <!-- Comment by -->
                            <div class="bg-light rounded-start-top-0 p-3 rounded" style="padding: 8px">
                              <div class="d-flex justify-content-between" style="display: flex;
                            flex-direction: row;
                            align-items: center;">
                                <h6 style="margin: 0px;display: inline-block; ">
                                  <a href="#!">{{ com.createBy }}</a>
                                </h6>
                                <small style="float: right" class="ms-2">{{ com.createTime }}
                                  <span>
                                  <a href="#" class="text-secondary btn btn-secondary-soft-hover py-1 px-2"
                                     id="cardNotiAction" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="bi bi-three-dots"></i>
                                  </a>
                                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cardNotiAction">
                                    <div v-if="com.createId === userInfo.uid">
                                    <li @click="commentDelete(index,comIndex)">
                                      <a class="dropdown-item" href="###">
                                        <i class="bi bi-check-lg fa-fw pe-2">
                                        </i>刪除评论</a>
                                    </li>
                                    </div>
                                  </ul>
                                </span>
                                </small>
                              </div>
                              <p class="small mb-0" style="word-break: break-all;word-wrap: break-word;height: auto">
                                {{ com.commentContext }}</p>
                            </div>
                            <!-- Comment react -->
                            <ul class="nav nav-divider py-2 small">
                            </ul>
                          </div>
                        </div>
                      </li>
                      <div style="padding: 5px; display: flex;
                    flex-direction: row;
                    align-items: center; ">
                        <el-button style="display: inline-block" @click="loadMoreComment(index)"
                                   :disabled="!item. commentLoading" size="small"
                                   type="primary" round>点击加载更多评论
                        </el-button>
                      </div>
                    </ul>
                  </div>
                  <!-- Card body END -->
                  <!-- Card footer START -->
                </div>
              </div>
            </div>
            <div class="card-body p-2">
              <div class=" border-0 py-3 text-center position-relative d-grid pt-0">
                <el-button @click="loadMorePost" type="primary" :disabled="!postLoading">
                  {{ postLoading ? '点击加载更多' : '已经到底啦~' }}
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  </body>
  <div class="modal fade" id="feedActionPhoto" tabindex="-1" aria-labelledby="feedActionPhotoLabel" aria-hidden="ture">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <!-- Modal feed header START -->
        <div class="modal-header">
          <h5 class="modal-title" id="feedActionPhotoLabel">添加图片</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
          </button>
        </div>
        <!-- Modal feed header END -->
        <!-- Modal feed body START -->
        <div class="modal-body">
          <!-- Add Feed -->
          <div class="d-flex mb-3">
            <!-- Avatar -->
            <div class="avatar avatar-xs me-2">
              <img class="avatar-img rounded-circle" :src="userIco" alt="">
            </div>
            <!-- Feed box -->
            <form class="w-100">
              <textarea v-model="tempPost.text" class="form-control pe-4 fs-3 lh-1 border-0" rows="10"
                        placeholder="分享你的想法">
              </textarea>
            </form>
          </div>
          <!-- Dropzone photo START -->
          <el-upload multiple class="upload-demo" drag
                     action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d51"
                     :show-file-list="false" :on-change="(uploadFile)=>myUploadChange(uploadFile)"
                     :file-list="tempPost.images" :auto-upload="false">
            <el-icon class="el-icon--upload">
              <upload-filled/>
            </el-icon>
            <div class="el-upload__text">Drop file here or
              <em>click to upload</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">Upload up to four photos</div>
            </template>
          </el-upload>
          <ul id="images" class="el-upload-list el-upload-list--picture-card" v-for="imgItem in tempPost.images"
              :key="imgItem.index">
            <li class="el-upload-list__item is-success" tabindex="0">
              <img class="el-upload-list__item-thumbnail" :src="imgItem.imgSrc" alt="">
              <!--v-if-->
              <label class="el-upload-list__item-status-label">
                <i class="el-icon el-icon--upload-success el-icon--check">
                  <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
                    <path fill="currentColor"
                          d="M406.656 706.944 195.84 496.256a32 32 0 1 0-45.248 45.248l256 256 512-512a32 32 0 0 0-45.248-45.248L406.592 706.944z">
                    </path>
                  </svg>
                </i>
              </label>
              <span class="el-upload-list__item-actions">
                <span class="el-upload-list__item-delete" @click="imageRemove(imgItem.index)">
                  <i class="el-icon el-icon--delete">
                    <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
                      <path fill="currentColor"
                            d="M160 256H96a32 32 0 0 1 0-64h256V95.936a32 32 0 0 1 32-32h256a32 32 0 0 1 32 32V192h256a32 32 0 1 1 0 64h-64v672a32 32 0 0 1-32 32H192a32 32 0 0 1-32-32V256zm448-64v-64H416v64h192zM224 896h576V256H224v640zm192-128a32 32 0 0 1-32-32V416a32 32 0 0 1 64 0v320a32 32 0 0 1-32 32zm192 0a32 32 0 0 1-32-32V416a32 32 0 0 1 64 0v320a32 32 0 0 1-32 32z">
                      </path>
                    </svg>
                  </i>
                </span>
              </span>
            </li>
          </ul>
        </div>
        <div class="modal-footer ">
          <button ref="closeModel" type="button" class="btn btn-danger-soft me-2" data-bs-dismiss="modal"
                  @click="modalCancel">关闭
          </button>
          <button type="button" class="btn btn-success-soft" @click="upload">发送</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import headerNav from "@/components/Parts/headerNav"
import indexLeft from '@/components/Parts/indexLeft'
import {ElMessage} from "element-plus";
import messageBox from "@/utils/tools";

export default {
  name: "groupDetail",
  props:['group_info'],
  components:{
    'IndexLeft':indexLeft,
    'HeaderNav':headerNav
  },
  // beforeMount() {
  //   this.groupInfo = JSON.parse(this.group_info)
  //   //需要发出请求传入的参数应该有小组的ID this.$route.query.groupId
  //   //后端获取该小组最近一天所加入的人的信息如果超过10就取前10个的
  //   this.personInformation = this.personInformation.concat(this.testPersonInformation)
  //   //.length获取目前总人数 this.groupPersonNum = .length
  //
  // },
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
      userIco: require("@/static/imgs/default_ico.jpg"),
      postLoading: false,
      postPageIndex: 0,
      dialogVisible: false,
      disabled: false,
      newComment: '',
      userInfo: '',
      postList: [],
      tempPost: {
        text: '',
        images: []
      },
    }
  },
  async beforeMount() {
    if (this.$cookies.isKey("tokens")) {
      this.userInfo = JSON.parse(localStorage.getItem("userCommonInfo"))
      this.userIco = this.userInfo.portrait
    }
    this.groupInfo = JSON.parse(this.group_info)
    await this.getPost(this.postPageIndex)
  },
  methods:
      {
        async getPost(index){
          const url = "/usd/group/group-post/"+this.group_info.gid+"/" + index + "/8/" + this.$cookies.get("tokens").accessToken
          await this.$axios.get(url)
              .then(res => {
                if (res.data.code === 200) {
                  for (let i of res.data.data) {
                    let conent = JSON.parse(i.context)
                    this.postList.push({
                      pid: i.pid,
                      createrAvatar: i.createrAvatar,
                      text: conent.text,
                      images: conent.images,
                      createTime: i.createTime,
                      createUser: i.createUser,
                      likes: i.likes,
                      favorites: i.favorites,
                      commentIndex: 0,
                      commentsList: [],
                      commentLoading: false,
                      commentIsFold: true
                    })
                  }
                  this.postLoading = res.data.data.length !== 8
                } else {
                  messageBox(res.data.msg, "error")
                }
              })
        },
        myUploadChange(uploadFile) {
          let imgUrlSuccess = URL.createObjectURL(uploadFile.raw)
          let index = this.tempPost.images.length
          if (index >= 4) {
            messageBox("最多上传四张！", "warning")
            return
          }
          this.tempPost.images.push({
            index: index,
            name: uploadFile.name,
            imgSrc: imgUrlSuccess,
            imageRaw: uploadFile.raw
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
        async upload() {
          let images = []
          for (let i of this.tempPost.images) {
            await this.toBase64(i.imageRaw)
                .then(res => {
                  images.push(res)
                })
          }
          this.tempPost.images = images
          const url = "/usd/post/upload/" + this.$cookies.get("tokens").accessToken
          const post = {
            groupId: this.group_info.gid,
            connect: JSON.stringify(this.tempPost)
          }
          await this.$axios.post(url, post)
              .then(res => {
                if (res.data.code === 200) {
                  messageBox("发布成功", "success")
                  this.$refs.closeModel.click()
                } else {
                  messageBox(res.data.msg, "error")
                }
              }).catch(err => {
                messageBox(err, "error")
              })
        },
        imageRemove(imageUid) {
          for (let i = 0; i < this.tempPost.images.length; i++) {
            if (this.tempPost.images[i].index === imageUid) {
              this.tempPost.images.splice(i, 1);
            }
          }
        },
        modalCancel() {
          this.tempPost.images = []
        },
        async commentCommit(index) {
          //输入为空评论失败
          if (this.newComment.length === 0) {
            messageBox("请至少输入一个文字~", "warning")
            return;
          }
          const url = "/usd/post/comment/" + this.postList[index].pid + "/" + this.$cookies.get("tokens").accessToken
          await this.$axios.post(url, {content: JSON.stringify(this.newComment)})
              .then(res => {
                if (res.data.code === 200) {
                  this.postList[index].commentsList.splice(0, 0, {
                    commentContext: this.newComment,
                    createTime: this.getNowDate(),
                    createBy: this.userInfo.nickName,
                    createRavatar: this.userInfo.portrait,
                    createId: this.userInfo.uid,
                    cid: res.data.data.id
                  })
                  this.newComment = "";
                } else {
                  messageBox(res.data.msg, "error")
                }
              }).catch(err => {
                messageBox(err, "error")
              })
        },
        async commentDelete(index, comIndex) {
          const url = "/usd/post/delete-comment/" + this.postList[index].pid + "/" + this.postList[index].commentsList[comIndex].cid + "/" + this.$cookies.get("tokens").accessToken
          await this.$axios.post(url)
              .then(res => {
                if (res.data.code === 200) {
                  this.postList[index].commentsList.splice(comIndex, 1)
                } else {
                  messageBox(res.data.msg, "error")
                }
              })
              .catch(err => {
                messageBox(err, "error")
              })

        },
        async transCommentFold(index) {
          if (this.postList[index].commentIsFold) {
            const url = "/usd/post/get-comment/" + this.postList[index].pid + "/" + this.postList[index].commentIndex + "/8/" + this.$cookies.get("tokens").accessToken
            await this.$axios.get(url)
                .then(res => {
                  if (res.data.code === 200) {
                    if (res.data.data === null) {
                      messageBox("没有更多评论啦~", "success")
                      this.postList[index].commentLoading = false
                    } else {
                      this.postList[index].commentsList = res.data.data
                      this.postList[index].commentLoading = res.data.data.length >= 8
                      this.postList[index].commentIndex += 2
                    }

                  } else {
                    messageBox(res.data.msg, "error")
                  }
                }).catch(err => {
                  messageBox(err, "error")
                })
            this.postList[index].commentIsFold = !this.postList[index].commentIsFold
          } else {
            this.postList[index].commentIsFold = !this.postList[index].commentIsFold
          }
        },
        async loadMoreComment(index) {
          const url = "/usd/post/get-comment/" + this.postList[index].pid + "/" + this.postList[index].commentIndex + "/8/" + this.$cookies.get("tokens").accessToken
          await this.$axios.get(url)
              .then(res => {
                if (res.data.code === 200) {
                  if (res.data.data === null) {
                    messageBox("没有更多评论啦~", "success")
                    this.postList[index].commentLoading = false
                  } else {
                    this.postList[index].commentsList = this.postList[index].commentsList.concat(res.data.data)
                    this.postList[index].commentLoading = res.data.data.length >= 8
                    this.postList[index].commentIndex += 1
                  }
                } else {
                  messageBox(res.data.msg, "error")
                }
              }).catch(err => {
                messageBox(err, "error")
              })
        },
        async transLiked(index) {
          const url = "/usd/post/like-post/"+this.postList[index].pid+"/"+this.$cookies.get("tokens").accessToken
          await this.$axios.post(url)
              .then(res=>{
                if(res.data.code !== 200){
                  messageBox(res.data.msg,"error")
                }
                else{
                  this.postList[index].likes +=1;
                }
              }).catch(err=>{
                messageBox(err,"error")
              })
        },
        async transFavorites(index) {
          const url = "/usd/post/favorite/add/"+this.postList[index].pid+"/"+this.$cookies.get("tokens").accessToken
          await this.$axios.post(url)
              .then(res=>{
                if(res.data.code === 200){
                  this.postList[index].favorites+=1
                  messageBox("收藏成功~","success")
                }
                else{
                  if(res.data.code === 409)
                    messageBox("已经收藏了~","warning")
                  else
                    messageBox(res.data.msg,"error")
                }
              }).catch(err=>{
                messageBox(err,"error")
              })
        },
        loadMorePost() {
          let winHeight = window.innerHeight || document.documentElement.clientHeight;
          let docHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
          this.postPageIndex = this.postPageIndex + 1
          this.getPost(this.postPageIndex)
          document.documentElement.scrollTop = docHeight - winHeight - 1
        },
        getNowDate() {
          let date = new Date();
          let yy = date.getFullYear();//年
          let mm = date.getMonth() + 1;//月
          let dd = date.getDate();//日
          let hh = date.getHours();//时
          let mf = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
          let ss = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
          return yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss;
        }
      }

}
</script>

<style scoped>
@import url(@/css/all.min.css);
@import url(@/css/style.css);
@import url(@/css/css2.css);
</style>
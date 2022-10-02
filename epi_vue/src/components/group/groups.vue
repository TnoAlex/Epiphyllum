<template>
  <head>
    <title>Social - Network,Community and Event Theme</title><!-- Meta Tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="author" content="Webestica.com">
    <meta name="description" content="Bootstrap based News, Magazine and Blog Theme">
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
                  <span class="btn btn-primary-soft  w-100"  data-bs-toggle="modal"
                     data-bs-target="#modalCreateGroup"> <i class="fa-solid fa-plus pe-1"></i> 创建一个群组</span>
                </div>
              </div>
            </div>


            <div class="card-body">

              <ul class="nav nav-tabs nav-bottom-line justify-content-center justify-content-md-start">
                <li @click="tabTranslate(1)" class="nav-item"> <a class="nav-link active" data-bs-toggle="tab" href="#tab-1">未加入
                </a></li>
                <li @click="tabTranslate(2)" class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#tab-2">已加入 </a></li>
              </ul>
              <div class="tab-content mb-0 pb-0">

                <div class="tab-pane fade show active" id="tab-1">
                  <div class="row g-4">
                    <div class="col-sm-6 col-lg-4" v-for="(item,index) in groupJoinedInformation " :key="index">
                      <div class="card">
                        <div class="h-80px rounded-top"
                              :style="{backgroundImage:`url(${item.backgroundImageSrc})`,
                                      'background-position':'center',
                                      'background-size':'cover',
                                      'background-repeat':'no-repeat'
                                      }">
                        </div>
                        <div class="card-body text-center pt-0">
                          <div class="avatar avatar-lg mt-n5 mb-3"><a href="group-details.html"><img
                              class="avatar-img rounded-circle border border-white border-3 bg-white"
                              :src="item.backgroundImageSrc" alt=""></a></div><!-- Info -->
                          <h5 class="mb-0">
                            <router-link :to="{path:'/groupDetail',query:{groupId:item.id,
                                                                          bigImageSrc:item.bigImageSrc,
                                                                          groupName:item.groupName,
                                                                          createTime:item.createTime,
                                                                          groupDescription: item.groupDescription
                                                                          }}">{{item.groupName}}</router-link>
                          </h5>
                          <div>
                            <small>
                              <span class="iconfont icon-yuanshuju-chuangjianren"></span>{{item.createPersonName}}
                            </small>
                          </div>

                          <div>
                            <small>
                              <span class="iconfont icon-yuanshuju-chuangjianshijian"></span> {{ item.createTime }}
                            </small>
                          </div>
                          <ul class="avatar-group list-unstyled align-items-center justify-content-center mb-0 mt-3">
                            <li  class="avatar avatar-xs" v-for="(avatarImgSrc,avatarIndex) in item.avatarList.slice(0,5)" :key="avatarIndex">
                              <img  class="avatar-img rounded-circle" :src="avatarImgSrc.littleAvatarImage" alt="avatar">
                            </li>
                          </ul>
                        </div>

                        <div class="card-footer text-center" >
                          <span class="btn btn-success-soft"  style="" @click="joinGroup(index)" >加入群组
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="tab-pane fade" id="tab-2">
                  <div class="row g-4">
                    <div class="col-sm-6 col-lg-4" v-for="(item,index) in groupNoJoinedInformation " :key="index">
                      <div class="card">
                        <div class="h-80px rounded-top"
                             :style="{backgroundImage:`url(${item.backgroundImageSrc})`,
                                      'background-position':'center',
                                      'background-size':'cover',
                                      'background-repeat':'no-repeat'
                                      }">
                        </div><!-- Card body START -->
                        <div class="card-body text-center pt-0">
                          <!-- Avatar -->
                          <div class="avatar avatar-lg mt-n5 mb-3"><a href="group-details.html"><img
                              class="avatar-img rounded-circle border border-white border-3 bg-white"
                              :src="item.backgroundImageSrc" alt=""></a></div><!-- Info -->
                          <h5 class="mb-0"><span >{{item.groupName}}</span></h5>
                          <div>
                            <small>
                              <span class="iconfont icon-yuanshuju-chuangjianren"></span>  {{item.createPersonName}}
                            </small>
                          </div>

                          <div>
                            <small>
                              <span class="iconfont icon-yuanshuju-chuangjianshijian"></span>{{item.createTime}}
                            </small>
                          </div>


                          <ul class="avatar-group list-unstyled align-items-center justify-content-center mb-0 mt-3">
                            <li  v-for="(avatarImgSrc,avatarIndex) in item.avatarList.slice(0,5)" :key="avatarIndex" class="avatar avatar-xs">
                              <img  class="avatar-img rounded-circle" :src="avatarImgSrc.littleAvatarImage"
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
                    <input type='file' id="avatarUpload" accept=".png, .jpg, .jpeg" @change="change($event)"><label for="avatarUpload"></label>
                  </div><!-- Avatar preview -->
                  <div class="avatar avatar-xl position-relative">
                    <img id="avatar-preview" class="avatar-img rounded-circle border border-white border-3 shadow"
                         :src="imageUrl ? imageUrl : baseImg" alt=""></div>
                </div><!-- Avatar remove button -->
<!--                <div class="avatar-remove"><button type="button" id="avatar-reset-img"-->
<!--                                                   class="btn btn-light">删除</button></div>-->
              </div><!-- Avatar upload END -->
            </div><!-- Select audience -->


            <!-- Invite friend -->

            <!-- Group description -->
            <div class="mb-3"><label class="form-label">群组简介 </label><textarea ref="groupDescriptionRef" class="form-control"
                                                                                            rows="3" placeholder="添加群组简介"></textarea></div>
          </form><!-- Form END -->
        </div><!-- Modal footer -->
        <div class="modal-footer">
          <span  class="btn btn-success-soft" @click="createGroup">立即创建</span>

        </div>
      </div>
    </div>
  </div>
</template>
<script>
import headerNav from "@/components/UniversalComponents/headerNav"
import indexLeft from '@/components/UniversalComponents/indexLeft'
import {ElMessage} from 'element-plus'
export default {
  name: "groups",
  components:{
    'IndexLeft':indexLeft,
    'HeaderNav':headerNav
  },
  data()
  {
    return {
      baseImg:"/047.jpg",
      imageUrl:"",
      pageIndex:0,
      tabIndex:1,
      groupJoinedInformation:[],
      groupNoJoinedInformation:[],
      testGroupJoinedInformation:[
        {
          id:"123456",
          isJoined:true,
          backgroundImageSrc:"/047.jpg",
          groupDescription:"这是关于群组的介绍",
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
          ]
        },
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:true,
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
        {
          id:"123456",
          backgroundImageSrc:"/047.jpg",
          groupDescription:"这是关于群组的介绍",
          isJoined:true,
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:true,
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:true,
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:true,
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
      ],
      testGroupNoJoinedInformation:[
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:false,
          bigImageSrc:"/047.jpg",
          groupName:"前端开发",
          createPersonName:"阿庆",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},

          ]
        },
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:true,
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:true,
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:false,
          bigImageSrc:"/047.jpg",
          groupName:"前端开发",
          createPersonName:"阿庆",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:true,
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
        {
          id:"123456",
          groupDescription:"这是关于群组的介绍",
          backgroundImageSrc:"/047.jpg",
          isJoined:true,
          bigImageSrc:"/047.jpg",
          groupName:"后端开发",
          createPersonName:"冀欢",
          createTime:"2022-9-27 23:43:30",
          avatarList:[
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"},
            {littleAvatarImage:"/047.jpg"}
          ]
        },
      ]
    }
  },
  methods:{
    getMoreGroupInformation(tabIndex)
    {


      if(tabIndex===1)
      {
        //后台发出请求
        this.pageIndex = this.pageIndex+1
        this.groupJoinedInformation = this.groupJoinedInformation.concat(this.testGroupJoinedInformation)
        //如果请求到的数据为空
        ElMessage({
          message: '没有更多群组啦~',
          grouping: true,
          type: 'warning',
        })
      }
      else if(tabIndex===2)
      {
        //后台发出请求
        this.pageIndex = this.pageIndex+1
        this.groupNoJoinedInformation = this.groupNoJoinedInformation.concat(this.testGroupNoJoinedInformation)
        //如果请求到的数据为空
        ElMessage({
          message: '没有更多群组啦~',
          grouping: true,
          type: 'warning',
        })
      }

    },
    scrollFn()
    {
      let winHeight = window.innerHeight || document.documentElement.clientHeight;
      //滚出去的高度
      let st = document.documentElement.scrollTop || document.body.scrollTop;
      //文档高度
      let docHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
      if(winHeight+st>=docHeight-5)
      {
        console.log("触底")
        this.getMoreGroupInformation(this.tabIndex)
      }
    },
    tabTranslate(tabIndex)
    {
      this.tabIndex = tabIndex
      //首先将pageIndex清0
      this.pageIndex=0
      console.log(tabIndex)
      this.groupJoinedInformation=[]
      this.groupNoJoinedInformation=[]
      this.getMoreGroupInformation(tabIndex)
    },
    joinGroup(index)
    {
      //修改数据库
      console.log("加入成功",index)
      this.groupJoinedInformation.splice(index,1);
    },
    leaveGroup(index)
    {
      //修改数据库
      this.groupNoJoinedInformation.splice(index,1)
    },
    async change(e)
    {
      console.log(e.target.files[0].name);
      // 判断是不是规定格式
      // let name  =  e.target.files[0].name

      // 获取到第一张图片
      let file = e.target.files[0]
      await this.toBase64(file).then(res=>{
        console.log(res)
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
    createGroup()
    {
      let groupName  = this.$refs.groupNameRef.value
      let groupDescription =this.$refs.groupDescriptionRef.value
      if(groupName ===""||groupDescription===""||this.imageUrl==="")
      {
        ElMessage({
          message: '请将信息填写完整',
          grouping: true,
          type: 'warning',
        })
        return
      }

      let groupObj = {
        groupName:groupName,
        groupDescription:groupDescription,
        groupAvatar:this.imageUrl
      }
      console.log(groupObj)
      //给后台发送内容


      //当上传成功之后将所有值设置为空，然后退出模态框
      this.$refs.groupNameRef.value = ""
      this.$refs.groupDescriptionRef.value = ""
      this.imageUrl = ""
      //调用关闭
      this.$refs.modalClose.click()
    }
  },
  beforeMount() {
    this.getMoreGroupInformation(1)
  },
  mounted() {
  //监听滚动
  window.addEventListener("scroll",this.scrollFn);
  },
  beforeUnmount() {
  //移除滚动
    window.removeEventListener("scroll",this.scrollFn);
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
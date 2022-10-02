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
                  <h1 class="h4 card-title mb-lg-0">竞赛一览</h1>
                </div>
                <div class="col-sm-6 col-lg-3 ms-lg-auto">
                </div>
                <div class="col-sm-6 col-lg-3">
                  <!-- Button modal -->
                  <span class="btn btn-primary-soft  w-100"  data-bs-toggle="modal"
                        data-bs-target="#modalCreateGroup"> <i class="fa-solid fa-plus pe-1"></i> 发布一个竞赛</span>
                </div>






              </div>
            </div>


            <div class="card-body">

              <ul class="nav nav-tabs nav-bottom-line justify-content-center justify-content-md-start">
                <li @click="tabTranslate(1)" class="nav-item"> <a class="nav-link active" data-bs-toggle="tab" href="#tab-1">未报名
                </a></li>
                <li @click="tabTranslate(2)" class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#tab-2">已报名</a></li>
              </ul>
              <div class="tab-content mb-0 pb-0">

                <div class="tab-pane fade show active" id="tab-1">
                  <div class="row" v-for="(item,index) in competitionNoJoinedInformation " :key="index" >
                    <div class="d-sm-flex " style= " border-bottom: 2px solid rgb(238,240,242);
                     margin-bottom: 15px;height: 220px">
                      <!-- Avatar -->
                      <div style="width: 195px; height: 195px" class="avatar avatar-xl">
                        <img  class=" rounded border border-white border-3"
                          :src="item.competitionImageSrc" alt=""></div>
                      <div class="ms-sm-4 mt-2 mt-sm-0">
                        <!-- Info -->
                        <h4 class="mb-1"><span >{{item.competitionName}} </span></h4>

                        <div >
                          <el-tooltip  placement="top-start" effect="light">
                            <template #content>
                              <div style="width: 500px">
                                {{item.competitionDescription}}
                              </div>
                            </template>
                            <span  style=" font-size: 13px;
                                           line-height: 25px;
                                           overflow: hidden;
                                           text-overflow: ellipsis;
                                           display: -webkit-box;
                                           -webkit-box-orient: vertical;
                                           -webkit-line-clamp: 2;">
                              {{item.competitionDescription}}
                            </span>
                          </el-tooltip>
                        </div>
                        <h4 class="mb-1" style="margin-top: 10px"><span >竞赛简介 </span></h4>
                        <div >
                          <el-tooltip  placement="top-start" effect="light">
                            <template #content>
                              <div style="width: 500px">
                                {{item.competitionDescription}}
                              </div>
                            </template>
                            <span  style=" font-size: 13px;
                                           line-height: 25px;
                                           overflow: hidden;
                                           text-overflow: ellipsis;
                                           display: -webkit-box;
                                           -webkit-box-orient: vertical;
                                           -webkit-line-clamp: 2;">
                              {{item.competitionDescription}}
                            </span>
                          </el-tooltip>

                        </div>
<!--                        <ul class="nav nav-stack small" style="bottom: 0; position: absolute;margin-bottom: 20px">-->
                        <div>
                          <span style="font-size: 20px" class="iconfont icon-jigouduiwu"></span>
                          <span style="font-size: 18px">
                            队伍
                          </span>
                          <span style="font-size: 18px ;margin-left: 5px;font-weight: bold;color: rgb(0,101,255);">
                            {{ item.teamNum }}
                          </span>


                          <span style="font-size: 20px;margin-left: 15px" class="iconfont icon-shijian" ></span>
                          <span style=" font-size: 18px">
                            时间:
                          </span>
                          <span style="font-size: 18px;margin-left: 5px; ">
                             {{ item.duringTime }}
                          </span>

                        </div>

<!--                        </ul>-->
                      </div><!-- Button -->
                      <div class="d-flex mt-3 ms-auto" style="margin-top: 0px !important;">
                        <div class="dropdown">
                          <!-- Card share action menu --><button
                            class="icon-md btn btn-secondary-soft" type="button" id="profileAction"
                            data-bs-toggle="dropdown" aria-expanded="false"><i
                            class="bi bi-three-dots"></i></button>
                          <!-- Card share action dropdown menu -->
                          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileAction">
                            <li>

                              <span class="dropdown-item" @click="enroll">
                                <span  class="iconfont icon-baoming"></span>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;竞赛报名 </span>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="tab-pane fade show " id="tab-2">
                  <div class="row" v-for="(item,index) in competitionJoinedInformation " :key="index" >
                    <div class="d-sm-flex " style= " border-bottom: 2px solid rgb(238,240,242);
                     margin-bottom: 15px;height: 220px">
                      <!-- Avatar -->
                      <div style="width: 195px; height: 195px" class="avatar avatar-xl">
                        <img  class=" rounded border border-white border-3"
                              :src="item.competitionImageSrc" alt=""></div>
                      <div class="ms-sm-4 mt-2 mt-sm-0">
                        <!-- Info -->
                        <h4 class="mb-1"><span >{{item.competitionName}} </span></h4>

                        <div >
                          <el-tooltip  placement="top-start" effect="light">
                            <template #content>
                              <div style="width: 500px">
                                {{item.competitionDescription}}
                              </div>
                            </template>
                            <span  style=" font-size: 13px;
                                           line-height: 25px;
                                           overflow: hidden;
                                           text-overflow: ellipsis;
                                           display: -webkit-box;
                                           -webkit-box-orient: vertical;
                                           -webkit-line-clamp: 2;">
                              {{item.competitionDescription}}
                            </span>
                          </el-tooltip>
                        </div>
                        <h4 class="mb-1" style="margin-top: 10px"><span >竞赛简介 </span></h4>
                        <div >
                          <el-tooltip  placement="top-start" effect="light">
                            <template #content>
                              <div style="width: 500px">
                                {{item.competitionDescription}}
                              </div>
                            </template>
                            <span  style=" font-size: 13px;
                                           line-height: 25px;
                                           overflow: hidden;
                                           text-overflow: ellipsis;
                                           display: -webkit-box;
                                           -webkit-box-orient: vertical;
                                           -webkit-line-clamp: 2;">
                              {{item.competitionDescription}}
                            </span>
                          </el-tooltip>

                        </div>
                        <!--                        <ul class="nav nav-stack small" style="bottom: 0; position: absolute;margin-bottom: 20px">-->
                        <div>
                          <span style="font-size: 20px" class="iconfont icon-jigouduiwu"></span>
                          <span style="font-size: 18px">
                            队伍
                          </span>
                          <span style="font-size: 18px ;margin-left: 5px;font-weight: bold;color: rgb(0,101,255);">
                            {{ item.teamNum }}
                          </span>


                          <span style="font-size: 20px;margin-left: 15px" class="iconfont icon-shijian" ></span>
                          <span style=" font-size: 18px">
                            时间:
                          </span>
                          <span style="font-size: 18px;margin-left: 5px; ">
                             {{ item.duringTime }}
                          </span>

                        </div>

                        <!--                        </ul>-->
                      </div><!-- Button -->
                      <div class="d-flex mt-3 ms-auto" style="margin-top: 0px !important;">
                        <div class="dropdown">
                          <!-- Card share action menu --><button
                            class="icon-md btn btn-secondary-soft" type="button" id="profileAction"
                            data-bs-toggle="dropdown" aria-expanded="false"><i
                            class="bi bi-three-dots"></i></button>
                          <!-- Card share action dropdown menu -->
                          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileAction">
                            <li>
                              <span class="dropdown-item" @click="cancelEnroll">
                                <span  class="iconfont icon-tuichu"></span>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;取消报名 </span>
                            </li>
                            <li>
                                <span class="dropdown-item" @click="worksUpload">
                                <span  class="iconfont icon-yunduanshangchuan"></span>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作品上传 </span>
                            </li>
                          </ul>
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
          <h5 class="modal-title" id="modalLabelCreateGroup">发布竞赛</h5>
          <button ref="modalClose" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <!-- Form START -->
          <form>
            <!-- Group name -->
            <div class="mb-3">
              <label class="form-label">竞赛名</label>
              <input ref="competitionNameRef" type="text" class="form-control" placeholder="添加一个群组名">
            </div>
            <!-- Group picture -->
            <div class="mb-3">
              <label class="form-label">竞赛海报图</label>
              <!-- Avatar upload START -->
              <div class="d-flex align-items-center">
                <div class="avatar-uploader me-3">
                  <!-- Avatar edit -->
                  <div class="avatar-edit">
                    <input type='file' id="avatarUpload" accept=".png, .jpg, .jpeg" @change="change($event)"><label for="avatarUpload"></label>
                  </div><!-- Avatar preview -->
                  <div class="avatar avatar-xl position-relative">
                    <img id="avatar-preview" class="avatar-img  border border-white border-3 shadow"
                         :src="imageUrl ? imageUrl : baseImg" alt=""></div>
                </div><!-- Avatar remove button -->
                <!--                <div class="avatar-remove"><button type="button" id="avatar-reset-img"-->
                <!--                                                   class="btn btn-light">删除</button></div>-->
              </div><!-- Avatar upload END -->
            </div><!-- Select audience -->


            <!-- Invite friend -->

            <!-- Group description -->
            <div class="mb-3"><label class="form-label">组织机构</label><textarea ref="competitionInstitutionRef" class="form-control"
                                                                                   rows="3" placeholder="添加组织机构"></textarea></div>
            <div class="mb-3"><label class="form-label">竞赛简介</label><textarea ref="competitionDescriptionRef" class="form-control"
                                                                                  rows="3" placeholder="添加竞赛简介"></textarea></div>

          </form><!-- Form END -->
        </div><!-- Modal footer -->
        <div class="modal-footer">
          <span  class="btn btn-success-soft" @click="createCompetition">立即创建</span>

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
      competitionNoJoinedInformation:[],
      competitionJoinedInformation:[],
      testCompetitionNoJoinedInformation:[
        {
          competitionImageSrc:'/047.jpg',
          competitionName:"竞赛名称",
          teamNum:314,
          duringTime:"2022-09-05 ~ 2022-11-13",
          bonus:"10000",
          competitionDescription:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司",
          institution:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司"
        },
        {
          competitionImageSrc:'/047.jpg',
          competitionName:"竞赛名称",
          teamNum:314,
          duringTime:"2022-09-05 ~ 2022-11-13",
          bonus:"10000",
          competitionDescription:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司",
          institution:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司"
        },
        {
          competitionImageSrc:'/047.jpg',
          competitionName:"竞赛名称",
          teamNum:314,
          duringTime:"2022-09-05 ~ 2022-11-13",
          bonus:"10000",
          competitionDescription:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司",
          institution:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司"
        },
      ],
      testCompetitionJoinedInformation:[
        {
          competitionImageSrc:'/047.jpg',
          competitionName:"竞赛名称",
          teamNum:314,
          duringTime:"2022-09-05 ~ 2022-11-13",
          bonus:"10000",
          competitionDescription:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司",
          institution:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司"
        },
        {
          competitionImageSrc:'/047.jpg',
          competitionName:"竞赛名称",
          teamNum:314,
          duringTime:"2022-09-05 ~ 2022-11-13",
          bonus:"10000",
          competitionDescription:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司",
          institution:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司"
        },
        {
          competitionImageSrc:'/047.jpg',
          competitionName:"竞赛名称",
          teamNum:314,
          duringTime:"2022-09-05 ~ 2022-11-13",
          bonus:"10000",
          competitionDescription:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司",
          institution:"组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、" +
              "广东泰迪智能科技股份有限公司组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司组织单位：" +
              "泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司" +
              "组织单位：泰迪杯数据分析技能赛组织委员会、广东泰迪智能科技股份有限公司"
        },
      ],
    }
  },
  methods:{
    getMoreGroupInformation(tabIndex)
    {


      console.log(tabIndex)
      if(tabIndex===1)
      {
        //后台发出请求
        this.pageIndex = this.pageIndex+1
        this.competitionNoJoinedInformation = this.competitionNoJoinedInformation.concat(this.testCompetitionNoJoinedInformation)
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
        this.competitionJoinedInformation = this.competitionJoinedInformation.concat(this.testCompetitionJoinedInformation)
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
      this.competitionNoJoinedInformation=[]
      this.competitionJoinedInformation=[]
      this.getMoreGroupInformation(tabIndex)
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
    createCompetition()
    {
      let competitionName  = this.$refs.competitionNameRef.value
      let competitionDescription =this.$refs.competitionDescriptionRef.value
      let competitionInstitution = this.$refs.competitionInstitutionRef.value
      if(competitionName ===""||competitionDescription===""||this.imageUrl===""|| competitionInstitution==="")
      {
        ElMessage({
          message: '请将信息填写完整',
          grouping: true,
          type: 'warning',
        })
        return
      }

      let competitionObj = {
        groupName:competitionName,
        groupDescription:competitionDescription,
        competitionInstitution:competitionInstitution,
        groupAvatar:this.imageUrl
      }
      console.log(competitionObj)
      //给后台发送内容

      //当上传成功之后将所有值设置为空，然后退出模态框
      this.$refs.competitionNameRef.value = ""
      this.$refs.competitionDescriptionRef.value = ""
      this.imageUrl = ""
      this.$refs.competitionInstitutionRef.value = ""
      //调用关闭
      this.$refs.modalClose.click()
      ElMessage({
        message: '创建竞赛成功',
        grouping: true,
        type: 'success',
      })

    },
    enroll()
    {
      //报名，添加数据库信息
      ElMessage({
        message: '报名成功',
        grouping: true,
        type: 'success',
      })

    },
    worksUpload()
    {
      ElMessage({
        message: '作品上传成功',
        grouping: true,
        type: 'success',
      })
    },
    cancelEnroll()
    {
      ElMessage({
        message: '取消报名成功',
        grouping: true,
        type: 'success',
      })
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
@import url(@/Icon/baoming_shangchuan-tuichu/iconfont.css);
@import url(@/css/all.min.css);
@import url(@/css/style.css);
@import url(@/css/css2.css);

@import url(@/Icon/createPerson/iconfont.css);
@import url(@/Icon/createTime/iconfont.css);
</style>
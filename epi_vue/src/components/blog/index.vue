<template>
  <body style="overflow-x: visible !important;">
  <HeaderNav></HeaderNav>
  <main>
    <div class="container">
      <div class="row g-4">
        <IndexLeft></IndexLeft> <!--左侧-->
        <div class="col-md-8 col-lg-6 vstack gap-4 h-20px"> <!--中间-->
          <div class="card card-body">
            <div class="d-flex mb-3" style="margin-bottom: 0rem !important">
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
          <div v-if="postList.length!==0" >
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
                    <!-- Card feed action dropdown START -->
                    <div class="dropdown">
                      <a href="#" class="text-secondary btn btn-secondary-soft-hover py-1 px-2" id="cardFeedAction"
                         data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-three-dots">
                        </i>
                      </a>
                      <!-- Card feed action dropdown menu -->
                      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cardFeedAction">
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-bookmark fa-fw pe-2">
                            </i>Save post</a>
                        </li>
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-person-x fa-fw pe-2">
                            </i>Unfollow lori ferguson</a>
                        </li>
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-x-circle fa-fw pe-2">
                            </i>Hide post</a>
                        </li>
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-slash-circle fa-fw pe-2">
                            </i>Block</a>
                        </li>
                        <li>
                          <hr class="dropdown-divider">
                        </li>
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-flag fa-fw pe-2">
                            </i>Report post</a>
                        </li>
                      </ul>
                    </div>
                    <!-- Card feed action dropdown END -->
                  </div>
                </div>
                <!-- Card header END -->
                <!-- Card body START -->
                <div class="card-body" style="padding: 5px">
                  <md-editor-v3 previewOnly="true" v-model="item.text"/>
                  <div v-for="(imgSrc,imgIndex) in item.images" :key="imgIndex">
                    <img style="height: 200px; margin-top: 5px" class="card-img" :src="imgSrc" alt="Post">
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
                    <li class="nav-item" @click="transCommentFold">
                      <a class="" href="#!">
                        <i class="bi bi-chat-fill pe-1">
                        </i>{{ commentIsFold ? `展开评论` : '收起评论' }}</a>
                    </li>
                    <li class="nav-item dropdown ms-sm-auto">
                      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cardShareAction">
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-envelope fa-fw pe-2">
                            </i>Send via Direct Message</a>
                        </li>
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-bookmark-check fa-fw pe-2">
                            </i>Bookmark</a>
                        </li>
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-link fa-fw pe-2">
                            </i>Copy link to post</a>
                        </li>
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-share fa-fw pe-2">
                            </i>Share post via …</a>
                        </li>
                        <li>
                          <hr class="dropdown-divider">
                        </li>
                        <li>
                          <a class="dropdown-item" href="#">
                            <i class="bi bi-pencil-square fa-fw pe-2">
                            </i>Share to News Feed</a>
                        </li>
                      </ul>
                    </li>
                    <!-- Card share action END -->
                  </ul>
                  <!-- Feed react END -->
                  <!-- Add comment -->
                  <ul v-show="!commentIsFold" class="comment-wrap list-unstyled">
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
                          <el-button @click="commentCommit(index)" style="margin-top: 5px;" type="primary" size="small" round>提交</el-button>
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
                                    <li @click="commentDelete(index,comIndex)">
                                      <a class="dropdown-item" href="###">
                                        <i class="bi bi-check-lg fa-fw pe-2">
                                        </i>刪除评论</a>
                                    </li>
                                  </ul>
                                </span>
                              </small>
                            </div>
                            <p class="small mb-0">{{ com.commentContext }}</p>
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
                      <el-button style="display: inline-block" @click="loadMoreComment(index)" size="small"
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
        <div class="col-lg-3">
          <div class="row g-4">
            <!-- Card follow START -->
            <div class="col-sm-6 col-lg-12">
              <div class="card">
                <!-- 右侧推荐-->
                <div class="card-header pb-0 border-0">
                  <h5 class="card-title mb-0">推荐用户</h5>
                </div>
                <!-- Card header END -->
                <!-- Card body START -->
                <div class="card-body">
                  <!-- Connection item START -->
                  <div class="hstack gap-2 mb-3">
                    <!-- Avatar -->
                    <div class="avatar">
                      <a href="#">
                        <img class="avatar-img rounded-circle" src="../../img/11.jpg" alt="">
                      </a>
                    </div>
                    <!-- Title -->
                    <div class="overflow-hidden">
                      <a class="h6 mb-0" href="#!">冀欢</a>
                      <p class="mb-0 small text-truncate">职业</p>
                    </div>
                    <!-- Button -->
                    <a class="btn btn-primary rounded-circle icon-md ms-auto" href="#">
                      <i class="bi bi-person-check-fill">
                      </i>
                    </a>
                  </div>
                  <div class="hstack gap-2 mb-3">
                    <!-- Avatar -->
                    <div class="avatar">
                      <a href="#">
                        <img class="avatar-img rounded-circle" src="../../img/placeholder.jpg" alt="">
                      </a>
                    </div>
                    <!-- Title -->
                    <div class="overflow-hidden">
                      <a class="h6 mb-0" href="#!">阿庆</a>
                      <p class="mb-0 small text-truncate">职业</p>
                    </div>
                    <!-- Button -->
                    <a class="btn btn-primary-soft rounded-circle icon-md ms-auto" href="#">
                      <i class="fa-solid fa-plus">
                      </i>
                    </a>
                  </div>
                  <!-- Connection item END -->
                  <!-- View more button -->
                  <div class="d-grid mt-3">
                    <span class="btn btn-sm btn-primary-soft">查看更多</span>
                  </div>
                </div>
                <!-- Card body END -->
              </div>
            </div>
            <!-- Card follow START -->
            <!-- Card News START -->
            <div class="col-sm-6 col-lg-12">
              <div class="card">
                <!-- Card header START -->
                <div class="card-header pb-0 border-0">
                  <h5 class="card-title mb-0">最新话题</h5>
                </div>
                <!-- Card header END -->
                <!-- Card body START -->
                <div class="card-body">
                  <!-- News item -->
                  <div class="mb-3">
                    <h6 class="mb-0">
                      <a href="blog-details.html">Vue 零基础入门</a>
                    </h6>
                    <small>2小时</small>
                  </div>
                  <!-- News item -->
                  <!-- Load more comments -->
                  <a href="#!" role="button"
                     class="btn btn-link btn-link-loader btn-sm text-secondary d-flex align-items-center"
                     data-bs-toggle="button" aria-pressed="true">
                      <span style="margin-right: 2px" class="iconfont icon-gengduo">
                      </span>查看更多</a>
                </div>
                <!-- Card body END -->
              </div>
            </div>
            <!-- Card News END -->
          </div>
        </div>
      </div>
    </div>
    <!-- Container END -->
  </main>
  <!-- **************** MAIN CONTENT END **************** -->
  <!-- Main Chat START -->
  <div class="d-none d-lg-block">
    <!-- Button -->
    <a class="icon-md btn btn-primary position-fixed end-0 bottom-0 me-5 mb-5" data-bs-toggle="offcanvas"
       href="#offcanvasChat" role="button" aria-controls="offcanvasChat">
      <i class="bi bi-chat-left-text-fill">
      </i>
    </a>
    <!-- Chat sidebar START -->
    <div class="offcanvas offcanvas-end" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1"
         id="offcanvasChat">
      <!-- Offcanvas header -->
      <div class="offcanvas-header d-flex justify-content-between">
        <h5 class="offcanvas-title">Messaging</h5>
        <div class="d-flex">
          <!-- New chat box open button -->
          <a href="#" class="btn btn-secondary-soft-hover py-1 px-2">
            <i class="bi bi-pencil-square">
            </i>
          </a>
          <!-- Chat action START -->
          <div class="dropdown">
            <a href="#" class="btn btn-secondary-soft-hover py-1 px-2" id="chatAction" data-bs-toggle="dropdown"
               aria-expanded="false">
              <i class="bi bi-three-dots">
              </i>
            </a>
            <!-- Chat action menu -->
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="chatAction">
              <li>
                <a class="dropdown-item" href="#">
                  <i class="bi bi-check-square fa-fw pe-2">
                  </i>Mark all as read</a>
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  <i class="bi bi-gear fa-fw pe-2">
                  </i>Chat setting</a>
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  <i class="bi bi-bell fa-fw pe-2">
                  </i>Disable notifications</a>
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  <i class="bi bi-volume-up-fill fa-fw pe-2">
                  </i>Message sounds</a>
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  <i class="bi bi-slash-circle fa-fw pe-2">
                  </i>Block setting</a>
              </li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  <i class="bi bi-people fa-fw pe-2">
                  </i>Create a group chat</a>
              </li>
            </ul>
          </div>
          <!-- Chat action END -->
          <!-- Close -->
          <a href="#" class="btn btn-secondary-soft-hover py-1 px-2" data-bs-dismiss="offcanvas" aria-label="Close">
            <i class="fa-solid fa-xmark">
            </i>
          </a>
        </div>
      </div>
      <!-- Offcanvas body START -->
      <div class="offcanvas-body pt-0 custom-scrollbar">
        <!-- Search contact START -->
        <form class="rounded position-relative">
          <input class="form-control ps-5 bg-light" type="search" placeholder="Search..." aria-label="Search">
          <button class="btn bg-transparent px-3 py-0 position-absolute top-50 start-0 translate-middle-y"
                  type="submit">
            <i class="bi bi-search fs-5">
            </i>
          </button>
        </form>
        <!-- Search contact END -->
        <ul class="list-unstyled">
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative toast-btn" data-target="chatToast">
            <!-- Avatar -->
            <div class="avatar status-online">
              <img class="avatar-img rounded-circle" src="../../img/01.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Frances Guerrero</a>
              <div class="small text-secondary text-truncate">Frances sent a photo.</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">Just now</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative toast-btn" data-target="chatToast2">
            <!-- Avatar -->
            <div class="avatar status-online">
              <img class="avatar-img rounded-circle" src="../../img/02.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Lori Ferguson</a>
              <div class="small text-secondary text-truncate">You missed a call form Carolyn🤙</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">1min</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-offline">
              <img class="avatar-img rounded-circle" src="../../img/placeholder.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Samuel Bishop</a>
              <div class="small text-secondary text-truncate">Day sweetness why cordially 😊</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">2min</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar">
              <img class="avatar-img rounded-circle" src="../../img/04.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Dennis Barrett</a>
              <div class="small text-secondary text-truncate">Happy birthday🎂</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">10min</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar avatar-story status-online">
              <img class="avatar-img rounded-circle" src="../../img/05.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Judy Nguyen</a>
              <div class="small text-secondary text-truncate">Thank you!</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">2hrs</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-online">
              <img class="avatar-img rounded-circle" src="../../img/06.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Carolyn Ortiz</a>
              <div class="small text-secondary text-truncate">Greetings from Webestica.</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">1 day</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="flex-shrink-0 avatar">
              <ul class="avatar-group avatar-group-four">
                <li class="avatar avatar-xxs">
                  <img class="avatar-img rounded-circle" src="../../img/06.jpg" alt="avatar">
                </li>
                <li class="avatar avatar-xxs">
                  <img class="avatar-img rounded-circle" src="../../img/07.jpg" alt="avatar">
                </li>
                <li class="avatar avatar-xxs">
                  <img class="avatar-img rounded-circle" src="../../img/082.jpg" alt="avatar">
                </li>
                <li class="avatar avatar-xxs">
                  <img class="avatar-img rounded-circle" src="../../img/09.jpg" alt="avatar">
                </li>
              </ul>
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link text-truncate d-inline-block" href="#!">Frances, Lori, Amanda, Lawson</a>
              <div class="small text-secondary text-truncate">Btw are you looking for job change?</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">4 day</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-offline">
              <img class="avatar-img rounded-circle" src="../../img/082.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Bryan Knight</a>
              <div class="small text-secondary text-truncate">if you are available to discuss🙄</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">6 day</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar">
              <img class="avatar-img rounded-circle" src="../../img/09.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#">Louis Crawford</a>
              <div class="small text-secondary text-truncate">🙌Congrats on your work anniversary!</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">1 day</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-online">
              <img class="avatar-img rounded-circle" src="../../img/10.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Jacqueline Miller</a>
              <div class="small text-secondary text-truncate">No sorry, Thanks.</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">15, dec</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar">
              <img class="avatar-img rounded-circle" src="../../img/11.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#">Amanda Reed</a>
              <div class="small text-secondary text-truncate">Interested can share CV at.</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">18, dec</div>
          </li>
          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-online">
              <img class="avatar-img rounded-circle" src="../../img/12.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#">Larry Lawson</a>
              <div class="small text-secondary text-truncate">Hope you're doing well and Safe.</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap">20,dec</div>
          </li>
          <!-- Button -->
          <li class="mt-3 d-grid">
            <a class="btn btn-primary-soft" href="messaging.html">See all in messaging</a>
          </li>
        </ul>
      </div>
      <!-- Offcanvas body END -->
    </div>
    <!-- Chat sidebar END -->
    <!-- Chat END -->
    <!-- Chat START -->
    <div aria-live="polite" aria-atomic="true" class="position-relative">
      <div class="toast-container toast-chat d-flex gap-3 align-items-end">
        <!-- Chat toast START -->
        <div id="chatToast" class="toast mb-0 bg-mode" role="alert" aria-live="assertive" aria-atomic="true"
             data-bs-autohide="false">
          <div class="toast-header bg-mode">
            <!-- Top avatar and status START -->
            <div class="d-flex justify-content-between align-items-center w-100">
              <div class="d-flex">
                <div class="flex-shrink-0 avatar me-2">
                  <img class="avatar-img rounded-circle" src="../../img/01.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <h6 class="mb-0 mt-1">Frances Guerrero</h6>
                  <div class="small text-secondary">
                    <i class="fa-solid fa-circle text-success me-1">
                    </i>Online
                  </div>
                </div>
              </div>
              <div class="d-flex">
                <!-- Call button -->
                <div class="dropdown">
                  <a class="btn btn-secondary-soft-hover py-1 px-2" href="#" id="chatcoversationDropdown"
                     data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
                    <i class="bi bi-three-dots-vertical">
                    </i>
                  </a>
                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="chatcoversationDropdown">
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-camera-video me-2 fw-icon">
                        </i>Video call</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-telephone me-2 fw-icon">
                        </i>Audio call</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-trash me-2 fw-icon">
                        </i>Delete</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-chat-square-text me-2 fw-icon">
                        </i>Mark as unread</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-volume-up me-2 fw-icon">
                        </i>Muted</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-archive me-2 fw-icon">
                        </i>Archive</a>
                    </li>
                    <li class="dropdown-divider">
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-flag me-2 fw-icon">
                        </i>Report</a>
                    </li>
                  </ul>
                </div>
                <!-- Card action END -->
                <a class="btn btn-secondary-soft-hover py-1 px-2" data-bs-toggle="collapse" href="#collapseChat"
                   aria-expanded="false" aria-controls="collapseChat">
                  <i class="bi bi-dash-lg">
                  </i>
                </a>
                <button class="btn btn-secondary-soft-hover py-1 px-2" data-bs-dismiss="toast" aria-label="Close">
                  <i class="fa-solid fa-xmark">
                  </i>
                </button>
              </div>
            </div>
            <!-- Top avatar and status END -->
          </div>
          <div class="toast-body collapse show" id="collapseChat">
            <!-- Chat conversation START -->
            <div class="chat-conversation-content custom-scrollbar h-200px">
              <!-- Chat time -->
              <div class="text-center small my-2">Jul 16,2022,06:15 am</div>
              <!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2">
                  <img class="avatar-img rounded-circle" src="../../img/01.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">Applauded no discovery😊</div>
                      <div class="small my-2">6:15 AM</div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Chat message right -->
              <div class="d-flex justify-content-end text-end mb-1">
                <div class="w-100">
                  <div class="d-flex flex-column align-items-end">
                    <div class="bg-primary text-white p-2 px-3 rounded-2">With pleasure</div>
                  </div>
                </div>
              </div>
              <!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2">
                  <img class="avatar-img rounded-circle" src="../../img/01.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">Please find the attached</div>
                      <!-- Files START -->
                      <!-- Files END -->
                      <div class="small my-2">12:16 PM</div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2">
                  <img class="avatar-img rounded-circle" src="../../img/01.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">How promotion excellent curiosity😮</div>
                      <div class="small my-2">3:22 PM</div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Chat message right -->
              <div class="d-flex justify-content-end text-end mb-1">
                <div class="w-100">
                  <div class="d-flex flex-column align-items-end">
                    <div class="bg-primary text-white p-2 px-3 rounded-2">And sir dare view.</div>
                    <!-- Images -->
                    <div class="d-flex my-2">
                      <div class="small text-secondary">5:35 PM</div>
                      <div class="small ms-2">
                        <i class="fa-solid fa-check">
                        </i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Chat time -->
              <div class="text-center small my-2">2 New Messages</div>
              <!-- Chat Typing -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2">
                  <img class="avatar-img rounded-circle" src="../../img/01.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-3 rounded-2">
                        <div class="typing d-flex align-items-center">
                          <div class="dot">
                          </div>
                          <div class="dot">
                          </div>
                          <div class="dot">
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- Chat conversation END -->
            <!-- Chat bottom START -->
            <div class="mt-2">
              <!-- Chat textarea -->
              <textarea class="form-control mb-sm-0 mb-3" placeholder="Type a message" rows="1">
                </textarea>
              <!-- Button -->
              <div class="d-sm-flex align-items-end mt-2">
                <button class="btn btn-sm btn-danger-soft me-2">
                  <i class="fa-solid fa-face-smile fs-6">
                  </i>
                </button>
                <button class="btn btn-sm btn-secondary-soft me-2">
                  <i class="fa-solid fa-paperclip fs-6">
                  </i>
                </button>
                <button class="btn btn-sm btn-success-soft me-2">Gif</button>
                <button class="btn btn-sm btn-primary ms-auto">Send</button>
              </div>
            </div>
            <!-- Chat bottom START -->
          </div>
        </div>
        <!-- Chat toast END -->
        <!-- Chat toast 2 START -->
        <div id="chatToast2" class="toast mb-0 bg-mode" role="alert" aria-live="assertive" aria-atomic="true"
             data-bs-autohide="false">
          <div class="toast-header bg-mode">
            <!-- Top avatar and status START -->
            <div class="d-flex justify-content-between align-items-center w-100">
              <div class="d-flex">
                <div class="flex-shrink-0 avatar me-2">
                  <img class="avatar-img rounded-circle" src="../../img/02.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <h6 class="mb-0 mt-1">Lori Ferguson</h6>
                  <div class="small text-secondary">
                    <i class="fa-solid fa-circle text-success me-1">
                    </i>Online
                  </div>
                </div>
              </div>
              <div class="d-flex">
                <!-- Call button -->
                <div class="dropdown">
                  <a class="btn btn-secondary-soft-hover py-1 px-2" href="#" id="chatcoversationDropdown2" role="button"
                     data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
                    <i class="bi bi-three-dots-vertical">
                    </i>
                  </a>
                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="chatcoversationDropdown2">
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-camera-video me-2 fw-icon">
                        </i>Video call</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-telephone me-2 fw-icon">
                        </i>Audio call</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-trash me-2 fw-icon">
                        </i>Delete</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-chat-square-text me-2 fw-icon">
                        </i>Mark as unread</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-volume-up me-2 fw-icon">
                        </i>Muted</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-archive me-2 fw-icon">
                        </i>Archive</a>
                    </li>
                    <li class="dropdown-divider">
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bi bi-flag me-2 fw-icon">
                        </i>Report</a>
                    </li>
                  </ul>
                </div>
                <!-- Card action END -->
                <a class="btn btn-secondary-soft-hover py-1 px-2" data-bs-toggle="collapse" href="#collapseChat2"
                   role="button" aria-expanded="false" aria-controls="collapseChat2">
                  <i class="bi bi-dash-lg">
                  </i>
                </a>
                <button class="btn btn-secondary-soft-hover py-1 px-2" data-bs-dismiss="toast" aria-label="Close">
                  <i class="fa-solid fa-xmark">
                  </i>
                </button>
              </div>
            </div>
            <!-- Top avatar and status END -->
          </div>
          <div class="toast-body collapse show" id="collapseChat2">
            <!-- Chat conversation START -->
            <div class="chat-conversation-content custom-scrollbar h-200px">
              <!-- Chat time -->
              <div class="text-center small my-2">Jul 16,2022,06:15 am</div>
              <!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2">
                  <img class="avatar-img rounded-circle" src="../../img/02.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">Applauded no discovery😊</div>
                      <div class="small my-2">6:15 AM</div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Chat message right -->
              <div class="d-flex justify-content-end text-end mb-1">
                <div class="w-100">
                  <div class="d-flex flex-column align-items-end">
                    <div class="bg-primary text-white p-2 px-3 rounded-2">With pleasure</div>
                  </div>
                </div>
              </div>
              <!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2">
                  <img class="avatar-img rounded-circle" src="../../img/02.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">Please find the attached</div>
                      <!-- Files START -->
                      <!-- Files END -->
                      <div class="small my-2">12:16 PM</div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2">
                  <img class="avatar-img rounded-circle" src="../../img/02.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">How promotion excellent curiosity😮</div>
                      <div class="small my-2">3:22 PM</div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Chat message right -->
              <div class="d-flex justify-content-end text-end mb-1">
                <div class="w-100">
                  <div class="d-flex flex-column align-items-end">
                    <div class="bg-primary text-white p-2 px-3 rounded-2">And sir dare view.</div>
                    <!-- Images -->
                    <div class="d-flex my-2">
                      <div class="small text-secondary">5:35 PM</div>
                      <div class="small ms-2">
                        <i class="fa-solid fa-check">
                        </i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Chat time -->
              <div class="text-center small my-2">2 New Messages</div>
              <!-- Chat Typing -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2">
                  <img class="avatar-img rounded-circle" src="../../img/02.jpg" alt="">
                </div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-3 rounded-2">
                        <div class="typing d-flex align-items-center">
                          <div class="dot">
                          </div>
                          <div class="dot">
                          </div>
                          <div class="dot">
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- Chat conversation END -->
            <!-- Chat bottom START -->
            <div class="mt-2">
              <!-- Chat textarea -->
              <textarea class="form-control mb-sm-0 mb-3" placeholder="Type a message" rows="1">
                </textarea>
              <!-- Button -->
              <div class="d-sm-flex align-items-end mt-2">
                <button class="btn btn-sm btn-danger-soft me-2">
                  <i class="fa-solid fa-face-smile fs-6">
                  </i>
                </button>
                <button class="btn btn-sm btn-secondary-soft me-2">
                  <i class="fa-solid fa-paperclip fs-6">
                  </i>
                </button>
                <button class="btn btn-sm btn-success-soft me-2">Gif</button>
                <button class="btn btn-sm btn-primary ms-auto">Send</button>
              </div>
            </div>
            <!-- Chat bottom START -->
          </div>
        </div>
        <!-- Chat toast 2 END -->
      </div>
    </div>
    <!-- Chat END -->
  </div>
  <!-- Main Chat END -->
  <!-- Modal create Feed START -->
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
                     :show-file-list="false" :on-change="(uploadFile) =>myUploadChange(uploadFile)"
                     :file-list="tempPost.images"
                     :on-preview="(uploadFile) => myPreview(uploadFile)" :auto-upload="false">
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
          <ul id="images" class="el-upload-list el-upload-list--picture-card" v-for="imgItem in tempPost.images" :key="imgItem.index">
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
        <!-- Modal feed body END -->
        <!-- Modal feed footer -->
        <div class="modal-footer ">
          <!-- Button -->
          <button ref="closeModel" type="button" class="btn btn-danger-soft me-2" data-bs-dismiss="modal"
                  @click="modalCancel">关闭
          </button>
          <button type="button" class="btn btn-success-soft" @click="upload">发送</button>
        </div>
        <!-- Modal feed footer -->
      </div>
    </div>
  </div>
</template>
<script>
import indexLeft from '@/components/UniversalComponents/indexLeft'
import headerNav from "@/components/UniversalComponents/headerNav";
import {ElMessage} from 'element-plus'


export default {
  name: "index",
  components:
      {
        'IndexLeft': indexLeft,
        'HeaderNav': headerNav
      },
  maraInfo: {
    title: "主页",
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
      userIco: require("@/static/imgs/default_ico.jpg"),
      postLoading: false,
      postPageIndex: 0,
      commentIsFold: true,
      commentText: "",//评论内容双向绑定
      dialogVisible: false,
      disabled: false,
      newComment:'',
      commentsList:{
        commentContext:'',
        createTime:'',
        createBy:'',
        createRavatar:''
      },
      postList: [],
      tempPost: {
        text: '',
        images: []
      },
    }
  },
  async beforeMount() {
    if (this.$cookies.isKey("tokens")) {
      this.userIco = JSON.parse(localStorage.getItem("userCommonInfo")).portrait
    }
    const url = "/usd/group/group-post/0/" + this.postPageIndex + "/8/" + this.$cookies.get("tokens").accessToken
    await this.$axios.get(url)
        .then(res => {
          if (res.data.code === 200) {
            for (let i of res.data.data) {
              let conetxt = JSON.parse(i.context)
              this.postList.push({
                pid: i.pid,
                createrAvatar: i.createrAvatar,
                text: conetxt.text,
                images: conetxt.images,
                createTime: i.createTime,
                createUser: i.createUser,
                likes: i.likes,
                favorites: i.favorites,
                commentsList:[]
              })
            }
            this.postLoading = res.data.data.length !== 8;
          } else {
            this.messageBox(res.data.msg, "error")
          }
        })
  },
  methods:
      {
        messageBox(msg, type) {
          ElMessage({
            showClose: false,
            message: msg,
            type: type,
            grouping: true
          })
        },
        myUploadChange(uploadFile) {
          let imgUrlSuccess = URL.createObjectURL(uploadFile.raw)
          let index = this.tempPost.images.length
          if (index >= 4) {
            this.messageBox("最多上传四张！", "warning")
            return
          }
          this.tempPost.images.push({
            index: index,
            name: uploadFile.name,
            imgSrc: imgUrlSuccess,
            imageRaw: uploadFile.raw
          })
        },
        myPreview(uploadFile) {
          console.log(uploadFile)
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
            groupId: "0",
            connect: JSON.stringify(this.tempPost)
          }
          await this.$axios.post(url, post)
              .then(res => {
                if (res.data.code === 200) {
                  this.messageBox("发布成功", "success")
                  this.$refs.closeModel.click()
                } else {
                  this.messageBox(res.data.msg, "error")
                }
              }).catch(err => {
                this.messageBox(err, "error")
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
            this.messageBox("请至少输入一个文字~","warning")
            return;
          }
          const url ="/usd/post/comment/"+this.postList[index].pid+"/"+this.$cookies.get("tokens").accessToken
          await this.$axios.post(url,{content:JSON.stringify(this.newComment)})
              .then(res=>{
                if(res.data.code === 200){
                  this.postList[index].commentsList.splice(0,0,{
                    commentContext: this.newComment,
                    createTime: this.getNowDate(),
                    createBy: JSON.parse(localStorage.getItem("userCommonInfo")).nickName,
                    createRavatar: JSON.parse(localStorage.getItem("userCommonInfo")).portrait
                  })
                  this.newComment = "";
                }
                else{
                  this.messageBox(res.data.msg,"error")
                }
              }).catch(err=>{
                this.messageBox(err,"error")
              })
        },
        commentDelete(index, comIndex) {
          this.postList[index].commentsList.splice(comIndex, 1);
          this.postList[index].comments = this.postList[index].comments - 1;
        },
        async transCommentFold() {
          this.commentIsFold = !this.commentIsFold;
        },
        loadMoreComment(index) {
          //发出请求，判断请求到的数量
          ElMessage({
            message: '没有更多评论啦~',
            grouping: true,
            type: 'success',
          })
          //将请求到的评论赋值
          let getCommentList = [{
            avatarImg: "/031.jpg",
            name: "阿庆",
            time: "2022/9/24",
            text: "Removed demands expense account in outward tedious do. Particular way thoroughly unaffected projection.",
          }]
          //将请求到的评论拼接
          this.postList[index].commentsList = this.postList[index].commentsList.concat(getCommentList);
        },
        transLiked(index) {
          //前端将点数数增加
          this.postList[index].likes = this.postList[index].likes + 1;
          //将点赞信息上传到后台
        },
        transFavorites(index){
          this.postList[index].favorites = this.postList[index].favorites+ 1;
        },
        loadMorePost() {
          let winHeight = window.innerHeight || document.documentElement.clientHeight;
          let docHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
          this.postPageIndex = this.postPageIndex + 1;
          this.getPosts(this.postPageIndex)
          document.documentElement.scrollTop = docHeight - winHeight - 1
        },
        getPosts(postPageIndex) {
          //向后端发起请求当前数据库中的贴子
          //如果获取的贴子数量为0则将this.postLoading改为false
          // if()
          // {
          //
          // }
          console.log("请求第几页的贴子", postPageIndex)
          this.postList = this.postList.concat(this.postList)
        },
        getNowDate() {
          let date = new Date();
          let yy = date.getFullYear();//年
          let mm = date.getMonth() + 1;//月
          let dd = date.getDate();//日
          let hh = date.getHours();//时
          let mf = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
          let ss = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
          let str = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss;
          return str;
        }
      }


}

</script>

<style>
@import url(@/Icon/threePoint01/iconfont.css);
@import url(@/css/font.css);
@import url(@/css/all.min.css);
@import url(@/css/style.css);
@import url(@/css/css2.css);
</style>


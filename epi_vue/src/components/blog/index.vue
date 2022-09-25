<template>
  <body>
  <!-- ======================= Header START -->
  <header class="navbar-light fixed-top header-static bg-mode">
    <!-- Logo Nav START -->
    <nav class="navbar navbar-expand-lg">
      <div class="container">
        <!-- Logo START --><a class="navbar-brand" href=""><img class="light-mode-item navbar-brand-item"
                                                                src="../../img/logo.svg" alt="logo"><img
          class="dark-mode-item navbar-brand-item" src="../../img/logo.svg"
          alt="logo"></a><!-- Logo END -->
        <!-- Responsive navbar toggler -->
        <button class="navbar-toggler ms-auto icon-md btn btn-light p-0" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse"
                aria-expanded="false" aria-label="Toggle navigation"><span
            class="navbar-toggler-animation"><span></span><span></span><span></span></span></button>
        <!-- Main navbar START -->
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <!-- Nav Search START -->
          <div class="nav mt-3 mt-lg-0 flex-nowrap align-items-center px-4 px-lg-0">
            <div class="nav-item w-100">
              <form class="rounded position-relative"><input class="form-control ps-5 bg-light" type="search"
                                                             placeholder="搜索" aria-label="Search">
                <button
                    class="btn bg-transparent px-2 py-0 position-absolute top-50 start-0 translate-middle-y"
                    type="submit"><i class="bi bi-search fs-5"></i></button>
              </form>
            </div>
          </div><!-- Nav Search END -->
          <ul class="navbar-nav navbar-nav-scroll ms-auto">
            <!-- Nav item 1 Demos -->
            <li class="nav-item dropdown"><a class="nav-link active dropdown-toggle" href="#" id="homeMenu"
                                             data-bs-toggle="dropdown" aria-haspopup="true"
                                             aria-expanded="false">Demo</a>
              <ul class="dropdown-menu" aria-labelledby="homeMenu">
                <li><a class="dropdown-item active" href="">主页</a></li>
                <!--                <li><a class="dropdown-item" href="index-classic.html">Home classic</a></li>-->
                <!--                <li><a class="dropdown-item" href="index-post.html">Home post</a></li>-->
                <!--                <li><a class="dropdown-item" href="index-video.html">Home video</a></li>-->
                <!--                <li><a class="dropdown-item" href="index-event.html">Home event</a></li>-->
                <!--                <li><a class="dropdown-item" href="landing.html">Landing page</a></li>-->
                <!--                <li><a class="dropdown-item" href="app-download.html">App download</a></li>-->
                <!--                <li class="dropdown-divider"></li>-->
                <!--                <li><a class="dropdown-item" href=""><i-->
                <!--                    class="text-success fa-fw bi bi-cloud-download-fill me-2"></i>Buy Social!</a></li>-->
              </ul>
            </li><!-- Nav item 2 Pages -->


            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="pagesMenu"
                                             data-bs-toggle="dropdown" aria-haspopup="true"
                                             aria-expanded="false">Pages</a>
              <ul class="dropdown-menu" aria-labelledby="pagesMenu">
                <li><a class="dropdown-item" href="albums.html">Albums</a></li>
                <li><a class="dropdown-item" href="celebration.html">Celebration</a></li>
                <li><a class="dropdown-item" href="messaging.html">Messaging</a></li><!-- Dropdown submenu -->
                <li class="dropdown-submenu dropend"><a class="dropdown-item dropdown-toggle" href="#!">Profile</a>
                  <ul class="dropdown-menu" data-bs-popper="none">
                    <li><a class="dropdown-item" href="my-profile.html">Feed</a></li>
                    <li><a class="dropdown-item" href="my-profile-about.html">About</a></li>
                    <li><a class="dropdown-item" href="my-profile-connections.html">Connections</a></li>
                    <li><a class="dropdown-item" href="my-profile-media.html">Media</a></li>
                    <li><a class="dropdown-item" href="my-profile-videos.html">Videos</a></li>
                    <li><a class="dropdown-item" href="my-profile-events.html">Events</a></li>
                    <li><a class="dropdown-item" href="my-profile-activity.html">Activity</a></li>
                  </ul>
                </li>
                <li><a class="dropdown-item" href="events.html">Events</a></li>
                <li><a class="dropdown-item" href="events-2.html">Events 2</a></li>
                <li><a class="dropdown-item" href="event-details.html">Event details</a></li>
                <li><a class="dropdown-item" href="event-details-2.html">Event details 2</a></li>
                <li><a class="dropdown-item" href="groups.html">群组</a></li>
                <li><a class="dropdown-item" href="group-details.html">Group details</a></li>
                <li><a class="dropdown-item" href="post-videos.html">Post videos</a></li>
                <li><a class="dropdown-item" href="post-video-details.html">Post video details</a></li>
                <li><a class="dropdown-item" href="post-details.html">Post details</a></li>
                <li><a class="dropdown-item" href="video-details.html">Video details</a></li>
                <li><a class="dropdown-item" href="blog.html">Blog</a></li>
                <li><a class="dropdown-item" href="blog-details.html">Blog details</a></li>
                <!-- Dropdown submenu levels -->
                <li class="dropdown-divider"></li>
                <li class="dropdown-submenu dropend"><a class="dropdown-item dropdown-toggle" href="#">Dropdown
                  levels</a>
                  <ul class="dropdown-menu dropdown-menu-end" data-bs-popper="none">
                    <li><a class="dropdown-item" href="#">Dropdown item</a></li>
                    <li><a class="dropdown-item" href="#">Dropdown item</a></li><!-- dropdown submenu open left -->
                    <li class="dropdown-submenu dropstart"><a class="dropdown-item dropdown-toggle"
                                                              href="#">Dropdown(start)</a>
                      <ul class="dropdown-menu dropdown-menu-end" data-bs-popper="none">
                        <li><a class="dropdown-item" href="#">Dropdown item</a></li>
                        <li><a class="dropdown-item" href="#">Dropdown item</a></li>
                      </ul>
                    </li>
                    <li><a class="dropdown-item" href="#">Dropdown item</a></li>
                  </ul>
                </li>
              </ul>
            </li><!-- Nav item 3 Post -->
            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="postMenu"
                                             data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Account </a>
              <ul class="dropdown-menu" aria-labelledby="postMenu">
                <!--                <li><a class="dropdown-item" href="create-page.html">Create a page</a></li>-->
                <!--                <li><a class="dropdown-item" href="settings.html">Settings</a></li>-->
                <!--                <li><a class="dropdown-item" href="notifications.html">Notifications</a></li>-->
                <!--                <li><a class="dropdown-item" href="help.html">Help center</a></li>-->
                <!--                <li><a class="dropdown-item" href="help-details.html">Help details</a></li>-->
                <!-- dropdown submenu open left -->
                <li class="dropdown-submenu dropstart"><a class="dropdown-item dropdown-toggle"
                                                          href="#">Authentication</a>
                  <ul class="dropdown-menu dropdown-menu-end" data-bs-popper="none">
                    <li><a class="dropdown-item" href="sign-in.html">Sign in</a></li>
                    <li><a class="dropdown-item" href="sign-up.html">Sing up</a></li>
                    <li><a class="dropdown-item" href="forgot-password.html">Forgot password</a></li>
                    <li class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="sign-in-advance.html">Sign in advance</a></li>
                    <li><a class="dropdown-item" href="sign-up-advance.html">Sing up advance</a></li>
                    <li><a class="dropdown-item" href="forgot-password-advance.html">Forgot password advance</a></li>
                  </ul>
                </li>
                <!--                <li><a class="dropdown-item" href="error-404.html">Error 404</a></li>-->
                <!--                <li><a class="dropdown-item" href="offline.html">Offline</a></li>-->
                <!--                <li><a class="dropdown-item" href="privacy-and-terms.html">Privacy & terms</a></li>-->
              </ul>
            </li><!-- Nav item 4 Mega menu -->
            <!--            <li class="nav-item"><a class="nav-link" href="my-profile-connections.html">My network</a></li>-->
          </ul>
        </div><!-- Main navbar END -->
        <!-- Nav right START -->


        <ul class="nav flex-nowrap align-items-center ms-sm-3 list-unstyled">
          <li class="nav-item ms-2"><a class="nav-link icon-md btn btn-light p-0" href="messaging.html">
            <ChatLineRound style="width: 25px; height: 25px; margin-right: 0px"/>
          </a></li>

          <li class="nav-item ms-2"><a class="nav-link icon-md btn btn-light p-0" href="settings.html">
            <Setting style="width: 25px; height: 25px; margin-right: 0px"/>
          </a></li>


          <li class="nav-item dropdown ms-2">
            <a class="nav-link icon-md btn btn-light p-0" href="#" id="notifDropdown"
               role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
              <span class="badge-notif animation-blink"></span>
              <Bell style="width: 25px; height: 25px; margin-right: 0px"/>
            </a>
            <div class="dropdown-menu dropdown-animation dropdown-menu-end dropdown-menu-size-md p-0 shadow-lg border-0"
                 aria-labelledby="notifDropdown">
              <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                  <h6 class="m-0">通知<span class="badge bg-danger bg-opacity-10 text-danger ms-2">4
                      new</span></h6><a class="small" href="#">清除全部</a>
                </div>
                <div class="card-body p-0">
                  <ul class="list-group list-group-flush list-unstyled p-2">
                    <!-- Notif item -->
                    <li>
                      <div class="list-group-item list-group-item-action rounded badge-unread d-flex border-0 mb-1 p-3">
                        <div class="avatar text-center d-none d-sm-inline-block"><img class="avatar-img rounded-circle"
                                                                                      src="../../img/01.jpg" alt="">
                        </div>
                        <div class="ms-sm-3">
                          <div class=" d-flex">
                            <p class="small mb-2"><b>Judy Nguyen</b>sent you a friend request.</p>
                            <p class="small ms-3 text-nowrap">Just now</p>
                          </div>
                          <div class="d-flex">
                            <button class="btn btn-sm py-1 btn-primary me-2">Accept</button>
                            <button
                                class="btn btn-sm py-1 btn-danger-soft">Delete
                            </button>
                          </div>
                        </div>
                      </div>
                    </li><!-- Notif item -->
                    <li>
                      <div
                          class="list-group-item list-group-item-action rounded badge-unread d-flex border-0 mb-1 p-3 position-relative">
                        <div class="avatar text-center d-none d-sm-inline-block"><img class="avatar-img rounded-circle"
                                                                                      src="../../img/02.jpg" alt="">
                        </div>
                        <div class="ms-sm-3 d-flex">
                          <div>
                            <p class="small mb-2">Wish <b>Amanda Reed</b>a happy birthday(Nov 12)</p>
                            <button
                                class="btn btn-sm btn-outline-light py-1 me-2">Say happy birthday 🎂
                            </button>
                          </div>
                          <p class="small ms-3">2min</p>
                        </div>
                      </div>
                    </li><!-- Notif item -->
                    <li><a href="#" class="list-group-item list-group-item-action rounded d-flex border-0 mb-1 p-3">
                      <div class="avatar text-center d-none d-sm-inline-block">
                        <div class="avatar-img rounded-circle bg-success"><span
                            class="text-white position-absolute top-50 start-50 translate-middle fw-bold">WB</span>
                        </div>
                      </div>
                      <div class="ms-sm-3">
                        <div class="d-flex">
                          <p class="small mb-2">Webestica has 15 like and 1 new activity</p>
                          <p class="small ms-3">1hr</p>
                        </div>
                      </div>
                    </a></li><!-- Notif item -->
                    <li><a href="#" class="list-group-item list-group-item-action rounded d-flex border-0 p-3 mb-1">
                      <div class="avatar text-center d-none d-sm-inline-block"><img class="avatar-img rounded-circle"
                                                                                    src="../../img/12.svg" alt=""></div>
                      <div class="ms-sm-3 d-flex">
                        <p class="small mb-2"><b>Bootstrap in the news:</b>The search giant’s parent
                          company,Alphabet,just joined an exclusive club of tech stocks.</p>
                        <p class="small ms-3">4hr</p>
                      </div>
                    </a></li>
                  </ul>
                </div>
                <div class="card-footer text-center"><a href="#" class="btn btn-sm btn-primary-soft">See all incoming
                  activity</a></div>
              </div>
            </div>
          </li><!-- Notification dropdown END -->


          <li class="nav-item ms-2 dropdown"><a class="nav-link btn icon-md p-0" href="#" id="profileDropdown"
                                                role="button" data-bs-auto-close="outside" data-bs-display="static"
                                                data-bs-toggle="dropdown"
                                                aria-expanded="false"><img class="avatar-img rounded-2"
                                                                           src="../../img/07.jpg" alt=""></a>
            <ul class="dropdown-menu dropdown-animation dropdown-menu-end pt-3 small me-md-n3"
                aria-labelledby="profileDropdown">
              <!-- Profile info -->
              <li class="px-3">
                <div class="d-flex align-items-center position-relative">
                  <!-- Avatar -->
                  <div class="avatar me-3"><img class="avatar-img rounded-circle" src="../../img/07.jpg" alt="avatar">
                  </div>
                  <div><a class="h6 stretched-link" href="#">Lori Ferguson</a>
                    <p class="small m-0">Web Developer</p>
                  </div>
                </div>
                <a class="dropdown-item btn btn-primary-soft btn-sm my-2 text-center" href="my-profile.html">View
                  profile</a>
              </li><!-- Links -->
              <li><a class="dropdown-item" href="settings.html"><i class="bi bi-gear fa-fw me-2"></i>Settings &
                Privacy</a></li>
              <li><a class="dropdown-item" href=""><i class="fa-fw bi bi-life-preserver me-2"></i>Support </a></li>
              <li><a class="dropdown-item" href=""><i class="fa-fw bi bi-card-text me-2"></i>Documentation </a></li>
              <li class="dropdown-divider"></li>
              <li><a class="dropdown-item bg-danger-soft-hover" href="sign-in-advance.html"><i
                  class="bi bi-power fa-fw me-2"></i>Sign Out</a></li>
              <li>
                <hr class="dropdown-divider">
              </li><!-- Dark mode switch START -->
              <li>
                <div class="modeswitch-wrap" id="darkModeSwitch">
                  <div class="modeswitch-item">
                    <div class="modeswitch-icon"></div>
                  </div>
                  <span>Dark mode</span>
                </div>
              </li><!-- Dark mode switch END -->
            </ul>
          </li><!-- Profile START -->
        </ul><!-- Nav right END -->


      </div>
    </nav><!-- Logo Nav END -->
  </header><!-- ======================= Header END -->
  <!-- **************** MAIN CONTENT START **************** -->
  <main>
    <!-- Container START -->
    <div class="container">
      <div class="row g-4">

        <div class="col-lg-3">
          <!-- Advanced filter responsive toggler START -->
          <div class="d-flex align-items-center d-lg-none">
            <button class="border-0 bg-transparent" type="button"
                    data-bs-toggle="offcanvas" data-bs-target="#offcanvasSideNavbar"
                    aria-controls="offcanvasSideNavbar"><i
                class="btn btn-primary fw-bold fa-solid fa-sliders-h"></i><span
                class="h6 mb-0 fw-bold d-lg-none ms-2">My profile</span></button>
          </div>
          <!-- Advanced filter responsive toggler END -->
          <!-- Navbar START-->
          <nav class="navbar navbar-expand-lg mx-0">
            <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasSideNavbar">
              <!-- Offcanvas header -->
              <div class="offcanvas-header">
                <button type="button" class="btn-close text-reset ms-auto"
                        data-bs-dismiss="offcanvas" aria-label="Close"></button>
              </div><!-- Offcanvas body -->
              <div class="offcanvas-body d-block px-2 px-lg-0">
                <!-- Card START -->
                <div class="card overflow-hidden">
                  <!-- Cover image -->
                  <div class="h-50px"
                       :style="{ 'backgroundImage': 'url('+img_047+')'} "
                       style=" background-position: center left; background-size: cover; background-repeat: no-repeat;">
                  </div><!-- Card body START -->
                  <div class="card-body pt-0">
                    <div class="text-center">
                      <!-- Avatar -->
                      <div class="avatar avatar-lg mt-n5 mb-3"><a href="#!"><img
                          class="avatar-img rounded border border-white border-3" src="../../img/07.jpg" alt=""></a>
                      </div><!-- Info -->
                      <h5 class="mb-0"><a href="#!">阿庆 </a></h5>
                      <small>职业</small>
                      <p class="mt-3">路尽隐香处 ，翩然雪海间。</p>

                      <!-- User stat START -->
                      <div class="hstack gap-2 gap-xl-3 justify-content-center">
                        <!-- User stat item -->
                        <!--                        <div>-->
                        <!--                          <h6 class="mb-0">256</h6>-->
                        <!--                          <small>我的话题</small>-->
                        <!--                        </div>-->
                        <!--                        &lt;!&ndash; Divider &ndash;&gt;-->
                        <!--                        <div class="vr"></div>-->
                        <!--                        &lt;!&ndash; User stat item &ndash;&gt;-->
                        <!--                        <div>-->
                        <!--                          <h6 class="mb-0">2.5K</h6>-->
                        <!--                          <small>粉丝</small>-->
                        <!--                        </div>-->
                        <!--                        &lt;!&ndash; Divider &ndash;&gt;-->
                        <!--                        <div class="vr"></div>-->
                        <!--                        &lt;!&ndash; User stat item &ndash;&gt;-->
                        <!--                        <div>-->
                        <!--                          <h6 class="mb-0">365</h6>-->
                        <!--                          <small>关注</small>-->
                        <!--                        </div>-->
                      </div>
                      <!-- User stat END -->
                    </div>

                    <!-- Divider -->
                    <hr>

                    <!-- Side Nav START -->
                    <ul class="nav nav-link-secondary flex-column fw-bold gap-2">
                      <li class="nav-item">
                        <a class="nav-link" href="my-profile.html"> <img class="me-2 h-20px fa-fw"
                                                                         src="../../img/home-outline-filled.svg" alt=""><span>收藏话题 </span></a>
                      </li>
                      <!--                      <li class="nav-item">-->
                      <!--                        <a class="nav-link" href="my-profile-connections.html"> <img class="me-2 h-20px fa-fw"-->
                      <!--                                                                                     src="../../img/person-outline-filled.svg" alt=""><span>Connections </span></a>-->
                      <!--                      </li>-->
                      <li class="nav-item">
                        <a class="nav-link" href="blog.html"> <img class="me-2 h-20px fa-fw"
                                                                   src="../../img/earth-outline-filled.svg"
                                                                   alt=""><span>最新新闻</span></a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="events.html"> <img class="me-2 h-20px fa-fw"
                                                                     src="../../img/calendar-outline-filled.svg" alt=""><span>事件</span></a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="groups.html"> <img class="me-2 h-20px fa-fw"
                                                                     src="../../img/chat-outline-filled.svg"
                                                                     alt=""><span>群组 </span></a>
                      </li>
                      <li class="nav-item">
                        <router-link to="/notification" class="nav-link">
                          <img class="me-2 h-20px fa-fw"
                               src="../../img/notification-outlined-filled.svg"
                               alt=""><span>通知 </span>
                        </router-link>

                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="settings.html"> <img class="me-2 h-20px fa-fw"
                                                                       src="../../img/cog-outline-filled.svg"
                                                                       alt=""><span>设置 </span></a>
                      </li>
                    </ul>
                    <!-- Side Nav END -->
                  </div>
                  <!-- Card body END -->
                  <!-- Card footer -->
                  <!--                  <div class="card-footer text-center py-2">-->
                  <!--                    <a class="btn btn-link btn-sm" href="my-profile.html">View Profile </a>-->
                  <!--                  </div>-->
                </div>
                <!-- Card END -->

                <!-- Helper link START -->
                <ul class="nav small mt-4 justify-content-center lh-1">
                  <li class="nav-item">
                    <a class="nav-link" href="my-profile-about.html">About</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="settings.html">Settings</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="login">Support </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="">Docs </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="help.html">Help</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="privacy-and-terms.html">Privacy & terms</a>
                  </li>
                </ul>
                <!-- Helper link END -->
                <!-- Copyright -->
                <p class="small text-center mt-1">©2022 <a class="text-body" href="/"> Webestica </a></p>
              </div>
            </div>
          </nav>
          <!-- Navbar END-->
        </div>

        <div class="col-md-8 col-lg-6 vstack gap-4">
          <!-- Story START -->


          <!-- Share feed START -->
          <div class="card card-body">
            <div class="d-flex mb-3">
              <!-- Avatar -->
              <div class="avatar avatar-xs me-2">
                <a href="#"> <img class="avatar-img rounded-circle" src="../../img/03.jpg" alt=""> </a>
              </div>
              <!-- Post input -->
              <form class="w-100">
                <textarea class="form-control pe-4 border-0" rows="2" data-autoresize=""
                          placeholder="分享你的想法"></textarea>
              </form>
            </div>
            <!-- Share feed toolbar START -->
            <ul class="nav nav-pills nav-stack small fw-normal">
              <li class="nav-item">
                <a class="nav-link bg-light py-1 px-2 mb-0" href="#!" data-bs-toggle="modal"
                   data-bs-target="#feedActionPhoto">
                  <i class="bi bi-image-fill text-success pe-2"></i>照片
                </a>
              </li>

              <li class="nav-item">
                <a class="nav-link bg-light py-1 px-2 mb-0" href="#!" data-bs-toggle="modal"
                   data-bs-target="#modalCreateFeed"> <i class="bi bi-emoji-smile-fill text-warning pe-2"></i>文字</a>
              </li>
              <li class="nav-item dropdown ms-lg-auto">
                <a class="nav-link bg-light py-1 px-2 mb-0" href="#" id="feedActionShare" data-bs-toggle="dropdown"
                   aria-expanded="false">
                  <i class="bi bi-three-dots"></i>
                </a>
                <!-- Dropdown menu -->
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="feedActionShare">
                  <!--                    创建一个投票-->
                  <li><a class="dropdown-item" href="#"> <i class="bi bi-envelope fa-fw pe-2"></i>创建一个投票</a></li>

                  <li>
                    <a class="dropdown-item" href="#"> <i class="bi bi-bookmark-check fa-fw pe-2"></i>发布一个话题</a>
                  </li>

                  <li>
                    <hr class="dropdown-divider">
                  </li>

                  <li><a class="dropdown-item" href="#"> <i class="bi bi-pencil-square fa-fw pe-2"></i>帮助</a></li>
                </ul>

              </li>

            </ul>
            <!-- Share feed toolbar END -->
          </div>
          <!-- Share feed END -->

          <!-- Card feed item START -->
          <div v-for="(item,index) in postList" :key="index">
            <div class="card">
              <!-- Card header START -->
              <div class="card-header border-0 pb-0">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="d-flex align-items-center">
                    <!-- Avatar -->
                    <div class="avatar avatar-story me-2">
                      <a href="#!"> <img class="avatar-img rounded-circle" :src="item.avatarImg" alt=""> </a>
                    </div>
                    <!-- Info -->
                    <div>
                      <div class="nav nav-divider">
                        <h6 class="nav-item card-title mb-0"><a href="#!"> {{ item.name}} </a></h6>
                        <span class="nav-item small">{{item.time}}</span>
                      </div>
<!--                      {{item.job}}-->
                      <p class="mb-0 small">{{item.job}}</p>
                    </div>
                  </div>
                  <!-- Card feed action dropdown START -->
                  <div class="dropdown">
                    <a href="#" class="text-secondary btn btn-secondary-soft-hover py-1 px-2" id="cardFeedAction"
                       data-bs-toggle="dropdown" aria-expanded="false">
                      <i class="bi bi-three-dots"></i>
                    </a>
                    <!-- Card feed action dropdown menu -->
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cardFeedAction">
                      <li><a class="dropdown-item" href="#"> <i class="bi bi-bookmark fa-fw pe-2"></i>Save post</a></li>
                      <li><a class="dropdown-item" href="#"> <i class="bi bi-person-x fa-fw pe-2"></i>Unfollow lori
                        ferguson </a></li>
                      <li><a class="dropdown-item" href="#"> <i class="bi bi-x-circle fa-fw pe-2"></i>Hide post</a></li>
                      <li><a class="dropdown-item" href="#"> <i class="bi bi-slash-circle fa-fw pe-2"></i>Block</a></li>
                      <li>
                        <hr class="dropdown-divider">
                      </li>
                      <li><a class="dropdown-item" href="#"> <i class="bi bi-flag fa-fw pe-2"></i>Report post</a></li>
                    </ul>
                  </div>
                  <!-- Card feed action dropdown END -->
                </div>
              </div>
              <!-- Card header END -->
              <!-- Card body START -->
              <div class="card-body" style="padding: 5px">
                <md-editor-v3   previewOnly="true" v-model="item.text"/>
<!--                <p>{{ item.text }}</p>&lt;!&ndash; Card img &ndash;&gt;-->


<!--                图片列表-->
                <div v-for="(imgSrc,imgIndex) in item.imgList" :key="imgIndex" >
                  <img style="height: 200px" class="card-img" :src="imgSrc" alt="Post">
                </div>
                <!-- Feed react START -->
                <ul class="nav nav-stack py-3 small">
                  <li class="nav-item">
                    <a class="nav-link active" href="#!">
                      <i class="bi bi-hand-thumbs-up-fill pe-1">
                      </i>

                      点赞({{item.liked}})
                    </a>
                  </li>
                  <li class="nav-item" @click="transCommentFold">
                    <a class="" href="#!">
                      <i class="bi bi-chat-fill pe-1"></i>{{commentIsFold ? `${item.comments}条评论`:'收起评论'}}</a>
                  </li>
                  <li class="nav-item dropdown ms-sm-auto">

                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cardShareAction">
                      <li><a class="dropdown-item" href="#"><i class="bi bi-envelope fa-fw pe-2"></i>Send via Direct
                        Message</a></li>
                      <li><a class="dropdown-item" href="#"><i class="bi bi-bookmark-check fa-fw pe-2"></i>Bookmark </a>
                      </li>
                      <li><a class="dropdown-item" href="#"><i class="bi bi-link fa-fw pe-2"></i>Copy link to post</a>
                      </li>
                      <li><a class="dropdown-item" href="#"><i class="bi bi-share fa-fw pe-2"></i>Share post via …</a>
                      </li>
                      <li>
                        <hr class="dropdown-divider">
                      </li>
                      <li><a class="dropdown-item" href="#"><i class="bi bi-pencil-square fa-fw pe-2"></i>Share to News
                        Feed</a></li>
                    </ul>
                  </li><!-- Card share action END -->
                </ul><!-- Feed react END -->
                <!-- Add comment -->

                <ul v-show="!commentIsFold" class="comment-wrap list-unstyled" >
                  <!-- Comment item START -->
                  <div class="d-flex mb-3">
                    <!-- Avatar -->
                    <div class="avatar avatar-xs me-2" >
                      <a href="###">
                        <img class="avatar-img rounded-circle" :src="PersonalInformation.avatarImg" alt="">
                      </a>
                    </div>
                    <!-- Comment box -->
                    <form class="w-100">
                      <el-input
                          v-model="addComment.commentText"
                          autosize
                          type="textarea"
                          placeholder="Please input"
                      />
                      <div style="text-align: right">
                        <el-button @click="commentCommit(index)" style="margin-top: 5px;" type="primary" size="small" round>提交</el-button>
                      </div>

                    </form>
                  </div><!-- Comment wrap START -->
                  <li class="comment-item" v-for="(com,comIndex) in item.commentsList" :key="comIndex">

                    <div class="d-flex position-relative">
                      <!-- Avatar -->
                      <div class="avatar avatar-xs"><a href="#!"><img class="avatar-img rounded-circle"
                                                                      :src="com.avatarImg" alt=""></a></div>
                      <div class="ms-2">
                        <!-- Comment by -->
                        <div class="bg-light rounded-start-top-0 p-3 rounded" style="padding: 8px">

                          <div class="d-flex justify-content-between" style="display: flex;
                              flex-direction: row;
                              align-items: center;">
                            <h6  style="margin: 0px;display: inline-block; ">
                              <a  href="#!">{{com.name}}
                              </a>
                            </h6>

                            <small style="float: right" class="ms-2">{{com.time}}
                              <span>
                                <a href="#" class="text-secondary btn btn-secondary-soft-hover py-1 px-2" id="cardNotiAction"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                   <i class="bi bi-three-dots"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cardNotiAction">
                                  <li @click="commentDelete(index,comIndex)">
                                    <a class="dropdown-item" href="###"><i class="bi bi-check-lg fa-fw pe-2">
                                    </i>刪除评论</a>
                                  </li>
                                </ul>
                              </span>
                            </small>
                          </div>

                          <p class="small mb-0">{{com.text}}
                          </p>

                        </div><!-- Comment react -->
                        <ul class="nav nav-divider py-2 small">
<!--                          <li class="nav-item"><a class="nav-link" href="#!">点赞(3)</a></li>-->
<!--                          <li class="nav-item"><a class="nav-link" href="#!">回复</a></li>-->
<!--                          <li class="nav-item"><a class="nav-link" href="#!">查看更多回复</a></li>-->
                        </ul>
                      </div>
                    </div>
                  </li>
                  <div  style="padding: 5px; display: flex;
                              flex-direction: row;
                              align-items: center; ">
                    <el-button style="display: inline-block" @click="loadMoreComment(index)" size="small" type="primary" round>点击加载更多评论</el-button>
                  </div>
                </ul>

              </div><!-- Card body END -->
              <!-- Card footer START -->

            </div>
          </div>



          <!-- Load more button START -->
          <a href="#!" role="button" class="btn btn-loader btn-primary-soft" data-bs-toggle="button"
             aria-pressed="true">
            <span class="load-text"> 查看更多话题 </span>
            <div class="load-icon">
              <div class="spinner-grow spinner-grow-sm" role="status">
                <span class="visually-hidden">Loading...</span>
              </div>
            </div>
          </a>
          <!-- Load more button END -->

        </div>

        <div class="col-lg-3">
          <div class="row g-4">
            <!-- Card follow START -->
            <div class="col-sm-6 col-lg-12">
              <div class="card">
                <!-- Card header START -->
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
                      <a href="#"> <img class="avatar-img rounded-circle" src="../../img/11.jpg" alt=""> </a>
                    </div>
                    <!-- Title -->
                    <div class="overflow-hidden">
                      <a class="h6 mb-0" href="#!">冀欢 </a>
                      <p class="mb-0 small text-truncate">职业</p>
                    </div>
                    <!-- Button -->
                    <a class="btn btn-primary rounded-circle icon-md ms-auto" href="#"><i
                        class="bi bi-person-check-fill"> </i></a>
                  </div>


                  <div class="hstack gap-2 mb-3">
                    <!-- Avatar -->
                    <div class="avatar">
                      <a href="#"> <img class="avatar-img rounded-circle" src="../../img/placeholder.jpg" alt=""> </a>
                    </div>
                    <!-- Title -->
                    <div class="overflow-hidden">
                      <a class="h6 mb-0" href="#!">阿庆 </a>
                      <p class="mb-0 small text-truncate">职业</p>
                    </div>
                    <!-- Button -->
                    <a class="btn btn-primary-soft rounded-circle icon-md ms-auto" href="#">
                      <i class="fa-solid fa-plus"></i>
                    </a>
                  </div>
                  <!-- Connection item END -->


                  <!-- View more button -->
                  <div class="d-grid mt-3">
                    <a class="btn btn-sm btn-primary-soft" href="#!">查看更多</a>
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
                    <h6 class="mb-0"><a href="blog-details.html">Vue 零基础入门</a></h6>
                    <small>2小时</small>
                  </div>
                  <!-- News item -->

                  <!-- Load more comments -->
                  <a href="#!" role="button"
                     class="btn btn-link btn-link-loader btn-sm text-secondary d-flex align-items-center"
                     data-bs-toggle="button" aria-pressed="true">
                    <div class="spinner-dots me-2">
                      <span class="spinner-dot"></span>
                      <span class="spinner-dot"></span>
                      <span class="spinner-dot"></span>
                    </div>
                    查看更多
                  </a>
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
      <i class="bi bi-chat-left-text-fill"></i>
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
            <i class="bi bi-pencil-square"></i>
          </a>
          <!-- Chat action START -->
          <div class="dropdown">
            <a href="#" class="btn btn-secondary-soft-hover py-1 px-2" id="chatAction" data-bs-toggle="dropdown"
               aria-expanded="false">
              <i class="bi bi-three-dots"></i>
            </a>
            <!-- Chat action menu -->
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="chatAction">
              <li><a class="dropdown-item" href="#"> <i class="bi bi-check-square fa-fw pe-2"></i>Mark all as read</a>
              </li>
              <li><a class="dropdown-item" href="#"> <i class="bi bi-gear fa-fw pe-2"></i>Chat setting </a></li>
              <li><a class="dropdown-item" href="#"> <i class="bi bi-bell fa-fw pe-2"></i>Disable notifications</a></li>
              <li><a class="dropdown-item" href="#"> <i class="bi bi-volume-up-fill fa-fw pe-2"></i>Message sounds</a>
              </li>
              <li><a class="dropdown-item" href="#"> <i class="bi bi-slash-circle fa-fw pe-2"></i>Block setting</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="#"> <i class="bi bi-people fa-fw pe-2"></i>Create a group chat</a></li>
            </ul>
          </div>
          <!-- Chat action END -->

          <!-- Close  -->
          <a href="#" class="btn btn-secondary-soft-hover py-1 px-2" data-bs-dismiss="offcanvas" aria-label="Close">
            <i class="fa-solid fa-xmark"></i>
          </a>

        </div>
      </div>
      <!-- Offcanvas body START -->
      <div class="offcanvas-body pt-0 custom-scrollbar">
        <!-- Search contact START -->
        <form class="rounded position-relative">
          <input class="form-control ps-5 bg-light" type="search" placeholder="Search..." aria-label="Search">
          <button class="btn bg-transparent px-3 py-0 position-absolute top-50 start-0 translate-middle-y"
                  type="submit"><i class="bi bi-search fs-5"> </i></button>
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
              <a class="h6 mb-0 stretched-link" href="#!">Frances Guerrero </a>
              <div class="small text-secondary text-truncate">Frances sent a photo.</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> Just now</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative toast-btn" data-target="chatToast2">
            <!-- Avatar -->
            <div class="avatar status-online">
              <img class="avatar-img rounded-circle" src="../../img/02.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Lori Ferguson </a>
              <div class="small text-secondary text-truncate">You missed a call form Carolyn🤙</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 1min</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-offline">
              <img class="avatar-img rounded-circle" src="../../img/placeholder.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Samuel Bishop </a>
              <div class="small text-secondary text-truncate">Day sweetness why cordially 😊</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 2min</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar">
              <img class="avatar-img rounded-circle" src="../../img/04.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Dennis Barrett </a>
              <div class="small text-secondary text-truncate">Happy birthday🎂</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 10min</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar avatar-story status-online">
              <img class="avatar-img rounded-circle" src="../../img/05.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Judy Nguyen </a>
              <div class="small text-secondary text-truncate">Thank you!</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 2hrs</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-online">
              <img class="avatar-img rounded-circle" src="../../img/06.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Carolyn Ortiz </a>
              <div class="small text-secondary text-truncate">Greetings from Webestica.</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 1 day</div>
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
              <a class="h6 mb-0 stretched-link text-truncate d-inline-block" href="#!">Frances, Lori, Amanda, Lawson
              </a>
              <div class="small text-secondary text-truncate">Btw are you looking for job change?</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 4 day</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-offline">
              <img class="avatar-img rounded-circle" src="../../img/082.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Bryan Knight </a>
              <div class="small text-secondary text-truncate">if you are available to discuss🙄</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 6 day</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar">
              <img class="avatar-img rounded-circle" src="../../img/09.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#">Louis Crawford </a>
              <div class="small text-secondary text-truncate">🙌Congrats on your work anniversary!</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 1 day</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-online">
              <img class="avatar-img rounded-circle" src="../../img/10.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#!">Jacqueline Miller </a>
              <div class="small text-secondary text-truncate">No sorry, Thanks.</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 15, dec</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar">
              <img class="avatar-img rounded-circle" src="../../img/11.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#">Amanda Reed </a>
              <div class="small text-secondary text-truncate">Interested can share CV at.</div>
            </div>
            <!-- Chat time -->
            <div class="small ms-auto text-nowrap"> 18, dec</div>
          </li>

          <!-- Contact item -->
          <li class="mt-3 hstack gap-3 align-items-center position-relative">
            <!-- Avatar -->
            <div class="avatar status-online">
              <img class="avatar-img rounded-circle" src="../../img/12.jpg" alt="">
            </div>
            <!-- Info -->
            <div class="overflow-hidden">
              <a class="h6 mb-0 stretched-link" href="#">Larry Lawson </a>
              <div class="small text-secondary text-truncate">Hope you're doing well and Safe.</div>
            </div><!-- Chat time -->
            <div class="small ms-auto text-nowrap">20,dec</div>
          </li><!-- Button -->
          <li class="mt-3 d-grid"><a class="btn btn-primary-soft" href="messaging.html">See all in messaging </a></li>
        </ul>
      </div><!-- Offcanvas body END -->
    </div><!-- Chat sidebar END -->
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
                <div class="flex-shrink-0 avatar me-2"><img class="avatar-img rounded-circle" src="../../img/01.jpg"
                                                            alt=""></div>
                <div class="flex-grow-1">
                  <h6 class="mb-0 mt-1">Frances Guerrero</h6>
                  <div class="small text-secondary"><i class="fa-solid fa-circle text-success me-1"></i>Online</div>
                </div>
              </div>
              <div class="d-flex">
                <!-- Call button -->
                <div class="dropdown"><a class="btn btn-secondary-soft-hover py-1 px-2" href="#"
                                         id="chatcoversationDropdown" data-bs-toggle="dropdown"
                                         data-bs-auto-close="outside"
                                         aria-expanded="false"><i class="bi bi-three-dots-vertical"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="chatcoversationDropdown">
                    <li><a class="dropdown-item" href="#"><i class="bi bi-camera-video me-2 fw-icon"></i>Video call</a>
                    </li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-telephone me-2 fw-icon"></i>Audio call</a>
                    </li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-trash me-2 fw-icon"></i>Delete </a></li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-chat-square-text me-2 fw-icon"></i>Mark as
                      unread</a></li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-volume-up me-2 fw-icon"></i>Muted</a></li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-archive me-2 fw-icon"></i>Archive</a></li>
                    <li class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-flag me-2 fw-icon"></i>Report</a></li>
                  </ul>
                </div><!-- Card action END --><a class="btn btn-secondary-soft-hover py-1 px-2"
                                                 data-bs-toggle="collapse" href="#collapseChat" aria-expanded="false"
                                                 aria-controls="collapseChat"><i
                  class="bi bi-dash-lg"></i></a>
                <button class="btn btn-secondary-soft-hover py-1 px-2"
                        data-bs-dismiss="toast" aria-label="Close"><i class="fa-solid fa-xmark"></i></button>
              </div>
            </div><!-- Top avatar and status END -->
          </div>
          <div class="toast-body collapse show" id="collapseChat">
            <!-- Chat conversation START -->
            <div class="chat-conversation-content custom-scrollbar h-200px">
              <!-- Chat time -->
              <div class="text-center small my-2">Jul 16,2022,06:15 am</div><!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2"><img class="avatar-img rounded-circle"
                                                                      src="../../img/01.jpg" alt=""></div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">Applauded no discovery😊</div>
                      <div class="small my-2">6:15 AM</div>
                    </div>
                  </div>
                </div>
              </div><!-- Chat message right -->
              <div class="d-flex justify-content-end text-end mb-1">
                <div class="w-100">
                  <div class="d-flex flex-column align-items-end">
                    <div class="bg-primary text-white p-2 px-3 rounded-2">With pleasure</div>
                  </div>
                </div>
              </div><!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2"><img class="avatar-img rounded-circle"
                                                                      src="../../img/01.jpg" alt=""></div>
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
              </div><!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2"><img class="avatar-img rounded-circle"
                                                                      src="../../img/01.jpg" alt=""></div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">How promotion excellent curiosity😮</div>
                      <div class="small my-2">3:22 PM</div>
                    </div>
                  </div>
                </div>
              </div><!-- Chat message right -->
              <div class="d-flex justify-content-end text-end mb-1">
                <div class="w-100">
                  <div class="d-flex flex-column align-items-end">
                    <div class="bg-primary text-white p-2 px-3 rounded-2">And sir dare view.</div><!-- Images -->
                    <div class="d-flex my-2">
                      <div class="small text-secondary">5:35 PM</div>
                      <div class="small ms-2"><i class="fa-solid fa-check"></i></div>
                    </div>
                  </div>
                </div>
              </div><!-- Chat time -->
              <div class="text-center small my-2">2 New Messages</div><!-- Chat Typing -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2"><img class="avatar-img rounded-circle"
                                                                      src="../../img/01.jpg" alt=""></div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-3 rounded-2">
                        <div class="typing d-flex align-items-center">
                          <div class="dot"></div>
                          <div class="dot"></div>
                          <div class="dot"></div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div><!-- Chat conversation END -->
            <!-- Chat bottom START -->
            <div class="mt-2">
              <!-- Chat textarea --><textarea class="form-control mb-sm-0 mb-3" placeholder="Type a message"
                                              rows="1"></textarea><!-- Button -->
              <div class="d-sm-flex align-items-end mt-2">
                <button class="btn btn-sm btn-danger-soft me-2"><i
                    class="fa-solid fa-face-smile fs-6"></i></button>
                <button
                    class="btn btn-sm btn-secondary-soft me-2"><i class="fa-solid fa-paperclip fs-6"></i></button>
                <button
                    class="btn btn-sm btn-success-soft me-2">Gif
                </button>
                <button
                    class="btn btn-sm btn-primary ms-auto">Send
                </button>
              </div>
            </div><!-- Chat bottom START -->
          </div>
        </div><!-- Chat toast END -->
        <!-- Chat toast 2 START -->
        <div id="chatToast2" class="toast mb-0 bg-mode" role="alert" aria-live="assertive" aria-atomic="true"
             data-bs-autohide="false">
          <div class="toast-header bg-mode">
            <!-- Top avatar and status START -->
            <div class="d-flex justify-content-between align-items-center w-100">
              <div class="d-flex">
                <div class="flex-shrink-0 avatar me-2"><img class="avatar-img rounded-circle" src="../../img/02.jpg"
                                                            alt=""></div>
                <div class="flex-grow-1">
                  <h6 class="mb-0 mt-1">Lori Ferguson</h6>
                  <div class="small text-secondary"><i class="fa-solid fa-circle text-success me-1"></i>Online</div>
                </div>
              </div>
              <div class="d-flex">
                <!-- Call button -->
                <div class="dropdown"><a class="btn btn-secondary-soft-hover py-1 px-2" href="#"
                                         id="chatcoversationDropdown2" role="button" data-bs-toggle="dropdown"
                                         data-bs-auto-close="outside"
                                         aria-expanded="false"><i class="bi bi-three-dots-vertical"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="chatcoversationDropdown2">
                    <li><a class="dropdown-item" href="#"><i class="bi bi-camera-video me-2 fw-icon"></i>Video call</a>
                    </li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-telephone me-2 fw-icon"></i>Audio call</a>
                    </li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-trash me-2 fw-icon"></i>Delete </a></li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-chat-square-text me-2 fw-icon"></i>Mark as
                      unread</a></li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-volume-up me-2 fw-icon"></i>Muted</a></li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-archive me-2 fw-icon"></i>Archive</a></li>
                    <li class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#"><i class="bi bi-flag me-2 fw-icon"></i>Report</a></li>
                  </ul>
                </div><!-- Card action END --><a class="btn btn-secondary-soft-hover py-1 px-2"
                                                 data-bs-toggle="collapse" href="#collapseChat2" role="button"
                                                 aria-expanded="false"
                                                 aria-controls="collapseChat2"><i class="bi bi-dash-lg"></i></a>
                <button
                    class="btn btn-secondary-soft-hover py-1 px-2" data-bs-dismiss="toast" aria-label="Close"><i
                    class="fa-solid fa-xmark"></i></button>
              </div>
            </div><!-- Top avatar and status END -->
          </div>
          <div class="toast-body collapse show" id="collapseChat2">
            <!-- Chat conversation START -->
            <div class="chat-conversation-content custom-scrollbar h-200px">
              <!-- Chat time -->
              <div class="text-center small my-2">Jul 16,2022,06:15 am</div><!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2"><img class="avatar-img rounded-circle"
                                                                      src="../../img/02.jpg" alt=""></div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">Applauded no discovery😊</div>
                      <div class="small my-2">6:15 AM</div>
                    </div>
                  </div>
                </div>
              </div><!-- Chat message right -->
              <div class="d-flex justify-content-end text-end mb-1">
                <div class="w-100">
                  <div class="d-flex flex-column align-items-end">
                    <div class="bg-primary text-white p-2 px-3 rounded-2">With pleasure</div>
                  </div>
                </div>
              </div><!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2"><img class="avatar-img rounded-circle"
                                                                      src="../../img/02.jpg" alt=""></div>
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
              </div><!-- Chat message left -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2"><img class="avatar-img rounded-circle"
                                                                      src="../../img/02.jpg" alt=""></div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-2 px-3 rounded-2">How promotion excellent curiosity😮</div>
                      <div class="small my-2">3:22 PM</div>
                    </div>
                  </div>
                </div>
              </div><!-- Chat message right -->
              <div class="d-flex justify-content-end text-end mb-1">
                <div class="w-100">
                  <div class="d-flex flex-column align-items-end">
                    <div class="bg-primary text-white p-2 px-3 rounded-2">And sir dare view.</div><!-- Images -->
                    <div class="d-flex my-2">
                      <div class="small text-secondary">5:35 PM</div>
                      <div class="small ms-2"><i class="fa-solid fa-check"></i></div>
                    </div>
                  </div>
                </div>
              </div><!-- Chat time -->
              <div class="text-center small my-2">2 New Messages</div><!-- Chat Typing -->
              <div class="d-flex mb-1">
                <div class="flex-shrink-0 avatar avatar-xs me-2"><img class="avatar-img rounded-circle"
                                                                      src="../../img/02.jpg" alt=""></div>
                <div class="flex-grow-1">
                  <div class="w-100">
                    <div class="d-flex flex-column align-items-start">
                      <div class="bg-light text-secondary p-3 rounded-2">
                        <div class="typing d-flex align-items-center">
                          <div class="dot"></div>
                          <div class="dot"></div>
                          <div class="dot"></div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div><!-- Chat conversation END -->
            <!-- Chat bottom START -->
            <div class="mt-2">
              <!-- Chat textarea --><textarea class="form-control mb-sm-0 mb-3" placeholder="Type a message"
                                              rows="1"></textarea><!-- Button -->
              <div class="d-sm-flex align-items-end mt-2">
                <button class="btn btn-sm btn-danger-soft me-2"><i
                    class="fa-solid fa-face-smile fs-6"></i></button>
                <button
                    class="btn btn-sm btn-secondary-soft me-2"><i class="fa-solid fa-paperclip fs-6"></i></button>
                <button
                    class="btn btn-sm btn-success-soft me-2">Gif
                </button>
                <button
                    class="btn btn-sm btn-primary ms-auto">Send
                </button>
              </div>
            </div><!-- Chat bottom START -->
          </div>
        </div><!-- Chat toast 2 END -->
      </div>
    </div><!-- Chat END -->
  </div><!-- Main Chat END -->
  <!-- Modal create Feed START -->
  Modal create Feed photo START

  </body>

  <div class="modal fade" id="feedActionPhoto" tabindex="-1" aria-labelledby="feedActionPhotoLabel" aria-hidden="ture">
    <div class="modal-dialog modal-dialog-centered">


      <div class="modal-content">
        <!-- Modal feed header START -->
        <div class="modal-header">
          <h5 class="modal-title" id="feedActionPhotoLabel">
            添加图片
          </h5>
          <button type="button" class="btn-close"
                  data-bs-dismiss="modal" aria-label="Close">

          </button>
        </div><!-- Modal feed header END -->


        <!-- Modal feed body START -->
        <div class="modal-body">
          <!-- Add Feed -->
          <div class="d-flex mb-3">
            <!-- Avatar -->
            <div class="avatar avatar-xs me-2"><img class="avatar-img rounded-circle" src="../../img/03.jpg" alt="">
            </div><!-- Feed box -->
            <form class="w-100">
              <textarea v-model="tempPost.text" class="form-control pe-4 fs-3 lh-1 border-0" rows="10"
                                          placeholder="分享你的想法">

              </textarea>
            </form>


          </div><!-- Dropzone photo START -->

          <el-upload
              multiple
              class="upload-demo"
              drag
              action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d51"
              :show-file-list="false"
              :on-success="(response, uploadFile, uploadFiles) => myUploadSuccess(response,uploadFile,uploadFiles)"
              :on-change="(uploadFile, uploadFiles) =>myUploadChange(uploadFile,uploadFiles)"
              :file-list="myUpFileList"
              :on-preview="(uploadFile) => myPreview(uploadFile)"
              :auto-upload="false"
          >
            <el-icon class="el-icon--upload">
              <upload-filled/>
            </el-icon>
            <div class="el-upload__text">
              Drop file here or <em>click to upload</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                jpg/png files with a size less than 500kb
              </div>
            </template>
          </el-upload>
          <ul id="images" class="el-upload-list el-upload-list--picture-card"  v-for="imgItem in myUpFileList" :key="imgItem.uid">

            <li class="el-upload-list__item is-success" tabindex="0" >

              <img  class="el-upload-list__item-thumbnail" :src="imgItem.imgSrc"
                    alt=""><!--v-if--><label
                class="el-upload-list__item-status-label"><i class="el-icon el-icon--upload-success el-icon--check">
              <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
                <path fill="currentColor"
                      d="M406.656 706.944 195.84 496.256a32 32 0 1 0-45.248 45.248l256 256 512-512a32 32 0 0 0-45.248-45.248L406.592 706.944z"></path>
              </svg>
            </i></label>
          <!-- TODO: Fix the incorrect navigation interaction -->
              <span class="el-upload-list__item-actions">
               <span
                   class="el-upload-list__item-delete" @click="imageRemove(imgItem.uid)">
                 <i class="el-icon el-icon--delete">
                   <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg"><path
                       fill="currentColor"
                       d="M160 256H96a32 32 0 0 1 0-64h256V95.936a32 32 0 0 1 32-32h256a32 32 0 0 1 32 32V192h256a32 32 0 1 1 0 64h-64v672a32 32 0 0 1-32 32H192a32 32 0 0 1-32-32V256zm448-64v-64H416v64h192zM224 896h576V256H224v640zm192-128a32 32 0 0 1-32-32V416a32 32 0 0 1 64 0v320a32 32 0 0 1-32 32zm192 0a32 32 0 0 1-32-32V416a32 32 0 0 1 64 0v320a32 32 0 0 1-32 32z"></path></svg></i>
               </span>
          </span>
            </li>

          </ul>


        </div><!-- Modal feed body END -->



        <!-- Modal feed footer -->
        <div class="modal-footer ">
          <!-- Button -->
          <button type="button" class="btn btn-danger-soft me-2"
                  data-bs-dismiss="modal" @click="modalCancel">关闭
          </button>
          <button type="button" class="btn btn-success-soft"  @click="upload">发送</button>
        </div><!-- Modal feed footer -->
      </div>


    </div>
  </div>



</template>
<script>

import {UploadFilled} from '@element-plus/icons-vue'
import {ElMessage} from 'element-plus'
import axios from "axios";
console.log(UploadFilled)
const ICON_047 = "047.jpg"
export default {
  name: "index.vue",
  components:
      {
      },
  maraInfo: {//页面设置
    title: "主页",
    meta: [
      {name: "viewport", content: "width=device-width, initial-scale=1, shrink-to-fit=no"},
      {name: "author", content: "Webestica.com"},
      {name: "author", content: "Webestica.com"},
      {name: "description", content: "Bootstrap 5 based Social Media Network and Community Theme"}
    ],
  },
  data() {
    return {
      PersonalInformation:{
        avatarImg: "/047.jpg"
      },
      img_047: require("../../img/" + ICON_047),
      commentIsFold:true,
      commentText:"",//评论内容双向绑定
      myUpFileList: [],
      myDeleteFileList:[],
      dialogImageUrl: "",
      dialogVisible: false,
      disabled: false,
      addComment:{
        commentText:"",
        avatarImg:"/047.jpg",
        time:"",
        name:"",
      },
      postList:[{
        id:"123456",
        avatarImg:"/047.jpg",
        name:"冀欢",
        time:"200/9/24",
        job:"学生",
        text:"话题内容",
        imgList:[
            "/img.png",
            "/031.jpg"
        ],
        liked:0,
        comments:0,
        commentsList:[

        ]

      }],
      tempPost:{
        id:"123",
        avatarImg:"/047.jpg",
        name:"冀欢",
        time:"200/9/24",
        job:"学生",
        text:"话题内容",
        imgList:[],
        liked:0,
        comments:1,
        commentsList:[
          {
            avatarImg:"/047.jpg",
            name:"阿庆",
            time:"2022/9/24",
            text:"Removed demands expense account in outward tedious do. Particular way thoroughly unaffected projection.",
          }
        ]

      },
    }
  },
  created()
  {

  },methods:
  {
    myUploadSuccess(response, uploadFile, uploadFiles) {
      console.log(response)
      console.log(uploadFile)
      console.log(uploadFiles)
      console.log("输出上传图片列表", this.myUpFileList)
    }
    ,
    myUploadChange(uploadFile, uploadFiles)
    {

      console.log("change",uploadFile,uploadFiles)
      this.dialogImageUrl = URL.createObjectURL(uploadFile.raw);
      if(this.myUpFileList.length===0)
      {
        let imgUrlSuccess = URL.createObjectURL(uploadFile.raw)
        this.myUpFileList.push({
          uid:uploadFile.uid,
          name:uploadFile.name,
          imgSrc:imgUrlSuccess,
          imageRaw:uploadFile.raw})
      }
      let judgePush = true;
      let judgeDelete=true;
      for(let i =0 ;i<this.myDeleteFileList.length;i++)
      {
        if(uploadFile.uid=== this.myDeleteFileList[i].uid)
        {
          judgeDelete = false
        }
      }
      for(let i =0 ;i<this.myUpFileList.length;i++)
      {
        if(uploadFile.uid=== this.myUpFileList[i].uid)
        {
          judgePush = false
        }
      }
      if(judgePush!==false&&judgeDelete===true)
      {
        console.log("push成功")
        let imgUrlSuccess = URL.createObjectURL(uploadFile.raw)
        this.myUpFileList.push({uid:uploadFile.uid,name:uploadFile.name,imgSrc:imgUrlSuccess,imageRaw:uploadFile.raw})
      }
      uploadFiles = this.myUpFileList

    },
    myPreview(uploadFile) {
      console.log(uploadFile)
    },
    upload() {
      console.log(this.myUpFileList)
      let _this = this
      let imageArrayList = []
      for(let i = 0;i<this.myUpFileList.length;i++)
      {
        let reader = new FileReader()
        reader.readAsDataURL(this.myUpFileList[i].imageRaw)
        reader.onload=function ()
        {
          imageArrayList.push({"image":reader.result})
          _this.tempPost.imgList.push(reader.result)
        }

      }
      axios.request({
        method: "post", // 请求方式
        url: 'https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15',
        dataType:'json', // 上传内容格式
        contentType: 'application/x-www-form-urlencoded', // 在发送前编码所有字符（默认）
        data: imageArrayList,  // 上传的json
        headers: { "Access-Control-Allow-Origin": "*" } // 跨域

      }).then(res=>{
        console.log("发送成功",res)
        this.postList.push(this.tempPost)
      }).catch(err=>{
        console.log("发送失败",err)
      });
      this.modalCancel()
    },
    imageRemove(imageUid)
    {
      console.log(imageUid)
      for (let i = 0;i<this.myUpFileList.length;i++)
      {
        if(this.myUpFileList[i].uid===imageUid)
        {
          this.myDeleteFileList.push(this.myUpFileList[i])
          console.log("查询到相同UID")
          this.myUpFileList.splice(i,1);

        }
      }

    },
    modalCancel()
    {
      this.myUpFileList.splice(0,this.myUpFileList.length)
    },
    commentCommit(index)
    {
      this.addComment.name="阿庆";
      this.addComment.time=this.getNowDate();
      this.addComment.avatarImg = '/031.jpg';
      //获取该帖子所对应的id,到数据库里面去查找id，然后将新增的评论插入进去
      //this.postList[index].id;
      //将评论插入到前端代码
      this.postList[index].commentsList.push(
          { name:this.addComment.name,
            avatarImg: this.addComment.avatarImg,
            time:this.addComment.time,
            text: this.addComment.commentText
          });
      //发出请求，修改数据库
      //请求完成之后，修改评论内容为空,双向绑定
      this.addComment.commentText="";
      this.postList[index].comments = this.postList[index].comments+1;

    },
    commentDelete(index,comIndex)
    {
        this.postList[index].commentsList.splice(comIndex,1);
      this.postList[index].comments = this.postList[index].comments-1;
    },
    transCommentFold()
    {
      this.commentIsFold = !this.commentIsFold;
    },
    loadMoreComment(index)
    {
      //发出请求，判断请求到的数量
      ElMessage({
        message: '没有更多评论啦~',
        type: 'success',
      })
      //将请求到的评论赋值
      let getCommentList = [{
        avatarImg:"/031.jpg",
        name:"阿庆",
        time:"2022/9/24",
        text:"Removed demands expense account in outward tedious do. Particular way thoroughly unaffected projection.",
      }]
      //将请求到的评论拼接
      this.postList[index].commentsList = this.postList[index].commentsList.concat(getCommentList);





    },
    getNowDate()
    {
      let date = new Date();
      let yy = date.getFullYear();//年
      let mm = date.getMonth() + 1;//月
      let dd = date.getDate();//日
      let hh = date.getHours();//时
      let mf = date.getMinutes()<10 ? '0'+date.getMinutes() : date.getMinutes();
      let ss = date.getSeconds()<10 ? '0'+date.getSeconds() : date.getSeconds();
      let str = yy+'-'+mm+'-'+dd+' '+hh+':'+mf+':'+ss;
      return str;
    }
  }



}

</script>

<style>
@import url(@/css/font.css);
@import url(@/css/all.min.css);
@import url(@/css/style.css);
</style>


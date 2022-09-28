<template>
  <header class="navbar-light fixed-top header-static bg-mode">
    <!-- Logo Nav START -->
    <nav class="navbar navbar-expand-lg">
      <div class="container">
        <a class="navbar-brand" href="">
          <img class="light-mode-item navbar-brand-item" src="@/static/imgs/notice_logo.svg" alt="logo">
          <img class="dark-mode-item navbar-brand-item" src="@/static/imgs/notice_logo.svg" alt="logo"></a><!-- Logo END -->
        <!-- Responsive navbar toggler -->
        <button class="navbar-toggler ms-auto icon-md btn btn-light p-0" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse"
                aria-expanded="false" aria-label="Toggle navigation"><span
            class="navbar-toggler-animation">
          <span></span><span></span><span></span></span></button>
        <!-- Main navbar START -->
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <!-- Nav Search START -->
          <div class="nav mt-3 mt-lg-0 flex-nowrap align-items-center px-4 px-lg-0">
            <div class="nav-item w-100">
              <form class="rounded position-relative">
                <input class="form-control ps-5 bg-light" type="search" placeholder="搜索" aria-label="Search">
                <button
                    class="btn bg-transparent px-2 py-0 position-absolute top-50 start-0 translate-middle-y"
                    type="submit"><i class="bi bi-search fs-5"></i></button>
              </form>
            </div>
          </div><!-- Nav Search END -->
          <ul class="navbar-nav navbar-nav-scroll ms-auto">
            <!-- Nav item 1 Demos -->
            <li class="nav-item dropdown">
              <a class="nav-link active dropdown-toggle" href="#" id="homeMenu" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Demo</a>
              <ul class="dropdown-menu" aria-labelledby="homeMenu">
                <li><a class="dropdown-item active" href="">主页</a></li>
              </ul>
            </li><!-- Nav item 2 Pages -->


            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="pagesMenu"
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
                <li class="dropdown-submenu dropend">
                  <a class="dropdown-item dropdown-toggle" href="#">Dropdown levels</a>
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
          <li class="nav-item ms-2">
            <a class="nav-link icon-md btn btn-light p-0" href="messaging.html">
            <ChatLineRound style="width: 25px; height: 25px; margin-right: 0px"/>
          </a></li>

          <li class="nav-item ms-2">
            <a class="nav-link icon-md btn btn-light p-0" href="settings.html">
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
                <div class="card-footer text-center"><a href="#" class="btn btn-sm btn-primary-soft">See all incoming
                  activity</a></div>
              </div>
            </div>
          </li><!-- Notification dropdown END -->

          <li class="nav-item ms-2 dropdown">
            <a class="nav-link btn icon-md p-0" href="#" id="profileDropdown"
                                                role="button" data-bs-auto-close="outside" data-bs-display="static"
                                                data-bs-toggle="dropdown"
                                                aria-expanded="false">
              <img class="avatar-img rounded-2" :src="userIco" alt=""></a>
          </li><!-- Profile START -->
        </ul><!-- Nav right END -->


      </div>
    </nav><!-- Logo Nav END -->
  </header>
</template>

<script>
export default {
  name: "headerNav",
  data(){
    return {
      userIco: require('@/static/imgs/default_ico.png'),
    }
  },
  beforeMount() {
    if(localStorage.getItem("userCommonInfo")!=null){
      this.userIco = JSON.parse(localStorage.getItem("userCommonInfo")).portrait
    }
  }
}

</script>

<style scoped>
@import url(@/Icon/threePoint01/iconfont.css);
@import url(@/css/font.css);
@import url(@/css/all.min.css);
@import url(@/css/style.css);
@import url(@/css/css2.css);
</style>
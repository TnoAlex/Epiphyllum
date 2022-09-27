<template>
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
                </div>
              </div>
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

                  <router-link class="nav-link" to="/group"> <img class="me-2 h-20px fa-fw"
                                                               src="../../img/chat-outline-filled.svg"
                                                               alt=""><span>群组 </span></router-link>
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
</template>

<script>
const ICON_047 = "047.jpg"
export default {
  name: "indexLeft",
  data(){
    return{
      img_047: require("../../img/" + ICON_047),
    }
  }
}
</script>

<style scoped>
@import url(@/css/all.min.css);
@import url(@/css/style.css);
@import url(@/css/css2.css);
</style>
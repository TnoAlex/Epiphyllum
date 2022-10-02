<template>
  <div class="row g-4"  v-for="(item,index) in groupInformation" :key="index">

    <div class="col-sm-6 col-lg-4" v-if="!item.isJoined">
      <div class="card">
        <div class="h-80px rounded-top"
             style="background-image:url(public/047.jpg); background-position: center; background-size: cover; background-repeat: no-repeat;">
        </div><!-- Card body START -->
        <div class="card-body text-center pt-0">
          <!-- Avatar -->
          <div class="avatar avatar-lg mt-n5 mb-3"><a href="group-details.html"><img
              class="avatar-img rounded-circle border border-white border-3 bg-white"
              src="/01.jpg" alt=""></a></div><!-- Info -->
          <h5 class="mb-0"><a href="group-details.html">{{item.groupName}}</a></h5>
          <div>
            <small>
              <span class="iconfont icon-yuanshuju-chuangjianren"></span> {{item.createPersonName}}
            </small>
          </div>
          <div>
            <small>
              <span class="iconfont icon-yuanshuju-chuangjianshijian"></span> {{ item.createTime }}
            </small>
          </div>
          <!-- Group stat START -->
          <!--                          <div class="hstack gap-2 gap-xl-3 justify-content-center mt-3">-->
          <!--                            &lt;!&ndash; Group stat item &ndash;&gt;-->
          <!--                            <div>-->
          <!--                              <h6 class="mb-0">32k</h6><small>Members</small>-->
          <!--                            </div>&lt;!&ndash; Divider &ndash;&gt;-->
          <!--                            <div class="vr"></div>&lt;!&ndash; Group stat item &ndash;&gt;-->
          <!--                            <div>-->
          <!--                              <h6 class="mb-0">20</h6><small>Post per day</small>-->
          <!--                            </div>-->
          <!--                          </div>-->
          <!-- Avatar group START -->
          <ul class="avatar-group list-unstyled align-items-center justify-content-center mb-0 mt-3">
            <li class="avatar avatar-xs"><img class="avatar-img rounded-circle" src="/01.jpg"
                                              alt="avatar"></li>
            <li class="avatar avatar-xs"><img class="avatar-img rounded-circle" src="/01.jpg"
                                              alt="avatar"></li>
            <li class="avatar avatar-xs"><img class="avatar-img rounded-circle" src="/01.jpg"
                                              alt="avatar"></li>
            <li class="avatar avatar-xs"><img class="avatar-img rounded-circle" src="/01.jpg"
                                              alt="avatar"></li>
            <li class="avatar avatar-xs">
              <div class="avatar-img rounded-circle bg-primary"><span
                  class="smaller text-white position-absolute top-50 start-50 translate-middle">+22</span>
              </div>
            </li>
          </ul><!-- Avatar group END -->
        </div><!-- Card body END -->
        <!-- Card Footer START -->
        <div class="card-footer text-center"><a class="btn btn-success-soft btn-sm" href="#!">加入群组
        </a></div><!-- Card Footer END -->
      </div>
    </div>
    <div class="col-sm-6 col-lg-4" v-if="item.isJoined">
      <div class="card">
        <div class="h-80px rounded-top"
             style="background-image:url(public/01.jpg); background-position: center; background-size: cover; background-repeat: no-repeat;">
        </div><!-- Card body START -->
        <div class="card-body text-center pt-0">
          <!-- Avatar -->
          <div class="avatar avatar-lg mt-n5 mb-3"><a href="group-details.html"><img
              class="avatar-img rounded-circle border border-white border-3 bg-white"
              src="/01.jpg" alt=""></a></div><!-- Info -->
          <h5 class="mb-0"><a href="group-details.html">{{item.groupName}}</a></h5>
          <div>
            <small>
              <span class="iconfont icon-yuanshuju-chuangjianren"></span> {{item.createPersonName}}
            </small>
          </div>
          <div>
            <small>
              <span class="iconfont icon-yuanshuju-chuangjianshijian"></span> {{ item.createTime }}
            </small>
          </div>

          <!-- Group stat START -->
          <!--                          <div class="hstack gap-2 gap-xl-3 justify-content-center mt-3">-->
          <!--                            &lt;!&ndash; Group stat item &ndash;&gt;-->
          <!--                            <div>-->
          <!--                              <h6 class="mb-0">32k</h6><small>Members</small>-->
          <!--                            </div>&lt;!&ndash; Divider &ndash;&gt;-->
          <!--                            <div class="vr"></div>&lt;!&ndash; Group stat item &ndash;&gt;-->
          <!--                            <div>-->
          <!--                              <h6 class="mb-0">05</h6><small>Post per day</small>-->
          <!--                            </div>-->
          <!--                          </div>&lt;!&ndash; Group stat END &ndash;&gt;-->
          <!-- Avatar group START -->
          <ul class="avatar-group list-unstyled align-items-center justify-content-center mb-0 mt-3">
            <li class="avatar avatar-xs"><img class="avatar-img rounded-circle" src="/01.jpg"
                                              alt="avatar"></li>
            <li class="avatar avatar-xs"><img class="avatar-img rounded-circle" src="/01.jpg"
                                              alt="avatar"></li>
            <li class="avatar avatar-xs"><img class="avatar-img rounded-circle" src="/01.jpg"
                                              alt="avatar"></li>
            <li class="avatar avatar-xs"><img class="avatar-img rounded-circle" src="/01.jpg"
                                              alt="avatar"></li>
            <li class="avatar avatar-xs"><img class="avatar-img rounded-circle" src="/01.jpg"
                                              alt="avatar"></li>
            <li class="avatar avatar-xs">
              <div class="avatar-img rounded-circle bg-primary"><span
                  class="smaller text-white position-absolute top-50 start-50 translate-middle">+08</span>
              </div>
            </li>
          </ul><!-- Avatar group END -->
        </div><!-- Card body END -->
        <!-- Card Footer START -->
        <div class="card-footer text-center"><a class="btn btn-danger-soft btn-sm" href="#!">离开群组
        </a></div><!-- Card Footer END -->
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "temp"
}
</script>

<style scoped>

</style>
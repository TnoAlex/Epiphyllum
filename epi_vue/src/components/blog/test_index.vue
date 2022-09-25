<template>
<!--  <div class="dropdown">-->
  <span>
    <a href="#"
       class="text-secondary btn btn-secondary-soft-hover py-1 px-2" id="cardNotiAction"
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
  </span>

  <input value="dddd">
<!--  </div>-->
</template>

<script>
export default {
  name: "test_index"
}
</script>

<style scoped>
  @import url(@/css/font.css);
  @import url(@/css/all.min.css);
  @import url(@/css/style.css);
</style>
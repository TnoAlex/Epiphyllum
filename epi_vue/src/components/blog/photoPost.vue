<template>
<!--  <div class="md-preview github-theme md-scrn" v-html="htmlText">-->
<!--  </div>-->
  <div class="modal-dialog modal-dialog-centered" style="width: 1500px">
  <div class="modal-content">
  <div class="modal-body">
  <div class="d-flex mb-3">
    <!-- Avatar -->
    <div class="avatar avatar-xs me-2"><img class="avatar-img rounded-circle" src="../../img/03.jpg" alt="">
    </div><!-- Feed box -->
    <form class="w-100">
      <textarea v-model="text" class="form-control pe-4 fs-3 lh-1 border-0" rows="15"
                                  placeholder="分享你的想法">
    </textarea>
    </form>
  </div>
  </div>
  </div>
  </div>
  <div v-for="(item,index) in textList" :key="index">
    <md-editor-v3   previewOnly="true" v-model="textList[index]"/>
  </div>
  <p>分隔</p>
</template>



<script>
export default {
  name: 'test_p',
  props: {

  }, data () {
    return {
      text:'Hello Editor!!',
      textList:[
          "dfdsf",
          "dfdfd",
          "qdfef",
      ]
      }

  },
  methods: {
      gethtml(h)
      {
        console.log(h)
      },
    gethtml1(h1)
    {
      console.log(h1)
    }
  }
}
</script>
<style scoped>
@import url(@/css/font.css);
@import url(@/css/all.min.css);
@import url(@/css/style.css);
</style>
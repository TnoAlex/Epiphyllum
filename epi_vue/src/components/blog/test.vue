<template>
<div>
    <div class="img" :style="{ 'backgroundImage': 'url('+img_047+')'} " style="background-position: center left;
      background-size: cover;
      width: 200px;
      height: 200px;"></div>

    </div>

</template>

<script>
import PhotoModal from "@/components/blog/photoPost";
const icon = "047.jpg"
export default {
  name: "test",
  components:{
     //eslint-disable-next-line vue/no-unused-components
    PhotoModal
  },
  data(){
    return{
      img_047: require("../../img/"+icon),
      showModal:false
    }
  }
}
</script>

<style scoped>
/*.img{*/
/*  background-position: center left;*/
/*  background-size: cover;*/
/*  width: 200px;*/
/*  height: 200px;*/
/*}*/
@import url(@/css/font.css);
@import url(@/css/all.min.css);
@import url(@/css/style.css);
</style>
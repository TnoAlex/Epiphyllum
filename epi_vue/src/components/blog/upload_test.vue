<template>


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
          <form class="w-100"><textarea class="form-control pe-4 fs-3 lh-1 border-0" rows="2"
                                        placeholder="分享你的想法"></textarea></form>


        </div><!-- Dropzone photo START -->

        <el-upload
            class="upload-demo"
            drag
            ref="imgupload"
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
              <!-- Due to close btn only appears when li gets focused disappears after li gets blurred, thus keyboard navigation can never reach close btn-->
              <!-- This is a bug which needs to be fixed --><!-- TODO: Fix the incorrect navigation interaction -->
              <span class="el-upload-list__item-actions">
<!--               <span class="el-upload-list__item-preview" @click="imagePreview">-->
<!--                 <i class="el-icon el-icon&#45;&#45;zoom-in">-->
<!--                   <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg"><path-->
<!--                       fill="currentColor"-->
<!--                       d="m795.904 750.72 124.992 124.928a32 32 0 0 1-45.248 45.248L750.656 795.904a416 416 0 1 1 45.248-45.248zM480 832a352 352 0 1 0 0-704 352 352 0 0 0 0 704zm-32-384v-96a32 32 0 0 1 64 0v96h96a32 32 0 0 1 0 64h-96v96a32 32 0 0 1-64 0v-96h-96a32 32 0 0 1 0-64h96z"></path></svg></i>-->
<!--               </span>-->
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
                data-bs-dismiss="modal">取消
        </button>
        <button type="button" class="btn btn-success-soft" @click="upload">发送</button>
      </div><!-- Modal feed footer -->
    </div>
  </div>
</template>


<script>

import {Delete, Download, Plus, ZoomIn} from '@element-plus/icons-vue'

console.log(Delete, Download, Plus, ZoomIn)
import {UploadFilled} from '@element-plus/icons-vue'

console.log(UploadFilled)
export default {
  name: "upload_test",
  methods: {
    myUploadSuccess(response, uploadFile, uploadFiles) {
      console.log(response)
      console.log(uploadFile)
      console.log(uploadFiles)
      console.log("输出上传图片列表", this.myUpFileList)
    }
    ,
    myUploadChange(uploadFile, uploadFiles) {
      console.log("change",uploadFile,uploadFiles)
      this.dialogImageUrl = URL.createObjectURL(uploadFile.raw);
      if(this.myUpFileList.length===0)
      {
        let imgUrlSuccess = URL.createObjectURL(uploadFile.raw)
        this.myUpFileList.push({uid:uploadFile.uid,name:uploadFile.name,imgSrc:imgUrlSuccess})
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
        this.myUpFileList.push({uid:uploadFile.uid,name:uploadFile.name,imgSrc:imgUrlSuccess})
      }
      uploadFiles = this.myUpFileList

    },
    myPreview(uploadFile) {
      console.log(uploadFile)
    },
    upload() {
      this.$refs.imgupload.submit()
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

  },
  data()
  {
    return {
      myUpFileList: [],
      myDeleteFileList:[],
      dialogImageUrl: "",
      dialogVisible: false,
      disabled: false,
    }
  }
}

</script>
<style scoped>
@import url(@/css/font.css);
@import url(@/css/all.min.css);
@import url(@/css/style.css);
</style>

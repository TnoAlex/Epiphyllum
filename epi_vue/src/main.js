import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'
import 'bootstrap-icons/font/bootstrap-icons.scss'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'overlayscrollbars/overlayscrollbars.css'
import 'tiny-slider/dist/tiny-slider.css'
import 'choices.js/public/assets/styles/choices.css'
import 'glightbox/dist/css/glightbox.css'
import 'glightbox/dist/css/glightbox.min.css'
import "glightbox/dist/css/plyr.css"
import 'flatpickr/dist/flatpickr.css'
import VueCookies from 'vue-cookies'
import router  from "@/router"
import axios from "axios"
import "md-editor-v3/lib/style.css"
import MdEditorV3 from 'md-editor-v3'

//评论工具引入
import 'gitalk/dist/gitalk.css'

const app = createApp(App)
app.use(ElementPlus)
app.use(router)
app.use(VueCookies)
app.use(MdEditorV3)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.config.globalProperties.$axios = axios
app.mount('#app')


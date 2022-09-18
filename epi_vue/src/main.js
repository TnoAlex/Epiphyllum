import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'
import 'bootstrap-icons/font/bootstrap-icons.scss'
import 'overlayscrollbars/overlayscrollbars.css'
import 'tiny-slider/dist/tiny-slider.css'
import 'choices.js/public/assets/styles/choices.css'
import 'glightbox/dist/css/glightbox.css'
import "glightbox/dist/css/plyr.css"
import 'flatpickr/dist/flatpickr.css'
import 'dropzone/src/dropzone.scss'
import 'dropzone/dist/dropzone.css'
import 'dropzone/src/basic.scss'
import router  from "@/router";
import axios from "axios"


//import Dropzone from 'dropzone'




const app = createApp(App)
app.use(ElementPlus)
app.use(router)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.config.globalProperties.$axios = axios
axios.defaults.baseURL='/api'
app.mount('#app')


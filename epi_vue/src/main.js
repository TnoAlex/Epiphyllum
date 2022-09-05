import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'
import 'tiny-slider/dist/tiny-slider.css'
import 'choices.js/public/assets/styles/choices.css'
import 'glightbox/dist/css/glightbox.css'
import "glightbox/dist/css/plyr.css"
import 'dropzone/dist/dropzone.css'
import 'flatpickr/dist/flatpickr.css'

import router  from "@/router";
import axios from "axios"

const app = createApp(App)
app.use(ElementPlus)
app.use(router)

app.config.globalProperties.$axios = axios
app.mount('#app')


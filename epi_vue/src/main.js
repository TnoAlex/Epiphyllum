import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'
import router  from "@/router";
import axios from "axios"

const app = createApp(App)
app.use(ElementPlus)
app.use(router)
app.config.globalProperties.$axios = axios
axios.defaults.baseURL='/api'
app.mount('#app')


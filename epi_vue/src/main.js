import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'

const app = createApp(App)
app.use(ElementPlus)
app.mount('#app')

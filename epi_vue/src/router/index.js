import {createRouter, createWebHistory} from "vue-router"

const router = createRouter({
        history: createWebHistory(),
        routes: [
            {
                path: "/login",
                name: "login",
                component: () => import ('@/components/Login')
            },
            {
                path:"/404",
                name:"404",
                component:()=>import('@/components/404')
            },
            {
                path:"/index",
                name:"index_vue",
                component:()=>import('@/components/blog/index')
            }

        ],
    }
)
export default router
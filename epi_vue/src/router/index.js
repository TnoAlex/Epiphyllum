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
            }
        ],
    }
)
export default router
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
            },
            {
                path:"/test",
                name:"test",
                component:()=>import('@/components/blog/test')
            },
            {
                path:"/test_index",
                name:"test_index",
                component:()=>import('@/components/blog/test_index')
            },
            {
                path:"/p",
                name:"test_P",
                component:()=>import('@/components/blog/photoPost')
            },
            {
                path:"/m",
                name:"test_m",
                component:()=>import('@/components/blog/mutai')
            },
            {
                path:'/u',
                name:"test_u",
                component:()=>import('@/components/blog/upload_test')
            },




        ],
    }
)
export default router
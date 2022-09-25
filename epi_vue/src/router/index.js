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
                path:"/ti",
                name:"test_index",
                component:()=>import('@/components/blog/test_index')
            },
            {
                path:"/p",
                name:"test_P",
                component:()=>import('@/components/blog/photoPost')
            },

            {
                path:'/u',
                name:"test_u",
                component:()=>import('@/components/blog/upload_test')
            },
            {
                path:'/notification',
                name:"notification",
                component:()=>import('@/components/notification/Notifications')
            },
            {
                path:'/tn',
                name:"tn",
                component:()=>import('@/components/notification/tn')
            },
            {
                path:'/img',
                name:"tn",
                component:()=>import('@/components/blog/imgTest')
            }



        ],
    }
)
export default router
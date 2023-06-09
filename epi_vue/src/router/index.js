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
                path: "/sign-in",
                name: "sign-in",
                component: () => import('@/components/Sign-in')
            },
            {
                path: "/sign-up",
                name: "sign-up",
                component: () => import('@/components/Sign-up')
            },
            {
                path:"/forgot-password",
                name:"forgot-password",
                component:()=>import('@/components/Forgot-Password')
            },
            {
                path: "/404",
                name: "404",
                component: () => import('@/components/404')
            },
            {
                path: "/index",
                name: "index",
                component: () => import('@/components/blog/index')
            },
            {
                path: "/test",
                name: "test",
                component: () => import('@/components/blog/test')
            },
            {
                path: "/ti",
                name: "test_index",
                component: () => import('@/components/blog/test_index')
            },
            {
                path: "/p",
                name: "test_P",
                component: () => import('@/components/blog/photoPost')
            },

            {
                path: '/u',
                name: "test_u",
                component: () => import('@/components/blog/upload_test')
            },
            {
                path: '/notification',
                name: "notification",
                component: () => import('@/components/notification/Notifications')
            },
            {
                path: '/tn',
                name: "tn",
                component: () => import('@/components/notification/tn')
            },
            {
                path:'/img',
                name:"tn",
                component:()=>import('@/components/blog/imgTest')
            },
            {
                path:'/group',
                name:"group",
                component:()=>import('@/components/group/groups')
            },
            {
                path:'/groupDetail/:group_info',
                name:'groupDetail',
                props:true,
                component:()=>import('@/components/group/groupDetail')
            },
            {
                path:'/race',
                name:'race',
                component:()=>import('@/components/competition/competitions')
            }

        ],
    }
)
export default router
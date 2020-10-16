import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store/index';

// Layouts
import Layout from '@/components/Layout/Layout'
import HeaderHorizontal from '@/components/Layout/HeaderHorizontal'
// SingleView
const SingleView = () => import ('@/views/SingleView/SingleView.vue')
// SubMenu
//StudyPlan Routes
import StudyPlanRoutes from '@/views/StudyPlan/routes'
import SubjectRoutes from '@/views/Subject/routes'
import Home from "@/views/Home";
import Login from "@/views/Login";
import Register from "@/views/Register";
import Website from "@/components/Layout/Website";
import LayoutPage from '@/components/Layout/LayoutPage'
// Pages
const Error404 = () => import('@/views/Pages/404.vue')
const Error500 = () => import('@/views/Pages/500.vue')
const Lock = () => import('@/views/Pages/Lock.vue')
const Maintenance = () => import('@/views/Pages/Maintenance.vue')
const Recover = () => import('@/views/Pages/Recover.vue')
const Template = () => import('@/views/Pages/Template.vue')


Vue.use(VueRouter)

let router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        // Not found route
        {
            path: '*',
            redirect: '/home'
        },

        {
            path: '/',
            component: Website,
            children: [
                {
                    path: '/home',
                    component: Home
                },
                {
                    path: '/login',
                    component: Login
                },
                {
                    path: '/register',
                    component: Register
                },
                {
                    path: '/profile',
                    name: 'profile',
                    // lazy-loaded
                    component: () => import('../views/Profile.vue')
                },
                {
                    path: '/admin',
                    name: 'admin',
                    // lazy-loaded
                    component: () => import('../views/BoardAdmin.vue')
                },
                {
                    path: '/mod',
                    name: 'moderator',
                    // lazy-loaded
                    component: () => import('../views/BoardModerator.vue')
                },
                {
                    path: '/user',
                    name: 'user',
                    // lazy-loaded
                    component: () => import('../views/BoardUser.vue')
                }
            ]
        },
        // Admin Pages
        {
            path: '/',
            component: Layout,
            children: [
                ...StudyPlanRoutes, ...SubjectRoutes,
                {path: '/singleview', component: SingleView},
            ]
        },
        {
            path: '/',
            component: LayoutPage,
            children: [
                {
                    component: Recover,
                    path: '/recover',
                }, {
                    component: Lock,
                    path: '/lock',
                }, {
                    component: Error404,
                    path: '/notfound',
                    name:'Error404'
                }, {
                    component: Error500,
                    path: '/error500'
                }, {
                    component: Maintenance,
                    path: '/maintenance'
                },
            ]
        }
    ]
})

let isAuthenticated = !(!store.state.auth.user);

router.beforeEach((to, from, next) => {
    if(to.role) {
        if(store.state.auth.user){
            if (to.name !== 'Login' && !isAuthenticated)
                next({ name: 'Login' })
            else if(this.currentUser.roles.includes(to.role)) {
                next()

            } else {
                next({ name: 'Error404' })
            }
        } else {
            next({ name: 'Login' })
        }
    } else {
        next()
    }

})

export default router;
import Vue from 'vue'
import VueRouter from 'vue-router'

// Layouts
import Layout from '@/components/Layout/Layout'
// SingleView
const SingleView = () => import ('@/views/SingleView/SingleView.vue')
// SubMenu
//StudyPlan Routes
import StudyPlanRoutes from '@/views/StudyPlan/routes'
import SubjectRoutes from '@/views/Subject/routes'
Vue.use(VueRouter)


export default new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            redirect: '/singleview'
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
        // Not found route
        {
            path: '*',
            redirect: '/'
        }
    ]
})
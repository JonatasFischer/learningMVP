const StudyPlanList = () => import ('@/views/StudyPlan/list.vue')
const StudyPlanEdit = () => import ('@/views/StudyPlan/edit.vue')

export default [
    {path: '/study-plan', component: StudyPlanList, name: "study-plan"},
    {path: '/study-plan/edit/:id', component: StudyPlanEdit},
    {path: '/study-plan/delete/:id', component: StudyPlanEdit},
    {path: '/study-plan/new', component: StudyPlanEdit},
]
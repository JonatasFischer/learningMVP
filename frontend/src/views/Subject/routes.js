const SubjectList = () => import ('@/views/Subject/list.vue')
const SubjectEdit = () => import ('@/views/Subject/edit.vue')

export default [
    {path: '/subject', component: SubjectList, name: "subject"},
    {path: '/subject/edit/:id', component: SubjectEdit},
    {path: '/subject/delete/:id', component: SubjectEdit},
    {path: '/subject/new', component: SubjectEdit},
]
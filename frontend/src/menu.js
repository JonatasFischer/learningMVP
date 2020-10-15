import StudyPlanMenus from '@/views/StudyPlan/menus'
import SubjectsMenus from '@/views/Subject/menus'

const Menu = [
    {
        heading: 'Main Navigation',
        translate: 'sidebar.heading.HEADER'
    },
    {
        name: 'Single View',
        path: '/home',
        icon : 'icon-grid',
        translate: 'sidebar.nav.menu.SINGLEVIEW'
    },
    {
        name: 'Menu',
        icon: 'icon-speedometer',
        translate: 'sidebar.nav.MENU',
        label: { value: 1, color: 'info' },
        submenu: [...StudyPlanMenus, ...SubjectsMenus]
    }
];

export default Menu;
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
        submenu: [{
            name: 'StudyPlan',
            translate: 'sidebar.nav.menu.STUDY_PLAN',
            path: ['/study-plan','/study-plan/edit/:index']
        }]
    }
];

export default Menu;
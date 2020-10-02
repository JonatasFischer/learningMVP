const Menu = [
    {
        heading: 'Main Navigation',
        translate: 'sidebar.heading.HEADER'
    },
    {
        name: 'Single View',
        path: 'singleview',
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
            path: ['study-plan','study-plan/{id}']
        }]
    }
];

export default Menu;
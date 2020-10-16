import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

import SettingModule from './modules/setting.js'
import SettingPlugin from './plugins/setting.js'
import ThemeModule from './modules/theme.js'
import ThemePlugin from './plugins/theme.js'
import { auth } from './auth/auth.module';

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        setting: SettingModule,
        theme: ThemeModule,
        auth
    },
    plugins: [
        createPersistedState({
            reducer: (persistedState) => {
                const stateFilter = JSON.parse(JSON.stringify(persistedState)); // deep clone
                ['offsidebarOpen', 'asideToggled', 'horizontal'] // states which we don't want to persist.
                    .forEach(item => delete stateFilter.setting[item])
                return stateFilter
            }
        }),
        SettingPlugin,
        ThemePlugin
    ]
})
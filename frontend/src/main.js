/*!
 *
 * Angle - Bootstrap Admin Template
 *
 * Version: 4.7.5
 * Author: @themicon_co
 * Website: http://themicon.co
 * License: https://wrapbootstrap.com/help/licenses
 *
 */

import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import VueI18Next from '@panter/vue-i18next';

import './vendor.js'

import App from './App.vue'
import router from './router'
import i18next from './i18n.js';
import store from './store'
import 'katex/dist/katex.min.css';
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import "vue-select/dist/vue-select.css";
import VeeValidate from 'vee-validate';
const config = {
    aria: true,
    classNames: {},
    classes: false,
    delay: 0,
    dictionary: null,
    errorBagName: 'errors', // change if property conflicts
    events: 'input|blur',
    fieldsBagName: 'validationFields',
    i18n: null, // the vue-i18n plugin instance
    i18nRootKey: 'validations', // the nested key under which the validation messages will be located
    inject: true,
    locale: 'en',
    validity: false,
    useConstraintAttrs: true
};




import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import {
    faHome,
    faUser,
    faUserPlus,
    faSignInAlt,
    faSignOutAlt
} from '@fortawesome/free-solid-svg-icons';
import httpInterceptor from './mixins/httpinterceptor'

library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

Vue.config.productionTip = false
Vue.use(VeeValidate, config);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.use(BootstrapVue);
Vue.use(VueI18Next);

const i18n = new VueI18Next(i18next);

new Vue({
    i18n,
    router,
    store,
    render: h => h(App),
    mixins: [httpInterceptor]
}).$mount('#app')

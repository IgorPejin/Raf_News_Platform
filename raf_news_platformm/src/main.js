import Vue from 'vue'
import App from './App.vue'
Vue.config.productionTip = false
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import router from "@/router";
import store from "@/store";

import VueRouter from 'vue-router';

Vue.use(VueRouter);
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)


new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

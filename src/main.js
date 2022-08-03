import Vue from "vue";
import App from "./App.vue";

Vue.config.productionTip = false;
Vue.prototype.$android = window._android_;
new Vue({
  render: (h) => h(App),
}).$mount("#app");

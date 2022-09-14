import Vue from "vue";
import App from "./App.vue";
import VideoPlayer from "vue-vjs-hls";

import android from "@/config/android";
import updateDpi from "@/config/dpi";
updateDpi();

Vue.config.productionTip = false;
Vue.prototype.$android = android;
Vue.prototype.$dpi = window.Detector;

VideoPlayer.config({
  youtube: false,
  switcher: true,
  hls: true,
});

Vue.use(VideoPlayer);

new Vue({
  render: (h) => h(App),
}).$mount("#app");

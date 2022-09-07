import Vue from "vue";
import App from "./App.vue";

Vue.config.productionTip = false;
Vue.prototype.$android = window._android_;
Vue.prototype.$dpi = window.Detector;
const minWidth = 1280;
if (window.screen.width < minWidth && window.Detector.devicePixelRatio > 1) {
  const scale = window.screen.width / minWidth;
  document
    .querySelector("meta[name=viewport]")
    .setAttribute(
      "content",
      `width=device-width, initial-scale=${scale}, maximum-scale=${scale}, user-scalable=0`
    );
}

new Vue({
  render: (h) => h(App),
}).$mount("#app");

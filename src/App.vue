<template>
  <div>
    <div class="app">
      <div class="app__wrapper">
        <FloatNavigation
          @change="onNavChange"
          @action="onNavAction"
          :time="timestamp"
        />
        <div class="app__container">
          <Applications v-if="screen === SCR_APPS" :items="applications" />
          <Channels v-if="screen === SCR_TV" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Applications from "./components/screens/Applications.vue";
import FloatNavigation from "./components/FloatNavigation.vue";
import {
  SCR_APPS,
  SCR_SETTINGS,
  SCR_STORE,
  SCR_TV,
  defaultScreen,
} from "./config/screens";
import { runAction } from "@/plugins/actions";
import Channels from "./components/screens/Channels.vue";

export default {
  name: "App",
  components: { Applications, FloatNavigation, Channels },
  data() {
    return {
      appList: [],
      SCR_APPS,
      SCR_SETTINGS,
      SCR_STORE,
      SCR_TV,
      screen: defaultScreen,
      screenSize: null,
      packageName: null,
      timestamp: "",
    };
  },
  computed: {
    dpiData() {
      return this.$dpi;
    },
    applications() {
      return this.appList.filter((i) => i.pname !== this.packageName);
    },
  },
  methods: {
    runAction,
    onNavChange(id) {
      this.screen = id;
    },
    onNavAction(id) {
      this.runAction(id);
    },
    updateAppList() {
      this.appList = this.$android.installedApps();
    },
    updateCurrentTime() {
      const today = new Date();
      const hour = this.pad(today.getHours());
      const minute = this.pad(today.getMinutes());
      this.timestamp = hour + ":" + minute;
    },
    pad(d) {
      return d < 10 ? "0" + d.toString() : d.toString();
    },
  },
  mounted() {
    this.screenSize = this.$android.screenSize();
    this.packageName = this.$android.packageName();
    this.$android.updateLoader(false);
    this.updateAppList();
    setInterval(this.updateAppList, 8000);
    setInterval(this.updateCurrentTime, 500);
  },
};
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap");
html,
body {
  font-family: "Inter", sans-serif;
  height: 100%;
  width: 100%;
  margin: 0px;
  padding: 0px;
  color: white;
}
* {
  box-sizing: border-box;
}
html {
  background: linear-gradient(108.38deg, #819fb1 0%, #506c7a 76.65%);
  background-image: url("https://wallpaperaccess.com/full/882671.jpg");
  background-size: cover;
  background-position: center;
}
body {
  display: block;
  background: rgba(15, 15, 15, 0.7);
}

h1 {
  font-size: 24px;
  font-weight: 600;
  margin: 0px 0px 32px 0px;
  padding: 0px;
}

.app {
  display: flex;
  align-items: center;
  position: relative;
  padding-top: 112px;
  &__wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
  }
  &__container {
    min-width: 1216px;
    width: 1216px;
    position: relative;
    margin-top: 3em;
  }
  &__list {
    width: calc(100% + 32px);
  }
}

@media screen and (min-width: 1592px) {
  .app {
    &__container {
      min-width: 1528px;
      width: 1528px;
      position: relative;
    }
  }
  h1 {
    font-size: 28px;
  }
}
</style>

<template>
  <div>
    <div class="app">
      <div class="app__wrapper">
        <FloatNavigation @change="onNavChange" @action="onNavAction" />
        <div class="app__container">
          <Applications v-if="screen === SCR_APPS" :items="appList" />
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
    };
  },
  computed: {
    dpiData() {
      return this.$dpi;
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
  },
  created() {
    this.appList = this.$android.installedApps();
    this.screenSize = this.$android.screenSize();
    this.$android.updateLoader(false);
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
  background: linear-gradient(108.38deg, #435159 0%, #20292e 76.65%);
}
body {
  display: block;
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
}
</style>

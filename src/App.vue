<template>
  <div>
    <div class="app">
      <div class="app__wrapper">
        <FloatNavigation />
        <div class="app__container">
          <h1>Список приложений</h1>
          <ApplicationList v-if="appList">
            <ApplicationItem
              v-for="app in appList"
              :key="app.pname"
              :app="app"
              @click-app="() => clickApp(app)"
            />
          </ApplicationList>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ApplicationItem from "./components/ApplicationItem.vue";
import ApplicationList from "./components/ApplicationList.vue";
import FloatNavigation from "./components/FloatNavigation.vue";
export default {
  name: "App",
  components: { ApplicationItem, ApplicationList, FloatNavigation },
  data() {
    return {
      message: "Привет, Vue!",
      appList: [],
    };
  },
  computed: {
    dpiData() {
      return this.$dpi;
    },
  },
  methods: {
    clickApp(app) {
      this.$android.runApplication(app.pname);
    },
    openSettings() {
      this.$android.openSettings();
    },
    showToast() {
      this.$android.showToast("Focused");
    },
  },
  created() {
    this.appList = JSON.parse(this.$android.installedApps());
    console.log("Hehehe");
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
</style>

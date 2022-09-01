<template>
  <div>
    <div class="app">
      <FloatNavigation />
      <div class="app__container">
        <h1>Список приложений</h1>
        <ApplicationList v-if="appList">
          <ApplicationItem v-for="app in appList" :key="app.pname" :app="app" />
        </ApplicationList>
      </div>
    </div>
  </div>
</template>

<script>
import exampleImage from "./assets/exampleapp.png";
import ApplicationItem from "./components/ApplicationItem.vue";
import ApplicationList from "./components/ApplicationList.vue";
import FloatNavigation from "./components/FloatNavigation.vue";
export default {
  name: "App",
  components: { ApplicationItem, ApplicationList, FloatNavigation },
  data() {
    return {
      message: "Привет, Vue!",
      appList: [
        {
          pname: "meme",
          appname: "Yandex Music",
          hasBanner: true,
          banner: exampleImage,
        },
        {
          pname: "meme2",
          appname: "Yandex Music App",
          hasBanner: false,
          icon: exampleImage,
        },
        {
          pname: "meme3",
          appname: "Приложение",
          hasBanner: true,
          banner: exampleImage,
        },
        {
          pname: "meme4",
          appname: "Приложение",
          hasBanner: true,
          banner: exampleImage,
        },
        {
          pname: "meme5",
          appname: "Приложение",
          hasBanner: true,
          banner: exampleImage,
        },
        {
          pname: "meme6",
          appname: "Приложение",
          hasBanner: true,
          banner: exampleImage,
        },
      ], // JSON.parse(this.$android.installedApps()),
    };
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
};
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap");
html,
body {
  background-color: #1d1f24;
  font-family: "Inter", sans-serif;
  background: linear-gradient(108.38deg, #435159 0%, #20292e 76.65%);
  min-height: 100%;
  min-width: 100%;
  margin: 0px;
  padding: 0px;
  color: white;
}
* {
  box-sizing: border-box;
}
body {
  padding: 32px;
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
  flex-direction: column;
  align-items: center;
  &__container {
    width: 1536px;
  }
}

@media all and (max-width: 1590px) {
  .app {
    &__container {
      width: 1216px;
    }
  }
}
</style>

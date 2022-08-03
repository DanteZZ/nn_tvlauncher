<template>
  <div>
    <button @click="openSettings" class="settings" @focus="showToast">
      Открыть настройки
    </button>
    <div class="app-grid">
      <button
        v-for="app in appList"
        :key="app.pname"
        @click="runApp(app.pname)"
        class="app-grid__item"
        :class="{ 'app-grid__item--nobanner': !app.hasBanner }"
      >
        <img
          :src="`data:image/png;base64,${
            app.hasBanner ? app.banner : app.icon
          }`"
          alt=""
          class="app-grid__item-image"
        />
        <div class="app-grid__item-name">{{ app.appname }}</div>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      message: "Привет, Vue!",
      appList: JSON.parse(this.$android.installedApps()),
    };
  },
  methods: {
    runApp(name) {
      this.$android.runApplication(name);
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
html,
body {
  background-color: #1d1f24;
}
.app {
  &-grid {
    display: flex;
    align-items: center;
    justify-content: space-around;
    flex-wrap: wrap;
    width: 100%;
    &__item {
      max-width: 213px;
      max-height: 120px;
      width: 213px;
      height: 120px;
      position: relative;
      border-radius: 8px;
      margin: 1em;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      border: 0px;
      transition: 0.2s;
      &:focus {
        outline: 0;
        box-shadow: rgba(47, 27, 162, 0.597) 0px 48px 100px 0px;
        & .app-grid__item-name {
          opacity: 1;
          bottom: -1em;
        }
      }
      &-image {
        position: absolute;
        left: 0px;
        top: 0px;
        right: 0px;
        bottom: 0px;
        object-fit: cover;
        border-radius: 8px;
      }
      &-name {
        position: absolute;
        bottom: 8px;
        color: white;
        font-weight: 800;
        opacity: 0;
        transition: 0.2s;
      }
      &--nobanner {
        & .app-grid__item-image {
          position: inherit;
          border-radius: 4px;
        }
      }
    }
  }
}
.settings:focus {
  color: red;
}
</style>

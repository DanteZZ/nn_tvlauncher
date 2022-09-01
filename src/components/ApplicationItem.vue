<template>
  <button
    @click="$emit('click-app')"
    class="app__item"
    :class="{ 'app__item--nobanner': !app.hasBanner }"
  >
    <img
      :src="`${app.hasBanner ? app.banner : app.icon}`"
      alt=""
      class="app__item-image"
    />
    <span class="app__item-name" v-if="!app.hasBanner">{{ app.appname }}</span>
  </button>
</template>
<script>
export default {
  name: "application-item",
  props: {
    app: {
      type: Object,
      required: true,
    },
  },
};
</script>
<style lang="scss">
.app {
  &__item {
    min-height: 160px;
    max-height: 160px;
    position: relative;
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border: 0px;
    transition: 0.2s;
    &:focus,
    &:hover {
      cursor: pointer;
      outline: 0;
      box-shadow: rgba(27, 81, 162, 0.37) 0px 48px 100px 0px;
      z-index: 10;
      margin-top: -8px;
    }
    &-image {
      position: absolute;
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 8px;
    }
    &--nobanner {
      display: flex;
      flex-direction: row;
      background: rgba(240, 240, 240, 0.04);
      padding: 2em;
      align-items: center;
      justify-content: center;
      & .app__item {
        &-image {
          position: inherit;
          border-radius: 4px;
          width: 68px;
          height: 68px;
          position: relative;
          right: auto;
          bottom: auto;
          margin-right: 1.5em;
        }
        &-name {
          position: relative;
          color: white;
          font-size: 24px;
          font-weight: 500;
          text-align: left;
        }
      }
    }
  }
}
</style>

<template>
  <button
    v-if="app"
    @click="$emit('click-app')"
    class="app__item"
    :class="{ 'app__item--nobanner': !app.hasBanner }"
  >
    <img
      :src="`data:image/png;base64,${app.hasBanner ? app.banner : app.icon}`"
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
      type: [Object, Number, Boolean],
      required: true,
    },
  },
};
</script>
<style lang="scss">
.app {
  &__item {
    min-height: 156px;
    height: 156px;
    min-width: 280px;
    width: 280px;
    margin: 0em 16px 32px 16px;
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
      box-shadow: rgba(0, 0, 0, 0.1) 0px 48px 100px 0px;
      z-index: 10;
      transform: scale(1.2);
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
          object-fit: contain;
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

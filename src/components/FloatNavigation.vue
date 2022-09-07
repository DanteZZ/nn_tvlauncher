<template>
  <div class="app__navigation">
    <div class="app__navigation-offset"></div>
    <div class="app__navigation-menu">
      <div
        v-show="isFocus"
        class="app__navigation-item--select"
        :style="{ left: `${focusLeft}px` }"
      ></div>
      <button
        v-for="item in items"
        :key="item.id"
        class="app__navigation-item"
        :class="{
          'app__navigation-item--selected': item.id === selectedItem,
          'app__navigation-item--focused': item.id === focusedItem,
        }"
        @focus="(e) => focusItem(e, item.id)"
        @blur="() => blurItem(item.id)"
        @click="() => selectItem(item)"
      >
        {{ item.name }}
      </button>
    </div>
  </div>
</template>
<script>
export default {
  name: "float-navigation",
  data() {
    return {
      isFocus: true,
      focusLeft: 0,
      focusedItem: "apps",
      selectedItem: "apps",
      items: [
        { id: "apps", name: "Приложения" },
        { id: "tv", name: "Каналы" },
        { id: "store", name: "Магазин" },
        { id: "settings", name: "Настройки" },
      ],
    };
  },
  methods: {
    focusItem(e, id) {
      this.focusLeft = e.target.offsetLeft || 0;
      this.focusedItem = id;
      this.isFocus = true;
    },
    selectItem(item) {
      this.selectedItem = item.id;
      this.$emit("select", item);
    },
    blurItem(id) {
      setTimeout(() => {
        if (this.focusedItem === id) {
          this.focusedItem = null;
          this.isFocus = false;
        }
      }, 100);
    },
  },
};
</script>
<style lang="scss">
.app__navigation {
  position: relative;
  display: flex;
  align-items: center;
  position: absolute;
  width: 100%;
  top: 32px;
  &-offset {
    width: 100%;
    height: 80px;
  }
  &-menu {
    position: absolute;
    top: 0px;
    position: absolute;
    left: 50%;
    transform: translate(-50%, 0%);
    margin: auto;
    background: rgba(0, 0, 0, 0.4);
    border-radius: 40px;
    display: flex;
  }
  &-item {
    font-size: 20px;
    font-weight: 600;
    color: rgba(255, 255, 255, 0.6);
    background: none;
    border: none;
    outline: none;
    width: 182px;
    padding: 8px;
    z-index: 10;
    transition: 0.2s;
    &--selected {
      color: white;
    }
    &--focused {
      color: rgba(28, 34, 38, 1);
    }
    &--select {
      position: absolute;
      background-color: white;
      left: 0px;
      top: -4px;
      height: 48px;
      width: 182px;
      border-radius: 30px;
      transition: 0.2s;
    }
  }
}
</style>

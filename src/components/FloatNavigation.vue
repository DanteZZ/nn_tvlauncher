<template>
  <div class="app__navigation">
    <div class="app__navigation-offset">
      <div class="app__navigation-logo"><img :src="logo" /></div>
      <div class="app__navigation-time">{{ time }}</div>
    </div>
    <div class="app__navigation-menu">
      <div
        v-show="isFocus"
        class="app__navigation-item--select"
        :style="{ left: `${focusLeft}px` }"
      ></div>
      <button
        v-for="item in items"
        :ref="'item-' + item.id"
        :key="item.id"
        class="app__navigation-item"
        :class="{
          'app__navigation-item--selected': item.id === selectedItem,
          'app__navigation-item--focused': item.id === focusedItem,
        }"
        @focus="(e) => focusItem(e.target, item.id)"
        @mousemove="(e) => focusItem(e.target, item.id)"
        @blur="() => blurItem(item.id)"
        @click="() => selectItem(item)"
      >
        {{ item.name }}
      </button>
    </div>
  </div>
</template>
<script>
import screens, { defaultScreen } from "@/config/screens";
import logo from "@/assets/logo.svg";
export default {
  name: "float-navigation",
  props: {
    time: { type: [String, Number, Boolean], default: null },
  },
  data() {
    return {
      isFocus: false,
      focusLeft: 0,
      focusedItem: null,
      selectedItem: defaultScreen,
      items: screens,
      logo,
    };
  },
  methods: {
    focusItem(e, id) {
      this.focusLeft = e.offsetLeft || 0;
      this.focusedItem = id;
      this.isFocus = true;
    },
    selectItem(item) {
      if (!item.action) {
        this.selectedItem = item.id;
        this.$emit("change", item.id);
      } else {
        this.$emit("action", item.id);
      }
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
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  top: 0px;
  padding-top: 32px;

  &-offset {
    width: 1216px;
    height: 80px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
  }
  &-time {
    font-size: 32px;
    font-weight: 300;
  }
  &-menu {
    position: absolute;
    top: calc(50% + 16px);
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
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

@media screen and (min-width: 1592px) {
  .app__navigation {
    &-logo {
      & > img {
        height: 38px;
      }
    }
    &-offset {
      width: 1528px;
      height: 80px;
    }
    &-item {
      font-size: 24px;
      padding: 12px;
      width: 216px;
      &--select {
        height: 58px;
        width: 216px;
      }
    }
  }
}
</style>

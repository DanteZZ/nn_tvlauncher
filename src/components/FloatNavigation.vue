<template>
  <div class="app__navigation">
    <div class="app__navigation-offset"></div>
    <div class="app__navigation-menu">
      <button class="app__navigation-item app__navigation-item--selected">
        Приложения
      </button>
      <button class="app__navigation-item">Приложения</button>
      <button class="app__navigation-item">Каналы</button>
    </div>
  </div>
</template>
<script>
export default {
  name: "float-navigation",
};
</script>
<style lang="scss">
.app__navigation {
  position: relative;
  display: flex;
  align-items: center;
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
    &--selected {
      position: relative;
      color: rgba(28, 34, 38, 1);
      &:before {
        content: "";
        position: absolute;
        background-color: white;
        left: 0px;
        top: -4px;
        height: 48px;
        width: 100%;
        border-radius: 30px;
        z-index: -1;
      }
    }
  }
}
</style>

<template>
  <div class="app__list">
    <slot></slot>
  </div>
</template>
<script>
export default {
  name: "application-list",
};
</script>
<style lang="scss">
.app__list {
  display: flex;
  margin-left: -16px;
  margin-right: -16px;
  align-items: center;
  flex-wrap: wrap;
}
</style>

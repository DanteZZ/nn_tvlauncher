<template>
  <div class="app__list">
    <slot></slot>
  </div>
</template>
<script>
export default {
  name: "application-list",
  props: {
    app: {
      type: Object,
      required: true,
    },
  },
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

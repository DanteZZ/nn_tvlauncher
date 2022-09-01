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
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  align-items: center;
  grid-gap: 23px;
}

@media all and (max-width: 1590px) {
  .app__list {
    grid-template-columns: 1fr 1fr 1fr 1fr;
  }
}
</style>

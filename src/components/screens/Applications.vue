<template>
  <div>
    <h1>Список приложений</h1>
    <ApplicationList v-if="items">
      <ApplicationItem
        v-for="app in items"
        :key="app.pname"
        :app="app"
        @click-app="() => clickApp(app)"
      />
    </ApplicationList>
  </div>
</template>
<script>
import ApplicationItem from "../ApplicationItem.vue";
import ApplicationList from "../ApplicationList.vue";
export default {
  name: "screen-applications",
  components: { ApplicationItem, ApplicationList },
  props: {
    items: { type: Array, required: false, default: () => [] },
  },
  methods: {
    clickApp(app) {
      this.$android.runApplication(app.pname);
    },
  },
};
</script>

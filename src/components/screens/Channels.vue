<template>
  <div>
    <h1>Список каналов</h1>
    <video-player :options="videoOptions"></video-player>
  </div>
</template>
<script>
export default {
  name: "screen-channels",
  props: {},
  data() {
    return {
      videoOptions: {
        source: {
          type: "application/x-mpegURL",
          src: "http://109.248.63.9:1013/sts/index.m3u8",
          withCredentials: false,
        },
        live: true,
      },
    };
  },
  methods: {},
};
</script>

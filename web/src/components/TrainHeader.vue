<script>
import { defineComponent, ref } from 'vue';
export default defineComponent({
  name: "TrainHeader",
  setup() {
    return {
      selectedKeys1: ref(['2']),
    };
  }
});

</script>

<template>
  <a-layout-header id="components-layout-demo-top-side" class="header">
    <div class="logo" />
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">nav 11</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<style scoped>


</style>
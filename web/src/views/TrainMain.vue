<template>
  <a-layout>
  <TrainHeader></TrainHeader>
    <a-layout-content style="padding: 0 50px">
      <a-breadcrumb style="margin: 16px 0">
        <a-breadcrumb-item>Home</a-breadcrumb-item>
        <a-breadcrumb-item>List</a-breadcrumb-item>
        <a-breadcrumb-item>App</a-breadcrumb-item>
      </a-breadcrumb>
      <a-layout style="padding: 20px; background: #fff">
        <TrainSiderBar></TrainSiderBar>
        <a-layout-content :style="{ padding: '0 24px', minHeight: '500px' }">
          Content
        </a-layout-content>
      </a-layout>
    </a-layout-content>
    <a-layout-footer style="text-align: center">
      12306 train system ©2024 Created by MR.Liu
    </a-layout-footer>
  </a-layout>
</template>
<script>
import { defineComponent, ref } from 'vue';
import TrainHeader from "@/components/TrainHeader.vue";
import TrainSiderBar from "@/components/TrainSiderBar.vue";
export default defineComponent({
  components: {
    TrainSiderBar,
    TrainHeader,

  },
  setup() {
    return {
      selectedKeys1: ref(['2']),
      selectedKeys2: ref(['1']),
      openKeys: ref(['sub1']),
    };
  },
});
</script>
<style>
#components-layout-demo-top-side .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>
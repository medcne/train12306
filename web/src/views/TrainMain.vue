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
          所有人数：{{count}}
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
import axios from "axios";
import {notification} from "ant-design-vue";
export default defineComponent({
  components: {
    TrainSiderBar,
    TrainHeader,
  },
  setup() {
    const count = ref(0)

    axios.get("http://localhost:8000/member/count").then((response) => {
      let data = response.data;
      if (data.success) {
        count.value = data.content;
      } else {
        notification.error({description: data.message});
      }
    });
    return {
      selectedKeys1: ref(['2']),
      selectedKeys2: ref(['1']),
      openKeys: ref(['sub1']),
      count,
    };
  }

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
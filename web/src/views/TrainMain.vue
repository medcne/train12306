<template>
  <a-layout>
    <TrainHeader></TrainHeader>
    <a-layout>
      <TrainSiderBar></TrainSiderBar>
      <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <router-view></router-view>
      </a-layout-content>
    </a-layout>
      <a-layout-footer style="text-align: center">
        12306 train system 2024-RC1 Created by MR.Liu<br>

      </a-layout-footer>
  </a-layout>
</template>
<script>
import { defineComponent } from 'vue';
import TrainHeader from "@/components/TrainHeader.vue";
import TrainSiderBar from "@/components/TrainSiderBar.vue";

export default defineComponent({
  components: {
    TrainSiderBar,
    TrainHeader,
  },
  setup() {
    return {
    };
  }

});
</script>
<style>

</style>
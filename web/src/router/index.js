import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'main',
    component: () => import('../views/TrainMain.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: ()=> import('../views/TrainLogin.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

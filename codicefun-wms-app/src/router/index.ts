import { createRouter, createWebHistory } from 'vue-router'
import { routes } from '@/router/routes'
import { useUserStore } from '@/stores'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: routes
})

router.beforeEach((to, from) => {
  const userStore = useUserStore()
  const token = userStore.token

  if (token !== '') {
    if (to.path === '/login') {
      return '/'
    }
  } else {
    if (to.path !== '/login' && to.path !== '/register') {
      return '/login'
    }
  }
})

export default router

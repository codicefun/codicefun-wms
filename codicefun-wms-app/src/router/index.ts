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

  // Have login
  if (to.path === '/login' && token !== '') {
    return '/'
  }

  // Not login
  if (to.path !== '/login' && token === '') {
    return '/login'
  }
})

export default router

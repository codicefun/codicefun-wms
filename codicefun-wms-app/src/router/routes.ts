import type { RouteRecordRaw } from 'vue-router'
import { Layout } from '@/layout/index'
import { HomeView, LoginView, UserView, WarehouseView } from '@/views'

export const routes: Readonly<RouteRecordRaw[]> = [
  {
    path: '',
    component: () => Layout,
    children: [
      {
        path: '',
        component: () => HomeView
      },
      {
        path: '/user',
        component: () => UserView
      },
      {
        path: '/warehouse',
        component: () => WarehouseView
      }
    ]
  },
  {
    path: '/login',
    component: () => LoginView
  }
]

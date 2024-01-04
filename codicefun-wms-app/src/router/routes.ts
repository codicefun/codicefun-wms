import type { RouteRecordRaw } from 'vue-router'
import { Layout } from '@/layout/index'
import { GoodsTypeView, GoodsView, HomeView, LoginView, RegisterView, UserView, WarehouseView } from '@/views'

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
      },
      {
        path: '/goods/type',
        component: () => GoodsTypeView
      },
      {
        path: '/goods',
        component: () => GoodsView
      }
    ]
  },
  {
    path: '/login',
    component: () => LoginView
  },
  {
    path: '/register',
    component: () => RegisterView
  }
]

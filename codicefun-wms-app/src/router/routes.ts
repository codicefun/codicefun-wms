import type { RouteRecordRaw } from 'vue-router'
import { Layout } from '@/layout/index'
import { HomeView, LoginView, RoleView, UserView } from '@/views'

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
        path: '/role',
        component: () => RoleView
      }
    ]
  },
  {
    path: '/login',
    component: () => LoginView
  }
]

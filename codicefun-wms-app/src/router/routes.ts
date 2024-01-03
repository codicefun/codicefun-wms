import type { RouteRecordRaw } from 'vue-router'
import { Layout } from '@/layout/index'
import { HomeView, RoleView, UserView } from '@/views'

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
  }
]

import request from '@/request'
import type { User } from './type'
import type { Pagination } from '@/request/type'
import type { AxiosRequestConfig } from 'axios'

const API_USER = '/user'

export const reqGetById = (id: number) => {
  return request.get<any, User>(`${API_USER}/${id}`)
}

export const reqUpdateById = (id: number, data: User) => {
  return request.put(`${API_USER}/${id}`, data)
}

export const reqDeleteById = (id: number) => {
  return request.delete(`${API_USER}/${id}`)
}

export const reqList = (config?: AxiosRequestConfig) => {
  return request.get<null, Pagination<User>>(API_USER, config)
}

export const reqCreate = (data: User) => {
  return request.post(API_USER, data)
}

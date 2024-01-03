import request from '@/request'
import type { User } from './type'
import type { Pagination } from '@/request/type'

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

export const reqList = () => {
  return request.get<any, Pagination<User>>(API_USER)
}

export const reqCreate = (data: User) => {
  return request.post(API_USER, data)
}

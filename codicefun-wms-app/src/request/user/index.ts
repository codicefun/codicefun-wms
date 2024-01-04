import request from '@/request'
import type { LoginData, Token, User } from './type'
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

export const reqUserList = (config?: AxiosRequestConfig) => {
  return request.get<null, Pagination<User>>(API_USER, config)
}

export const reqCreate = (data: User) => {
  return request.post(API_USER, data)
}

export const reqChangeState = (id: number) => {
  return request.put(`${API_USER}/${id}/state`, null)
}

export const reqLogin = (data: LoginData) => {
  return request.post<LoginData, Token>(`${API_USER}/login`, data)
}

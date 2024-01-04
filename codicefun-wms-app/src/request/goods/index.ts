import request from '@/request'
import type { AxiosRequestConfig } from 'axios'
import type { Pagination } from '@/request/type'
import type { Goods } from '@/request/goods/type'

const API_GOOD = '/goods'

export const reqGetById = (id: number) => {
  return request.get<any, Goods>(`${API_GOOD}/${id}`)
}

export const reqUpdateById = (id: number, data: Goods) => {
  return request.put(`${API_GOOD}/${id}`, data)
}

export const reqDeleteById = (id: number) => {
  return request.delete(`${API_GOOD}/${id}`)
}

export const reqList = (config?: AxiosRequestConfig) => {
  return request.get<null, Pagination<Goods>>(API_GOOD, config)
}

export const reqCreate = (data: Goods) => {
  return request.post(API_GOOD, data)
}

import request from '@/request'
import type { AxiosRequestConfig } from 'axios'
import type { Pagination } from '@/request/type'
import type { Goods } from '@/request/goods/type'
import type { AmountForm } from '@/views/type'

const API_GOODS = '/goods'

export const reqGetById = (id: number) => {
  return request.get<any, Goods>(`${API_GOODS}/${id}`)
}

export const reqUpdateById = (id: number, data: Goods) => {
  return request.put(`${API_GOODS}/${id}`, data)
}

export const reqDeleteById = (id: number) => {
  return request.delete(`${API_GOODS}/${id}`)
}

export const reqList = (config?: AxiosRequestConfig) => {
  return request.get<null, Pagination<Goods>>(API_GOODS, config)
}

export const reqCreate = (data: Goods) => {
  return request.post(API_GOODS, data)
}

export const reqChangeAmount = (id: number, data: AmountForm) => {
  return request.put(`${API_GOODS}/${id}/amount`, data)
}

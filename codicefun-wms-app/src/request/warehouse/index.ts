import request from '@/request'
import type { User } from '@/request/user/type'
import type { AxiosRequestConfig } from 'axios'
import type { Pagination } from '@/request/type'
import type { Warehouse } from '@/request/warehouse/type'

const API_WAREHOUSE = '/warehouse'

export const reqGetById = (id: number) => {
  return request.get<any, User>(`${API_WAREHOUSE}/${id}`)
}

export const reqUpdateById = (id: number, data: Warehouse) => {
  return request.put(`${API_WAREHOUSE}/${id}`, data)
}

export const reqDeleteById = (id: number) => {
  return request.delete(`${API_WAREHOUSE}/${id}`)
}

export const reqList = (config?: AxiosRequestConfig) => {
  return request.get<null, Pagination<Warehouse>>(API_WAREHOUSE, config)
}

export const reqCreate = (data: Warehouse) => {
  return request.post(API_WAREHOUSE, data)
}

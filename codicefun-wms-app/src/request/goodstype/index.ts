import request from '@/request'
import type { AxiosRequestConfig } from 'axios'
import type { Pagination } from '@/request/type'
import type { GoodsType } from '@/request/goodstype/type'

const API_GOODS_TYPE = '/goods/type'

export const reqGetById = (id: number) => {
  return request.get<any, GoodsType>(`${API_GOODS_TYPE}/${id}`)
}

export const reqUpdateById = (id: number, data: GoodsType) => {
  return request.put(`${API_GOODS_TYPE}/${id}`, data)
}

export const reqDeleteById = (id: number) => {
  return request.delete(`${API_GOODS_TYPE}/${id}`)
}

export const reqTypeList = (config?: AxiosRequestConfig) => {
  return request.get<null, Pagination<GoodsType>>(API_GOODS_TYPE, config)
}

export const reqCreate = (data: GoodsType) => {
  return request.post(API_GOODS_TYPE, data)
}

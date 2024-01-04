import request from '@/request'
import type { AxiosRequestConfig } from 'axios'
import type { Pagination } from '@/request/type'
import type { Record } from '@/request/record/type'

const API_RECORD = '/record'

export const reqDeleteById = (id: number) => {
  return request.delete(`${API_RECORD}/${id}`)
}

export const reqTypeList = (config?: AxiosRequestConfig) => {
  return request.get<null, Pagination<Record>>(API_RECORD, config)
}

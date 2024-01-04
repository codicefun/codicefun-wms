import axios, { type AxiosInstance, type AxiosRequestConfig, type AxiosResponse } from 'axios'
import { ElMessage } from 'element-plus'
import type { Response } from '@/request/type'
import { useUserStore } from '@/stores'

export class Request {
  instance: AxiosInstance
  config: AxiosRequestConfig = { baseURL: import.meta.env.VITE_API_PATH, timeout: 60000 }

  constructor(config: AxiosRequestConfig) {
    this.instance = axios.create(Object.assign(this.config, config))

    this.instance.interceptors.request.use(
      (config) => {
        const userStore = useUserStore()
        const token = userStore.token

        if (token !== '') {
          config.headers.token = token
        }

        return config
      }
    )

    this.instance.interceptors.response.use(
      (response: AxiosResponse) => {
        return response.data
      },
      (error: any) => {
        let message: string

        switch (error.response?.status) {
          case 400:
            message = '请求错误(400)'
            break
          case 401:
            message = '未授权，请重新登录(401)'
            break
          case 403:
            message = '拒绝访问(403)'
            break
          case 404:
            message = '请求出错(404)'
            break
          case 408:
            message = '请求超时(408)'
            break
          case 500:
            message = '服务器错误(500)'
            break
          case 501:
            message = '服务未实现(501)'
            break
          case 502:
            message = '网络错误(502)'
            break
          case 503:
            message = '服务不可用(503)'
            break
          case 504:
            message = '网络超时(504)'
            break
          case 505:
            message = 'HTTP版本不受支持(505)'
            break
          default:
            message = `连接出错(${error.response?.status ?? error.message})!`
        }

        ElMessage({
          showClose: true,
          message: message,
          type: 'error'
        })

        return Promise.reject(error.response)
      }
    )
  }

  public request<T = any, R = any>(config: AxiosRequestConfig<T>): Promise<Response<R>> {
    return this.instance.request(config)
  }

  public get<T = any, R = any>(url: string, config?: AxiosRequestConfig<T>): Promise<Response<R>> {
    return this.instance.get(url, config)
  }

  public post<T = any, R = any>(url: string, data: T, config?: AxiosRequestConfig<T>): Promise<Response<R>> {
    return this.instance.post(url, data, config)
  }

  public put<T = any, R = any>(url: string, data: T, config?: AxiosRequestConfig<T>): Promise<Response<R>> {
    return this.instance.put(url, data, config)
  }

  public delete<T = any, R = any>(url: string, config?: AxiosRequestConfig<T>): Promise<Response<R>> {
    return this.instance.delete(url, config)
  }
}

export default new Request({})

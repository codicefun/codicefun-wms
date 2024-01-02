export type Response<T> = {
  code: number,
  message: string,
  data: T
}

export type Pagination<T> = {
  total: number,
  current: number,
  size: number,
  list: T[]
}

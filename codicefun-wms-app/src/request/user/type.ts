export type User = {
  id: number
  username: string
  password: string
  nickname: string
  age: number
  sex: number
  phone: string
  role: number
  state: number
  isDeleted: number
}

export type LoginData = {
  username: string
  password: string
}

export type Token = {
  token: string
}

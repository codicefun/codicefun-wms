/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_APP_TITLE: string
  readonly VITE_API_PATH: string
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}

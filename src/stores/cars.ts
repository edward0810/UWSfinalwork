import http from '@/utils/http'
import { defineStore } from 'pinia'

export type Data = {
  brandId?: number | string
  file?: any
  carName?: string
  level?: string
  environmentProtection?: string
  maxPower?: string
  maxTorque?: string
  engine?: string
  gearbox?: string
  size?: string
  bodyStructure?: string
  maxSpeed?: string
  wltc?: string
  warranty?: string
  price?: string
  commentStr?: string
  comment?: Array<string>
  [index: string]: unknown
}
export type PostData = {
  searchText: string,
  page: number,
  rows: number,
  brandId: number,
  priceFilter: number,
  typeFilter: string
}
// export type DevicePostData = {
//   deviceId: number,
//   lastTestPerson?: string,
//   type?: number
// }
export const useCarsStore = defineStore('cars', () => {
  /** 获取汽车列表 */
  function getTableDataAction(payload: PostData){
    return http.post('/configure/selectCarList', payload);
  }
  /** 添加汽车 */
  function insertDataAction(payload: Data){
    return http.postU('/configure/insertCarInfo', payload);
  }
  /** 修改汽车 */
  function updateDataAction(payload: Data){
    return http.post('/configure/updateCarInfo', payload);
  }
  /** 删除汽车 */
  function deleteDataAction(payload: Data){
    return http.post('/configure/deleteCarInfo', payload);
  }

  return { 
    getTableDataAction,
    insertDataAction,
    updateDataAction,
    deleteDataAction
  }
})

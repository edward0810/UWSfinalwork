import http from '@/utils/http'
import { defineStore } from 'pinia'

export type Data = {
  brandId?: number
  brandName?: string
  image?: string
  type?: number
  file?: any
  [index: string]: unknown
}
export type PostData = {
  searchText: string,
  page: number,
  rows: number,
  type: number
}
// export type DevicePostData = {
//   deviceId: number,
//   lastTestPerson?: string,
//   type?: number
// }
export const useBrandsStore = defineStore('brands', () => {
  /** 获取品牌列表 */
  function getTableDataAction(payload: PostData){
    return http.post('/configure/selectBrandList', payload);
  }
  /** 添加品牌 */
  function insertDataAction(payload: Data){
    return http.postU('/configure/insertBrandInfo', payload);
  }
  /** 修改品牌 */
  function updateDataAction(payload: Data){
    return http.post('/configure/updateBrandInfo', payload);
  }
  /** 删除品牌 */
  function deleteDataAction(payload: Data){
    return http.post('/configure/deleteBrandInfo', payload);
  }

  return { 
    getTableDataAction,
    insertDataAction,
    updateDataAction,
    deleteDataAction
  }
})

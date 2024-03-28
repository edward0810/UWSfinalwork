import http from '@/utils/http'
import { defineStore } from 'pinia'

export type Data = {
  url?: string
  image?: string
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
  searchText?: string,
  page?: number,
  rows?: number
}
export type CommentData = {
  carId: number,
  comment: string
}
// export type DevicePostData = {
//   deviceId: number,
//   lastTestPerson?: string,
//   type?: number
// }
export const useRecordsStore = defineStore('records', () => {
  /** 获取新闻列表 */
  function getNewsDataAction(payload: PostData){
    return http.post('/car/selectNewsInfoList', payload);
  }
  /** 获取浏览记录列表 */
  function getHistoryDataAction(payload: PostData){
    return http.post('/car/selectHistoryInfoList', payload);
  }
  /** 获取爱好信息列表 */
  function getSavedDataAction(payload: PostData){
    return http.post('/car/selectSavedInfoList', payload);
  }
  /** 添加浏览记录 */
  function insertHistoryDataAction(payload: Data){
    return http.post('/car/addHistoryInfo', payload);
  }
  /** 添加 */
  function insertCommentDataAction(payload: CommentData){
    return http.post('/car/addCommentInfo', payload);
  }
  /** 添加爱好 */
  function insertSavedDataAction(payload: Data){
    return http.post('/car/addSavedInfo', payload);
  }

  return { 
    getNewsDataAction,
    getHistoryDataAction,
    getSavedDataAction,
    insertHistoryDataAction,
    insertCommentDataAction,
    insertSavedDataAction
  }
})

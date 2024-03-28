import http from '@/utils/http'
import { defineStore } from 'pinia'

export type Data = {
  deviceId?: number
  deviceName?: string
  usingTime?: string
  modelName?: string
  lengthImei?: string
  lengthStatus?: string
  widthImei?: string
  widthStatus?: string
  heightImei?: string
  heightStatus?: string
  testTime?: string
  testZero?: string
  factoryName?: string
  gprsId?: number
  iccid?: string
  [index: string]: unknown
}
export type PostData = {
  searchText: string,
  page: number,
  rows: number,
  startTime?: string,
  endTime?: string
}
// export type DevicePostData = {
//   deviceId: number,
//   lastTestPerson?: string,
//   type?: number
// }
export const useDevicesStore = defineStore('devices', () => {
  /** 获取测量设备列表 */
  function getTableDataAction(payload: PostData){
    return http.post('/device/selectDeviceList', payload);
  }
  /** 添加设备 */
  function insertDataAction(payload: Data){
    return http.post('/device/insertDeviceInfo', payload);
  }
  /** 获取设备上次测量信息 */
  function getLastMeasureAction(payload: Data){
    return http.post('/device/getDeviceLastMeasureInfo', payload);
  } 
  /**测试设备在线状态 */
  function detectAction(payload: Data){
    return http.('/device/detectDeviceStatus', payload);
  } 
  /**开始测试设备零点校准 */
  function detectZeroAction(payload: Data){
    return http.post('/device/detectDeviceZero', payload);
  } 
  /**获取测试设备零点校准值 */
  function getDeviceZeroAction(payload: Data){
    return http.post('/device/getDeviceZero', payload);
  } 
  /** 获取IMEI列表 */
  function getIMEITableDataAction(payload: PostData){
    return http.('/device/selectIMEIList', payload);
  }
  /** 删除IMEI */
  function deleteDataAction(payload: Data){
    return http.post('/device/delIMEIInfo', payload);
  }
  /** 添加IMEI */
  function insertIMEIDataAction(payload: Data){
    return http.post('/device/insertIMEIInfo', payload);
  }
  /** 修改IMEI */
  function updateIMEIDataAction(payload: Data){
    return http.post('/device/modifyIMEIInfo', payload);
  }
  /** 获取SIM卡信息 */
  function getIMEIDetailAction(payload: Data){
    return http.post('/device/selectICCIDInfo', payload);
  }
  return { 
    getTableDataAction,
    insertDataAction,
    getLastMeasureAction,
    detectAction,
    detectZeroAction,
    getDeviceZeroAction,
    getIMEITableDataAction,
    deleteDataAction,
    insertIMEIDataAction,
    updateIMEIDataAction,
    getIMEIDetailAction    
  }
})

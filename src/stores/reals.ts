import http from '@/utils/http'
import { defineStore } from 'pinia'
import { ref } from 'vue'

// type timestampLength = number
// type timestampWidth = number
// type timestampHeight = number

export type Data = {
  factoryName?: string
  modelName?: string
  [index: string]: unknown
}

export const useRealsStore = defineStore('reals', () => {
  // const timestamp = ref<number>(0)
  /**获取所有设备厂家列表 */
  function getFactoryListAction(payload: Data){
    return http.post('/measure/selectDeviceFactoryList', payload);
  }
  function getModelListAction(payload: Data){
    return http.post('/measure/selectDeviceModelList', payload);
  }
  function getDeviceListAction(payload: Data){
    return http.post('/measure/selectDeviceList', payload);
  }
  function startMeasureAction(payload: Data){
    return http.post('/measure/startMeasure', payload);
  }
  function doMeasuringAction(payload: Data){
    return http.post('/measure/doMeasuring', payload);
  }
  function stopMeasureAction(payload: Data){
    return http.post('/measure/stopMeasure', payload);
  }
  function saveMeasureAction(payload: Data){
    return http.post('/measure/saveMeasure', payload);
  }
  function exportMeasureAction(payload: Data){
    return http.postE('/measure/exportMeasure', payload);
  }
  // function updateTimestamp(payload: number){
  //   timestamp.value = payload;
  // }
  return {
    // timestamp, 
    getFactoryListAction,
    getModelListAction,
    getDeviceListAction,
    startMeasureAction,
    doMeasuringAction,
    stopMeasureAction,
    saveMeasureAction,
    exportMeasureAction
    // updateTimestamp
  }
})

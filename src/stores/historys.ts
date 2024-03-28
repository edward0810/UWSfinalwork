import http from '@/utils/http'
import { defineStore } from 'pinia'

export type Data = {
  measureId: number
  measureTime: string
  company: string
  modelName: string
  factoryName: string
  standardLWH: string
  measureLWH: string
  conclusion: number
  testerName: string
  validDate: string
  [index: string]: unknown
}
export type PostData = {
  searchText: string,
  page: number,
  rows: number,
  startTime?: string,
  endTime?: string
}

type PostDataI = {
  measureId: number
}

export const useHistorysStore = defineStore('historys', () => {
  function getTableDataAction(payload: PostData){
    return http.post('/historyMeasure/selectHistoryMeasureList', payload);
  }
  function getDataDetailAction(payload: PostDataI){
    return http.post('/historyMeasure/selectHistoryMeasureDetailnfo', payload);
  }
  return { 
    getTableDataAction,
    getDataDetailAction
  }
})

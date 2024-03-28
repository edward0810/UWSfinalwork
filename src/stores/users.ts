import http from '@/utils/http'
import { ref } from 'vue'
import { defineStore } from 'pinia'
import type { RouteRecordRaw } from 'vue-router'

type SessionID = string
type PlatVersion = string
type Infos = {
  [index: string]: unknown
}
type Login = {
  userName: string
  password: string
}
export type Data = {
  userId?: number
  userName?: string
  password: string
  createTime?: string
  authStrArr?: string | []
  treeSelectVal?: string[]
  [index: string]: unknown
}
type ids = {
  userIds: string
}
export type PostData = {
  searchText: string,
  page: number,
  rows: number,
  startTime?: string,
  endTime?: string
}

export const useUsersStore = defineStore('users', () => {
  const sessionID = ref<SessionID>('')
  const platVersion = ref<PlatVersion>('')
  const infos = ref<Infos>({})
  const menuItemList = ref<RouteRecordRaw[] | undefined>([])
  function loginAction(payload: Login) {
    return http.post('/login/toLogin', payload)
  }
  function resetPwdAction(payload: Data) {
    return http.post('/login/resetPwd', payload)
  }
  function regUserAction(payload: Data) {
    return http.post('/login/regUser', payload)
  }
  function getTableDataAction(payload: PostData){
    return http.post('/configure/selectUserInfoList', payload);
  }
  function insertDataAction(payload: Data){
    return http.post('/configure/insertUserInfo', payload);
  }
  function updateDataAction(payload: Data){
    return http.post('/configure/updateUserInfo', payload);
  }
  function updatePwdAction(payload: Data){
    return http.post('/configure/updatePassword', payload);
  }
  function deleteDataAction(payload: ids){
    return http.post('/configure/deleteUserInfoByUserIds', payload);
  }
  function updateSessionID(payload: SessionID){
    sessionID.value = payload;
  }
  function updatePlatVersion(payload: PlatVersion){
    platVersion.value = payload;
  }
  function updateInfos(payload: Infos){
    infos.value = payload;
  }
  function clearSessionID(){
    sessionID.value = '';
  }
  function updateMenuItemList(payload: RouteRecordRaw[] | undefined){
    menuItemList.value = payload;
  }

  return { 
    sessionID, 
    platVersion, 
    infos, 
    menuItemList, 
    loginAction, 
    resetPwdAction, 
    regUserAction,
    getTableDataAction,
    insertDataAction,
    updateDataAction,
    updatePwdAction,
    deleteDataAction,
    updateSessionID,
    updatePlatVersion,
    updateInfos,
    clearSessionID,
    updateMenuItemList 
  }
}, {
  persist: {
    paths: ['sessionID','platVersion','infos']
  }
})

import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw, RouteRecordName } from 'vue-router'
import { useUsersStore } from '@/stores/users'
import { storeToRefs } from 'pinia'; 
import _ from 'lodash'
import { ref } from 'vue';

const Login = () => import('@/views/Login/Login.vue');
const Home = () => import('@/views/Home/Home.vue');
const Record = () => import('@/views/Record/Record.vue');
const Car = () => import('@/views/Car/Car.vue');
const CarShow = () => import('@/views/Car/CarShow.vue');
const News = () => import('@/views/News/News.vue');
const History = () => import('@/views/History/History.vue');
const Comment = () => import('@/views/Comment/Comment.vue');
const Saved = () => import('@/views/Saved/Saved.vue');
const Brand = () => import('@/views/Brand/Brand.vue');
const User = () => import('@/views/User/User.vue');
const NotAuth = () => import('@/views/NotAuth/NotAuth.vue');
const NotFound = () => import('@/views/NotFound/NotFound.vue');
const NotServer = () => import('@/views/NotServer/NotServer.vue');

declare module 'vue-router' {
  interface RouteMeta {
    menu?: boolean
    title?: string
    icon?: string 
    auth?: boolean
  }
}

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: Home,
    redirect: '/record',
    meta: {
      menu: true,
      title: 'Car',
      // icon: 'document-copy',
      auth: false
    },
    children: [
      {
        path: 'record',
        name: 'record',
        component: Record,
        meta: {
          menu: true,
          title: 'Homepage',
          // icon: 'calendar',
          auth: false
        }
      },
      {
        path: 'carShow',
        name: 'carShow',
        component: CarShow,
        meta: {
          menu: true,
          title: 'Car display',
          // icon: 'calendar',
          auth: false
        }
      },
      {
        path: 'history',
        name: 'history',
        component: History,
        meta: {
          menu: true,
          title: 'Browsing history',
          // icon: 'calendar',
          auth: false
        }
      },
      {
        path: 'saved',
        name: 'saved',
        component: Saved,
        meta: {
          menu: true,
          title: 'favourite',
          // icon: 'calendar',
          auth: false
        }
      },
      {
        path: 'brand',
        name: 'brand',
        component: Brand,
        meta: {
          menu: true,
          title: 'Brand management',
          // icon: 'calendar',
          auth: true
        }
      },
      {
        path: 'car',
        name: 'car',
        component: Car,
        meta: {
          menu: true,
          title: 'Automobile management',
          // icon: 'calendar',
          auth: true
        }
      },
      {
        path: 'user',
        name: 'user',
        component: User,
        meta: {
          menu: true,
          title: 'User management',
          // icon: 'calendar',
          auth: true
        }
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/403',
    name: 'notAuth',
    component: NotAuth
  },
  {
    path: '/404',
    name: 'notFound',
    component: NotFound
  },
  {
    path: '/500',
    name: 'notServer',
    component: NotServer
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404'
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.VITE_NODE_BASE_URL),
  routes
})

router.beforeEach((to, from, next)=>{
  const usersStore = useUsersStore()
  const { sessionID, infos } = storeToRefs(usersStore)
  if(sessionID.value && !_.isEmpty(infos.value)){
    if(to.path === '/login'){
      next('/');
    } else {
      interface Infos {
        [index: string]: unknown
      }
      const role = ref<Infos>({
        '1': 'record',
        '2': 'car',
        '3': 'brand',
        '4': 'user',
        '5': 'saved',
        '6': 'carShow',
        '7': 'history'
      })
      const permission: (RouteRecordName|undefined)[] = (infos.value.authStrArr as number[])?.map((item) => {
          return role.value[item.toString()] as string
      })
      const menus = _.cloneDeep(router.options.routes).filter((v)=> {
          v.children = v.children?.filter((v)=> v.meta?.menu && (permission as (RouteRecordName|undefined)[])?.includes(v.name));
          v.children = v.children?.reverse()
          return v.meta?.menu;
      }) 
      const menuItemList: RouteRecordRaw[] | undefined = menus[0].children
      console.log('==menuItemList==', menuItemList)
      usersStore.updateMenuItemList(menuItemList)

      if(to.meta.auth && to.path === '/user' && infos.value.isAdmin === 1){
        next();
      } else if (to.meta.auth){
        const p = menuItemList?.find(item => {
          return !_.isEmpty(item.path)
        })
        if(to.path === '/user'){
          next(`/${p?.path}`)
        } else {
          const has = menuItemList?.some((item) => {
            return item.path === to.path.split('/')[1]
          })
          if(has){
            next();
          } else {
            next(`/${p?.path}`)
          }
        }
      } else {
        next();
      }
    }
  } else {
    if(to.path === '/login'){
      next();
    } else {
      next('/login');
    }
  }
})

export default router

import { fileURLToPath, URL } from 'node:url'

import { defineConfig, loadEnv } from 'vite'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import vue from '@vitejs/plugin-vue'
import OptimizationPersist from 'vite-plugin-optimize-persist'
import PkgConfig from 'vite-plugin-package-config'
import legacy from '@vitejs/plugin-legacy'
 
// https://vitejs.dev/config/
export default defineConfig(({mode}) => {
  const env = loadEnv (mode, process.cwd ()); 
  return {
    plugins: [
      vue(),
      AutoImport({
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        resolvers: [ElementPlusResolver()],
      }),
      PkgConfig(),
      OptimizationPersist(),
      legacy({
        targets: ['defaults', 'ie >= 11', 'chrome 52'],  //需要兼容的目标列表，可以设置多个
        additionalLegacyPolyfills: ['regenerator-runtime/runtime'],
        renderLegacyChunks:true,
        polyfills:[
           'es.symbol',
           'es.array.filter',
           'es.promise',
           'es.promise.finally',
           'es/map',
           'es/set',
           'es.array.for-each',
           'es.object.define-properties',
           'es.object.define-property',
           'es.object.get-own-property-descriptor',
           'es.object.get-own-property-descriptors',
           'es.object.keys',
           'es.object.to-string',
           'web.dom-collections.for-each',
           'esnext.global-this',
           'esnext.string.match-all'
        ]
      })
    ],
    build: {
      target: 'es2015',
      assetsDir: "assets",
      sourcemap: false,
      // 配置打包文件路径和命名
      rollupOptions: {
        output: {
          // 超大静态资源拆分
          manualChunks(id){
            // console.log(id,'id')
            if(id.includes('node_modules')){
              return id.toString().split('node_modules/')[1].split('/')[0].toString()
            }
          },
          chunkFileNames: 'assets/js/[name]-[hash].js',
          entryFileNames: 'assets/js/[name]-[hash].js',
          // assetFileNames: 'assets/[ext]/[name]-[hash].[ext]',
        }
      },
      
      // 清除console和debugger
      minify: 'terser', // 使用terserOptions需要配置
      terserOptions: {
        compress: {
          drop_console: true,
          drop_debugger: true
        }
      }
    },
    // base: env.VITE_NODE_BASE_URL,
    base: "./",
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    },
    server: {
      proxy: {
        '/api': {
          // target: "http://112.124.17.70/NTLaserServer",
          target: env.VITE_NODE_PROXYURL,
          // ws:true,//代理websocked
          changeOrigin:true,//虚拟的站点需要更管origin
          // secure: false,                   //是否https接口
          rewrite: (path) => path.replace(/^\/api/,'')
          // pathRewrite:{
          //   '^/api':''//重写路径
          // }
          // headers: {
          //   referer: 'http://localhost:8081/ResourcesCenter/', //这里后端做了拒绝策略限制，请求头必须携带referer，否则无法访问后台
          // }
        }
      }
    }
  }
})

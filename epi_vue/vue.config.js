const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
})

module.exports={
  devServer:{
    proxy:{
      '/api':{
        target:'http://localhost:8086/',
        ws:true,
        changeOrigin:true,
        secure:false,
        pathRewrite:{
          '^/api':''
        }
      }
    }
  },
}
const webpack = require('webpack')

module.exports = {
    devServer: {
        proxy: {
            "/api": {
                target: "http://localhost:8081",
                secure: false,
                changeOrigin: true
            }
        },
        port: 8080,
    },
    lintOnSave: false,
    publicPath: process.env.VUE_BASE_URL || '/',
    productionSourceMap: false,
    configureWebpack: {

        module: {
            // Fix for flot resize
            rules: [{
                test: /jquery\.flot\.resize\.js$/,
                use: 'imports-loader?this=>window'
            }]
        },
        plugins: [
            new webpack.ProvidePlugin({
                $: 'jquery',
                jquery: 'jquery',
                'window.jQuery': 'jquery',
                jQuery: 'jquery',
                'window.Quill': ['quill'],
                'window.katex': ['katex'],
            })
        ]
    },
    transpileDependencies: [
        'resize-detector' // vue-echarts
    ]
}
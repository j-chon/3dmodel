var path = require("path")
var webpack = require("webpack")

module.exports = {
    entry: {
        // 名前を付けて複数のファイルをエントリー可能
        index: "./src/main/ts/logic/index.ts"
    },
    output: {
        path: path.resolve(__dirname, "./src/main/resources/templates/assets/js/"),
        publicPath: "/js/",
        filename: "[name].js" // エントリー時につけた名前ごとに出力する為、[name]変数を使用する
    },
    module: {
        rules: [
            {
                // ".vue"で終わるファイルあったら、vue-loaderで処理する。
                test: /\.vue$/,
                loader: "vue-loader",
                options: {
                    loaders: {
                        // Since sass-loader (weirdly) has SCSS as its default parse mode, we map
                        // the "scss" and "sass" values for the lang attribute to the right configs here.
                        // other preprocessors should work out of the box, no loader config like this necessary.
                        "scss": "vue-style-loader!css-loader!sass-loader",
                        "sass": "vue-style-loader!css-loader!sass-loader?indentedSyntax",
                    }
                    // other vue-loader options go here
                }
            },
            {
                // ".ts"で終わるファイルあったら、ts-loaderで処理する。
                test: /\.tsx?$/,
                loader: "ts-loader",
                exclude: /node_modules/,
                options: {
                    appendTsSuffixTo: [/\.vue$/],
                }
            },
            {
                test: /\.(png|jpg|gif|svg)$/,
                loader: "file-loader",
                options: {
                    name: "[name].[ext]?[hash]"
                }
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader'],
            },
        ]
    },
    resolve: {
        extensions: [".ts", ".js", ".vue", ".json"],
        alias: {
            // コンポーネント利用時に必要
            "vue$": "vue/dist/vue.esm.js"
        }
    },
    devServer: {
        historyApiFallback: true,
        noInfo: true,
        port : 80
    },
    performance: {
        hints: false
    },
    devtool: "#source-map" // "eval-source-map"
}

if (process.env.NODE_ENV === "production") {
    module.exports.devtool = "#source-map"
    // http://vue-loader.vuejs.org/en/workflow/production.html
    module.exports.plugins = (module.exports.plugins || []).concat([
        new webpack.DefinePlugin({
            "process.env": {
                NODE_ENV: "production"
            }
        }),
        new webpack.optimize.UglifyJsPlugin({
            sourceMap: true,
            compress: {
                warnings: false
            }
        }),
        new webpack.LoaderOptionsPlugin({
            minimize: true
        })
    ])
}

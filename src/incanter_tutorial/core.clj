(ns incanter-tutorial.core
  (:require [incanter.core :as incanter]
            [incanter.stats :as status]
            [incanter.charts :as charts]
            [incanter.datasets :as datasets]
            [incanter.io :as io]
            [incanter.pdf]
            [incanter.svg]
            [clojure.core.matrix :as matrix]))

;https://github.com/incanter/incanter

(-> (status/sample-normal 1000) ;標準正規分布から1,000個の値をサンプリング
    charts/histogram ;ヒストグラムを
    incanter/view) ;表示

(def my-plot (charts/function-plot incanter/sin -10 10)) ;-10から10の範囲にわたる正弦関数のプロット
(incanter/view my-plot) ;表示
#_(incanter/save my-plot "plot.png") ;pngで保存
#_(incanter.pdf/save-pdf my-plot "plot.pdf") 
#_(incanter.svg/save-svg my-plot "plot.svg")

(let [x (range 1 11)
      y [1 2 4 3 4 5 5 4 3 5]]
  (incanter/view
   (charts/xy-plot x y)))

(let [x (range 1 11)
      y [1 2 4 3 4 5 5 4 3 5]]
  (incanter/view
   (charts/line-chart x y)))

;2つのグラフ
(let [x (range 1 11)
      y1 [1 2 4 3 4 5 5 4 3 5]
      y2 [2 3 4 5 6 5 4 3 2 1]
      plot (charts/xy-plot x y1
                           :series-label "sl1"
                           :x-label "x"
                           :y-label "y"
                           :title "test-plot"
                           :legend true)]
  (charts/add-lines plot  x y2
                    :series-label "sl2")  
  (incanter/view plot))



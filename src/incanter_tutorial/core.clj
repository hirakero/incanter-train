(ns incanter-tutorial.core
  (:require [incanter.core :as incanter]
            [incanter.stats :as status]
            [incanter.charts :as charts]
            [incanter.io :as io]
            [incanter.pdf]
            [incanter.svg]
            [clojure.core.matrix :as matrix]))

;https://github.com/incanter/incanter

(-> (status/sample-normal 1000) ;標準正規分布から1,000個の値をサンプリング
    charts/histogram ;ヒストグラムを
    incanter/view) ;表示

(def my-plot (charts/function-plot incanter/sin -10 10 )) ;-10から10の範囲にわたる正弦関数のプロット
(incanter/view my-plot) ;表示
(incanter/save my-plot "plot.png") ;pngで保存
(incanter.pdf/save-pdf my-plot "plot.pdf")  
(incanter.svg/save-svg my-plot "plot.svg")  


(comment
  (charts/conj)
  (incanter/conj-rows)
  (status/sin 100)

  )

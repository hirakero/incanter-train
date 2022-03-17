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

; https://www.slideshare.net/adorepump/incanter-data-sorcery
;; creating datasets
(incanter/dataset ["x1" "x2" "x3"]
                  [[1 2 3]
                   [4 5 6]
                   [7 8 9]])

;| x1 | x2 | x3 |
;|----+----+----|
;|  1 |  2 |  3 |
;|  4 |  5 |  6 |
;|  7 |  8 |  9 |

(incanter/to-dataset [{"x1" 1, "x2" 2, "x3" 3}
                      {"x1" 4, "x2" 5, "x3" 6}
                      {"x1" 7, "x2" 8, "x3" 9}])
;同上

(incanter/to-dataset [[1 2 3]
                      [4 5 6]
                      [7 8 9]])
;| 0 | 1 | 2 |
;|---+---+---|
;| 1 | 2 | 3 |
;| 4 | 5 | 6 |
;| 7 | 8 | 9 |

(incanter/conj-cols [1 4 7] [2 5 8] [3 6 9])
;同上

;↓非推奨 clojure.core.matrix/conj-rows を使うようにあるが、そんな関数は無さそう？
;(incanter/conj-rows  [1 2 3] [4 5 6] [7 8 9])
;(matrix/conj-rows [1 2 3] [4 5 6] [7 8 9])

;; reading data
(io/read-dataset "cars.csv" 
                 :header true) ;1行目をヘッダーとして読み込む
;speed,dist
;4.0, 2.0
;4.0, 10.0
;7.0, 4.0
; ↓
;| :speed | :dist |
;|--------+-------|
;|    4.0 |   2.0 |
;|    4.0 |  10.0 |
;|    7.0 |   4.0 |

(io/read-dataset "cars.tdd"
                 :header true
                 :delim \tab)
;speed	dist
;4.0	2.0
;4.0	10.0
;7.0	4.0
; ↓
; 同上

(io/read-dataset "https://raw.githubusercontent.com/incanter/incanter/master/data/cars.csv"
                 :header true)

(incanter.datasets/get-dataset :cars) ;サンプルのデータセットを取得

;; saving dataA
(incanter/save (incanter/dataset ["x1" "x2" "x3"]
                           [[1 2 3]
                            [4 5 6]
                            [7 8 9]])
         "cars2.csv")
;x1,x2,x3
;1,2,3
;4,5,6
;7,8,9


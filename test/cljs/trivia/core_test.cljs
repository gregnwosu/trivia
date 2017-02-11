(ns trivia.core-test
  (:require [trivia.core :as sut]
            [clojure.test :as t]))
;; lein doo phantom test (runs tests)  ; need to install http://phantomjs.org/download.html
(t/deftest testing-clojurescript
  (t/is (= 9 (sut/addnum 4 5))))

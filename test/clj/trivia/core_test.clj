(ns trivia.core-test
  (:require [trivia.core :as sut]
            [clojure.test :as t]))

(t/deftest addition
  (t/is (= 7 (sut/add-number 3 4))))

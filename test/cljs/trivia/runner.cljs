(ns trivia.runner
  (:require  [clojure.test :as t]
             [doo.runner :refer-macros [doo-tests]]
             [trivia.core-test]))
;; necessary to run lein do phantom test
(doo-tests 'trivia.core-test)

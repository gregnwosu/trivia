(ns trivia.subs
  (:require [re-frame.core :as re-frame]))

;; register a subscription for name as something that extracts the name from the db
(re-frame/reg-sub
 :name
 (fn [db _]
   (:name db)))
;; extract the name from the db for the subscription

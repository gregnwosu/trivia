(ns trivia.subs
  (:require [re-frame.core :as re-frame]))

;;registers a callback for whenever an reactive-atom changes

;; register a subscription for name as something that extracts the name from the db
(re-frame/reg-sub
 :name
 (fn [db _]
   (:name db)))
;; extract the name from the db for the subscription


(re-frame/reg-sub
 :active-page
 (fn [db _] (:active-page db)))


(re-frame/reg-sub
 :current-question
 (fn [db _]
   (:current-question db)))

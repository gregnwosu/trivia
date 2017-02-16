(ns trivia.events
  (:require [re-frame.core :as re-frame ]
            [trivia.db :as db]))
;; see https://github.com/Day8/re-frame/blob/master/docs/EventHandlingInfographic.md
(re-frame/reg-event-fx
 :login
 (fn [db [event data]]
   (prn "db is " db)
   ;; note need to create new map i dont think it works to just update existing
   (let [
         new-map  {:db  (assoc (:db db) :name data) :dispatch [:login-success]}]
     new-map
     )))



(re-frame/reg-event-db
 :name
 (fn [db [event data]]
   (assoc db :name data)))

(re-frame/reg-event-db
 :login-success
 (fn [db]
   (assoc db :active-page :create-game)
   ))

(re-frame/reg-event-db
 :initialise-db
 []
 (fn [db]
   (console.log "Initialising" db)
   db/default-value))

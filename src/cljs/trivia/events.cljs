(ns trivia.events
  (:require [re-frame.core :as re-frame ]
            [trivia.db :as db]))
;; see https://github.com/Day8/re-frame/blob/master/docs/EventHandlingInfographic.md


(re-frame/reg-event-fx
 :login
 (fn [cofx [_ data]]
   (console.log (str  " login event handled" cofx))
   {:db  (assoc (:db cofx) :name data)
    :dispatch [:login-success]}
   ))

(re-frame/reg-event-fx
 :login-success
 (fn [cofx [_ _] ]
   (console.log (str  "login success called\n" cofx "\n"    {:db (assoc (:db cofx) :active-page :create-game) } ))
   {:db (assoc (:db cofx) :active-page :create-game) }
   ))

(re-frame/reg-event-db
 :name
 (fn [db [event data]]
   (assoc db :name data)))


(re-frame/reg-event-db
 :initialise-db
 []
 (fn [db]
   (console.log "Initialising" db)
   db/default-value))

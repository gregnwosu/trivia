(ns trivia.events
  (:require [re-frame.core :as re-frame ]
            [trivia.db :as db]))

(re-frame/reg-event-fx
 :create-game
 ;; (co`nsole.log "create game event handled")
 (fn [db]
   {:db (assoc (:db db)  :current-question {:question "How cool is clojurescript?"
                                            :answers [{:answer "Meh" :correct false}
                                                      {:answer "Its OK" :correct false}
                                                      {:answer "AWESOME" :correct true}
                                                      {:answer "Rubbish" :correct false}]})
    :dispatch [:active-page :ask-question]}))

(re-frame/reg-event-db
 :active-page
 (fn [db [event data]]
   (assoc db :active-page data)))


(re-frame/reg-event-fx
 :login
 (fn [cofx [event data]]
   ;; nb writableiting to console fux fx event handling
   ;;(console.log (str  "handling login event" cofx)  )
   {:db (assoc cofx :name data)
    :dispatch [:login-success]}
   ))

(re-frame/reg-event-db
 :login-success
 (fn [db]
   (console.log (str  "handling login-success event" db))
   ;; no need to assoc in db, will change the cofx that has the db
   (assoc db :active-page :create-game)))

;; see https://github.com/Day8/re-frame/blob/master/docs/EventHandlingInfographic.md

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

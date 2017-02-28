(ns trivia.events
  (:require [re-frame.core :as re-frame ]
            [trivia.db :as db]))
;; see https://github.com/Day8/re-frame/blob/master/docs/EventHandlingInfographic.md
(re-frame/reg-event-fx
 :create-game
 (fn [db]
   {:db (assoc db
               :current-question {:question "How cool is clojurescript?"
                                  :answers [{:answer "Meh" :correct false}
                                            {:answer "Its OK" :correct false}
                                            {:answer "AWESOME" :correct true}
                                            {:answer "Rubbish" :correct false}]}
               :dispatch [:active-page :ask-question])}
   )
 )

(re-frame/reg-event-db
 :active-page
 (fn [db [event data]]
   (assoc db :active-page data)))


(re-frame/reg-event-fx
 :login
 (fn [cofx [_ data]]

   {:db  (assoc (:db cofx) :name data)
    :dispatch [:login-success]}
   ))

(re-frame/reg-event-fx
 :login-success
 (fn [cofx [_ _] ]
   ;; no need to assoc in db, will change the cofx that has the db
   {:dispatch [:active-page :create-game]}))


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

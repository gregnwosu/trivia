(ns trivia.events
  (:require [re-frame.core :as re-frame ]
            [trivia.db :as db]))
;; see https://github.com/Day8/re-frame/blob/master/docs/EventHandlingInfographic.md
(re-frame/reg-event-db
 :login
 (fn [db [event data]]
   (console.log "Event: " event  " data  " data " db " db)))

(re-frame/reg-event-db
 :name
 (fn [db [event data]]
   (console.log "Event: " event  " data  " data " db " db)
   (assoc db :name data)))


(re-frame/reg-event-fx
 :initialise-db
 []
 (fn [db]
   (console.log "Initialising")
   db/default-value))

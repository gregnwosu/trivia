(ns trivia.core
  (:require [re-frame.core :as re-frame]
            [reagent.core :as reagent]
            [trivia.events :as events]
            [trivia.subs :as subs]
            [trivia.views :as views]))

;;enable use of prn
(enable-console-print!)

;; Note we need to INCLUDE all of our sub modules in core
;; for hacking javascript we have http://cljsjs.github.io/
;; basic set up from https://www.youtube.com/watch?v=9sVGy0IovH8
;;tutorial
;; https://github.com/fasiha/fullstack-cljs-tutorial

;;for server side use cljs-ajax
;; use https://github.com/JulianBirch/cljs-ajax/blob/master/docs/server.md
;; or a python restful interface

;; for clientside request use cljs-ajax
;; https://github.com/JulianBirch/cljs-ajax

;; for websites we should use aws elastic beanstalk and probably ought to learn refreame at some point

;; styles are done using
;; http://getbootstrap.com/
;; unzip and put in the resources folder, you can see css classes to use
;; if you use developer tools and browse http://getbootstrap.com/examples/jumbotron/
;; add a class to a hiccup component via
;; e.g.  <div class=jumbotron/> =  [:div.jumbotron ]
;; https://github.com/Day8/re-frame/tree/master/examples/todomvc

;; HTML from bootsnip.com, in project 2 he seems to get it from http://www.initializr.com/
(def click-count (reagent/atom 0))

(defn counting-component []
  [:div.jumbotron
   "The atom " [:code "click-count"] " has value: "
   @click-count ". "
   [:input.btn-primary {:type "button" :value "Click me Boss!"
                        :on-click #(swap! click-count inc)}]])

(defn mount-root []
  (reagent/render [views/main-page]
                  ;;[:h1 "Hello"]
                  (.getElementById js/document "app")))

(defn ^:export init []
  ;; manually fire an event
  (re-frame/dispatch-sync [:initialise-db])
  (mount-root))


;; stupid function to test

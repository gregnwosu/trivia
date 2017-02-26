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

;; use clojure multimethods to override pages
;; from https://github.com/Day8/re-frame/blob/master/docs/Navigation.md

;; setup multimethods, override function called 'pages'
(defmulti pages identity)
;; define your pages         views/login-panel from views.cljs
(defmethod pages :login []
  [(views/login-panel)])
(defmethod pages :create-game []
  (console.log "Rendering create-game")


  [:div
   [:form.form-horizontal
    [:fieldset
     [:comment " Form Name "]
     [:legend "Form Name"]
     [:comment " Appended checkbox "]

     [:div.form-group
      [:label.col-md-4.control-label {:for "Greg"} "Appended Checkbox"]
      [:div.col-md-4
       [:div.input-group
        [:input#Greg.form-control {:name "Greg" :type "text" :placeholder "placeholder"}]
        [:span.input-group-addon [:input {:type "checkbox"}]]]
       [:p.help-block "help"]]]

     [:comment " Button "]
     [:div.form-group
      [:label.col-md-4.control-label {:for "singlebutton"} "Single Button"]
      [:div.col-md-4
       [:button#singlebutton.btn.btn-primary {:name "singlebutton"} "Button"]]]]]]

  )


;; so now the function show-page takes a page name and dispatches on the multimethod pages above
(defn show-page [page-name]
  (prn "Show page" page-name)
  (pages page-name))

(defn main-page []
  (let [active-page  (re-frame/subscribe [:active-page])]
    (fn [] [:div
           (show-page @active-page)])))
;;; end multimethod technique for page dispatch

(defn mount-root []
  (reagent/render [main-page]
                  ;;[:h1 "Hello"]
                  (.getElementById js/document "app")))

(defn ^:export init []
  ;; manually fire an event
  (re-frame/dispatch-sync [:initialise-db])
  (mount-root))


;; stupid function to test

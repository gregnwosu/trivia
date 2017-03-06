(ns trivia.views
  (:require [re-frame.core :as re-frame :refer [dispatch subscribe]]
            [reagent.core :as reagent]))

;; http://bootsnipp.com/
;; boiler plate from http://www.initializr.com/
;; configure and download

;; "C-c h h" . html-to-hiccup-convert-region
;; also note https://github.com/madvas/jsx-to-clojurescript
(defn login-panel []
  ;; aha subscribe to name from the db, this will track the name always (subscribe [:name]) , but it is readonly so instead well create a writable atom
  (let [name  (reagent/atom "")]
    (fn []
      [:div.container [:div.row [:div.main [:h3 "Please Log In, or " [:a {:href "#"} "Sign Up"]] [:div.row [:div.col-xs-6.col-sm-6.col-md-6 [:a.btn.btn-lg.btn-primary.btn-block {:href "#"} "Facebook"]] [:div.col-xs-6.col-sm-6.col-md-6 [:a.btn.btn-lg.btn-info.btn-block {:href "#"} "Google"]]] [:div.login-or [:hr.hr-or] [:span.span-or "or"]] [:form {:role "form"} [:div.form-group [:label {:for "inputUsernameEmail"} "Username or email"] [:input#inputUsernameEmail.form-control {:type "text"  :on-change #(reset! name (-> % .-target .-value))}]] [:div.form-group [:a.pull-right {:href "#"} "Forgot password?"] [:label {:for "inputPassword"} "Password"] [:input#inputPassword.form-control {:type "password"}]] [:div.checkbox.pull-right [:label [:input {:type "checkbox"}]"
  Remember me "]] [:button.btn.btn.btn-primary {:type "button" :on-click #(dispatch [:login @name])}
                   "Log In"]]]]])))


(defn navbar
  ""
  []
  (let [name (re-frame/subscribe [:name])]
    [:div.container [:comment " Static navbar "] [:nav.navbar.navbar-default [:div.container-fluid [:div.navbar-header [:button.navbar-toggle.collapsed {:type "button" :data-toggle "collapse" :data-target "#navbar" :aria-expanded "false" :aria-controls "navbar"} [:span.sr-only "Toggle navigation"] [:span.icon-bar] [:span.icon-bar] [:span.icon-bar]] [:a.navbar-brand {:href "#"} @name]] [:div#navbar.navbar-collapse.collapse [:ul.nav.navbar-nav [:li.active [:a {:href "#"} "Home"]] [:li [:a {:href "#"} "About"]] [:li [:a {:href "#"} "Contact"]] [:li.dropdown [:a.dropdown-toggle {:href "#" :data-toggle "dropdown" :role "button" :aria-haspopup "true" :aria-expanded "false"} "Dropdown " [:span.caret]] [:ul.dropdown-menu [:li [:a {:href "#"} "Action"]] [:li [:a {:href "#"} "Another action"]] [:li [:a {:href "#"} "Something else here"]] [:li.divider {:role "separator"}] [:li.dropdown-header "Nav header"] [:li [:a {:href "#"} "Separated link"]] [:li [:a {:href "#"} "One more separated link"]]]]] [:ul.nav.navbar-nav.navbar-right [:li.active [:a {:href "./"} "Default " [:span.sr-only "(current)"]]] [:li [:a {:href "../navbar-static-top/"} "Static top"]] [:li [:a {:href "../navbar-fixed-top/"} "Fixed top"]]]] ] ]]))

(defn check-answer
  ""
  [answer]
  )



(defn create-answer
  ""
  [answer]
  ;;need a unique key for each component in the sequence - key is used to sort and identifu rows
  ^{:key (:answer answer)}
  [:a {:class "btn btn-lg btn-default btn-block", :href "#", :role "button" :on-click #(js/alert (:correct answer))} (:answer answer)])

(defn ask-question ""
  []
  ;; note we subscribe to an atom reframe  only does this once as the let is outside the lambda
  (let [question (re-frame/subscribe [:current-question])]
    (prn (str "some stuff ********************************    >> ************************************************" "boo"))
    (fn []
      [:div {:class "container"}
       [:div {:class "row"}
        [:div {:class "col-md-8 col-md-offset-2"}
         [:h1 "Just a question"]]]
       [:div {:class "row"}
        [:div {:class "col-md-8 col-md-offset-2"}
         [:div {:class "jumbotron"}
          [:div {:class "container text-center"}
           [:h2 (:question @question)
            ]]]]]
       [:div {:class "row"}
        [:div {:class "col-md-8 col-md-offset-2"}

         (map create-answer (:answers @question))


         [:a {:class "btn btn-lg btn-warning btn-block", :href "#", :role "button"} "A) Yes I do!"]
         [:a {:class "btn btn-lg btn-danger btn-block", :href "#", :role "button"} "A) Yes I do!"]
         ]]]))
  )


(defn create-game
  ""
  []
  (fn []
    [:div
     ;; this is a common component that we reuse , note that we can only use a form 1 componnent , ie direct hiccup as we cannot nest it again with a function via form2 hiccup commponent
     (navbar)
     [:div {:class "container"}
      [:div {:class "row"}
       [:div {:class "jumbotron"}
        [:div {:class "container"}
         [:h1 "Trivia Game!"]
         [:p "The most exciting trivia game"]
         [:p [:a
              {:class "btn btn-primary btn-lg", :href "#", :role "button", :on-click #(dispatch [:create-game])} "Create a new game >>"]]]
        ]]]]))

(defn create-menu
  ""
  []
  (let [mythis "that"]
    (fn []
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
           [:button#singlebutton.btn.btn-primary {:name "singlebutton"} "Button"]]]]]])))

;; use clojure multimethods to override pages
;; from https://github.com/Day8/re-frame/blob/master/docs/Navigation.md

;; setup multimethods, override function called 'pages'
(defmulti pages identity)
;; define your pages         views/login-panel from views.cljs
(defmethod pages :login []
  [(login-panel)])
(defmethod pages :create-game []
  [(create-game)])
(defmethod pages :ask-question [] [(ask-question)])



;; so now the function show-page takes a page name and dispatches on the multimethod pages above
(defn show-page [page-name]
  (prn "Show page" page-name)
  (pages page-name))

(defn main-page []
  (let [active-page  (re-frame/subscribe [:active-page])]
    (fn [] [:div
           (show-page @active-page)])))
;;; end multimethod technique for page dispatch

;; its the on-click dispatch that makes it fire an event,
;; name is subscribed using this lambda syntax

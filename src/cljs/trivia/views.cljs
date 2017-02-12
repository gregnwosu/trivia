(ns trivia.views
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch subscribe]]))
;; http://bootsnipp.com/
;; boiler plate from http://www.initializr.com/
;; configure and download

;; "C-c h h" . html-to-hiccup-convert-region
;; also note https://github.com/madvas/jsx-to-clojurescript
(defn login-panel []
  [:div.container [:div.row [:div.main [:h3 "Please Log In, or " [:a {:href "#"} "Sign Up"]] [:div.row [:div.col-xs-6.col-sm-6.col-md-6 [:a.btn.btn-lg.btn-primary.btn-block {:href "#"} "Facebook"]] [:div.col-xs-6.col-sm-6.col-md-6 [:a.btn.btn-lg.btn-info.btn-block {:href "#"} "Google"]]] [:div.login-or [:hr.hr-or] [:span.span-or "or"]] [:form {:role "form"} [:div.form-group [:label {:for "inputUsernameEmail"} "Username or email"] [:input#inputUsernameEmail.form-control {:type "text"}]] [:div.form-group [:a.pull-right {:href "#"} "Forgot password?"] [:label {:for "inputPassword"} "Password"] [:input#inputPassword.form-control {:type "password"}]] [:div.checkbox.pull-right [:label [:input {:type "checkbox"}] "
  Remember me "]] [:button.btn.btn.btn-primary {:type "submit"} "Log In"]]]]])
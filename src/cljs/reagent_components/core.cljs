(ns reagent-components.core
  (:import goog.history.Html5History
           goog.Uri)
  (:require [reagent.core :as r :refer [atom]]
            [secretary.core :as secretary :include-macros true]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [accountant.core :as accountant]
            [reagent-components.dropdown :refer [dropdown]]
            [reagent-components.app-state :refer [app-state]]))

;; -------------------------
;; Pages

(defn home-page []
  [:div [:h2 "Welcome to reagent-components"]
   [:div [:a {:href "/about"} "go to about page"]]
   [:div [:a {:href "/dropdown"} "dropdown"]]])

(defn about-page []
  (def auth-state (r/cursor app-state [:auth]))
  (if (not @auth-state)
    (fn []
      ; Change path
      (accountant/navigate! "/")
      ; Navigate to there
      (accountant/dispatch-current!))
    (fn []
      [:div [:h2 "About reagent-components"]
       [:div [:a {:href "/"} "go to the home page"]]
       (println "well i was here")])))

[dropdown]

;; -------------------------
;; Routes

(defonce page (atom #'home-page))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
                    (reset! page #'home-page))

(secretary/defroute "/about" []
                    (reset! page #'about-page))

(secretary/defroute "/dropdown" []
                    (reset! page #'dropdown))

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (secretary/dispatch! path))
    :path-exists?
    (fn [path]
      (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))

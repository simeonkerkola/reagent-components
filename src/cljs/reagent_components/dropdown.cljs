(ns reagent-components.dropdown
  (:require [reagent.core :as r :refer [atom]]
            [reagent-components.app-state :refer [app-state]]))


;; --------
;; Dropdown

(def mod-list (r/cursor app-state [:mod-list]))
(def current-mod (r/cursor app-state [:current-mod]))

(defn dropdown []
  (defn- change-mod [evt]
    (reset! current-mod (-> evt .-target .-value))
    (println "current: " @current-mod))

  [:div
   [:select {:name "mod-list"
             :id "mod-list"
             :on-change change-mod}
    (for [item @mod-list]
      ^{:key (:id item)}
      [:option {:value (:name item)}
       (:name item) " (" (:id item) ")"])]])

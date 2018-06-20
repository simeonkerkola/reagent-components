(ns reagent-components.app-state
    (:require [reagent.core :as r :refer [atom]]))
;; -------------------------
;; State

(def app-state (r/atom {:mod-list [{:id "10"
                                    :name "työstö"}
                                   {:id 666
                                    :name "not-työstö"}]
                        :current-mod ""
                        :auth false}))

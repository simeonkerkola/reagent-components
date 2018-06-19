(ns reagent-components.prod
  (:require [reagent-components.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)

(ns ^:figwheel-no-load reagent-components.dev
  (:require
    [reagent-components.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)

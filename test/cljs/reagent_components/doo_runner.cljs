(ns reagent-components.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [reagent-components.core-test]))

(doo-tests 'reagent-components.core-test)

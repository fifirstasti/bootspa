(ns bootspa.core
  (:require-macros [bootspa.macro :refer [foobar]])
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponentk]]
            [sablono.core :as html :refer-macros [html]]
            [bootspa.hello :refer [foo-cljc]]
            [foo.bar]))

(js/foo)

(defonce app-state (atom {:y 2014}))

(defcomponentk main
  [[:data y :as cursor]]
  (render [_]
    (html
      [:div
       [:h1 (foo-cljc y)]
       [:div.btn-toolbar
        [:button.btn.btn-danger {:type "button" :on-click #(om/transact! cursor :y (partial + 5))} "+"]
        [:button.btn.btn-success {:type "button" :on-click #(om/transact! cursor :y dec)} "-"]]])))

(defn start! []
  (js/console.log "Starting the app")
  (om/root main app-state {:target (. js/document (getElementById "app"))}))

(start!)

; Macro test
(foobar :abc 3)

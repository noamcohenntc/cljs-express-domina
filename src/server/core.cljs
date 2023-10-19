(ns server.core
  (:require [cljs.nodejs :as node]))

(node/enable-util-print!)

(def express (node/require "express"))
(def app (express))
(defn -main []
  (doto app
    (.set "view engine" "pug")
    (.set "views" "build/views")
    (.use (.static express "build/public"))
    (.get "/" (fn [req res]
                (.render res "index" (clj->js {:title "Welcome!" :message "Hello World!"}))))
    (.listen 3000))
  (println "localhost:3000"))

(set! *main-cli-fn* -main)

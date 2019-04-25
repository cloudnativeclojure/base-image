(ns example.server
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.params :as params]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [reitit.ring.coercion :as coercion]
            [reitit.ring :as ring]
            [example.plain]))

(def app
  (ring/ring-handler
    (ring/router
      [example.plain/routes
       ]
      {:data {:muuntaja m/instance
      	      :middleware [params/wrap-params
                           muuntaja/format-middleware
                           coercion/coerce-exceptions-middleware
                           coercion/coerce-request-middleware
                           coercion/coerce-response-middleware]}})
    (ring/create-default-handler)))

(defn start []
  (jetty/run-jetty #'app {:port 3000, :join? false})
  (println "server running in port 3000"))

;; On ChromeOS, from browser:   http://penguin.linux.test:3000/plain/plus?x=10&y=20
;;              from terminal: (slurp "http://localhost:3000/plain/plus?x=10&y=20")

(comment
  (start))

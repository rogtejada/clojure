(ns bank-app.handler
  (:require [compojure.core :refer :all]
     [compojure.handler :as handler]
     [compojure.route :as route]
     [ring.middleware.json :as json]
     [ring.util.response :refer [response]]
     [bank-app.query :refer :all]))

(defroutes app-routes
  (GET "/account" []
    (response (get-account)))
  
  (GET "/account/:id" [id]
    (response (get-account-by-id (java.util.UUID/fromString id))))
  
  (POST "/account" {:keys [params]}
    (let [{:keys [name cpf]} params]
      (response (create-account name cpf))))
  
  (POST "/account/:source-id/transfer/:target-id/:amount" [source-id target-id amount]
    (response (transfer (java.util.UUID/fromString source-id) (java.util.UUID/fromString target-id) (Integer/parseInt amount))))
  
  (POST "/account/:id/deposit/:amount" [id amount]
    (response (deposit (java.util.UUID/fromString id) (Integer/parseInt amount))))
  
  (POST "/account/:id/withdraw/:amount" [id amount]
    (response (withdraw (java.util.UUID/fromString id) (Integer/parseInt amount))))


  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (json/wrap-json-params)
      (json/wrap-json-response)))
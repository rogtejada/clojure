(ns bank-app.handler
  (:require [compojure.core :refer :all]
     [compojure.handler :as handler]
     [compojure.route :as route]
     [ring.middleware.json :as json]
     [ring.util.response :refer [response]]
     [bank-app.query :refer :all]))

(defroutes app-routes
  (GET "/api/todos" []
       (response (get-todos)))
  
  (GET "/api/todos/:id" [id]
       (response (get-todo (Integer/parseInt id))))
  
  (POST "/api/todos" {:keys [params]}
    (let [{:keys [title description]} params]
      (response (add-todo title description))))
  
  (PUT "/api/todos/:id" [id title description]
       (response (update-todo (Integer/parseInt id) title description)))
  
  (DELETE "/api/todos/:id" [id]
    (response (delete-todo (Integer/parseInt id))))
  
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (json/wrap-json-params)
      (json/wrap-json-response)))
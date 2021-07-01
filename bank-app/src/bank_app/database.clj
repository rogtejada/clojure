(ns bank-app.database
  (:require [korma.db :as korma]))

(def db-connection-info
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :user "postgres"
   :password "password"
   :subname "//localhost:5432/postgres"})

; set up kormaWWW
(korma/defdb db db-connection-info)
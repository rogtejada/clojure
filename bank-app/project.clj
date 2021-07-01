(defproject bank-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-json "0.3.1"]
                 [korma "0.3.0-RC5"]
                 [org.postgresql/postgresql "42.1.4"]
                 [ring/ring-defaults "0.2.1"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler bank-app.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
(ns helloworld.core)

(defn lastElement [x] (x (dec (count x))))


(defn -main
  []
  (println (lastElement [1 2 3])))


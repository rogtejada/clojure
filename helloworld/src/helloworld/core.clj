(ns helloworld.core)

(defn lastElement [x] (x (dec (count x))))

(defn someArray [x] (range 0 (dec(count x))))

(defn lala [x] (->> (range 0 (dec(count x)))
                    #_=> (map inc)))

(defn -main
  []
  (println (lala [1 2 3])))


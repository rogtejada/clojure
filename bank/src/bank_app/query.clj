(ns bank-app.query)


;;create an empty map
;;atom is used to have a mutable structure
(def accounts
  (atom '()))

(defn parse-atom [atom] @atom)

(defn parse-atom-list [atom-list] (->> (for [x  atom-list
                                             :let [parsed (parse-atom x)]] parsed)))


(defn get-account [] (parse-atom-list @accounts))

(defn get-account-by-id [id]
  (first (filter #(= id  (% :id)) (parse-atom-list @accounts))))


(defn create-account [name cpf]
  (let [id (java.util.UUID/randomUUID)]  (swap! accounts conj (atom {:id id
                                                                     :name    name
                                                                     :cpf     cpf
                                                                     :balance 0}))
       (get-account-by-id id)))


(defn validate-transfer [balance amount] (<= amount balance))

(defn validateWithdraw [id amount]
  (if (validate-transfer (:balance (get-account-by-id id)) amount) true false))

(defn get-atom-by-id [id]  (first (filter (fn [x] (= id  (@x :id))) @accounts)))

(defn add-amount [id amount]
  (let [acc  (get-atom-by-id id)]
  (swap! acc assoc :balance (+ (:balance (parse-atom acc)) amount))))


(defn transfer [source-id target-id amount]
  (if (validateWithdraw source-id amount)
    ((add-amount target-id amount)
     (add-amount source-id (* -1 amount)))
    (get-account-by-id source-id) ))

(defn withdraw [id amount] (if (validateWithdraw id amount)
                             (add-amount id (* -1 amount)) 
                             (get-account-by-id id)))

(defn deposit [id amount] (add-amount id amount))
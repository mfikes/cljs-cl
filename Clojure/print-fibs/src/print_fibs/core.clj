(ns print-fibs.core
   (:gen-class :main true))

;; Define some Clojure functionality

(def fib-seq
    (lazy-cat [0 1] (map + (rest fib-seq) fib-seq)))

;; Execute the defined functionality

(defn -main [& args]
   (println (take 10 fib-seq)))

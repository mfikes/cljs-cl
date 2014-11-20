(ns print-fibs.core)

;; Arrange things so that we can print to stdout

(defn print-java [s]
   (js/java.lang.System.out.print s))

(set! cljs.core/*print-fn* print-java)

;; Define some ClojureScript functionality

(def fib-seq
    (lazy-cat [0 1] (map + (rest fib-seq) fib-seq)))

;; Execute the defined functionality when script loaded

(println (take 10 fib-seq))

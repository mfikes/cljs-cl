cljs-cl
=======

Experiment with ClojureScript command-line app (compiled to Java byte code).

One problem with command-line apps written in Clojure is the startup latency. 

[Sam Beran](https://github.com/sberan) recently mentioned success with a proof-of-concept which reduces startup latency in an Android app where the approach is to use ClojureScript which is then compiled to Java bytecode.

I was curious as to whether this same approach could be used to produce Clojure command line apps. This repo simply compares the Clojure and ClojureScript/bytecode approach to an app which prints the first 10 Fibonacci numbers, where the command-line app essentially consists of:

```clojure
(def fib-seq
    (lazy-cat [0 1] (map + (rest fib-seq) fib-seq)))

(println (take 10 fib-seq))
```

Running
=======

To run the ClojureScript code, first download Rhino so that you have a copy of `js.jar` available.

1. `cd` into the ClojureScript directory.
2. Compile the ClojureScript to JavaScript with `lein cljsbuild once rel`.
3. Compile the JavaScript to Java bytecode with `java -cp /path/to/js.jar org.mozilla.javascript.tools.jsc.Main js/main.js`.
4. Run the resulting bytecode with `java -cp /path/to/js.jar:js main`
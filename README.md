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

To run the ClojureScript code, first download Rhino so that you have a copy of `js.jar` available. Then:

1. `cd` into the `ClojureScript/print-fibs` directory of this repo.
2. Compile the ClojureScript to JavaScript with `lein cljsbuild once rel`.
3. Compile the JavaScript to Java bytecode with `java -cp /path/to/js.jar org.mozilla.javascript.tools.jsc.Main js/main.js`.
4. Run the resulting bytecode with `java -cp /path/to/js.jar:js main`


To run the Clojure code:

1. `cd` into the `Clojure/print-fibs` directory of this repo.
2. Create a JAR using `lein uberjar` 
3. Run the JAR using `java -jar target/print-fibs-0.1.0-SNAPSHOT-standalone.jar`.

Both of the above should produce the output 
```
(0 1 1 2 3 5 8 13 21 34)
```

I timed both, using `time` on a Mac, and the ClojureScript version is a bit faster for me, running in 0.348 seconds, while the Clojure version takes 1.132 seconds.

As a comparison, I tried the same using Nashorn's `jrunscript js/main.js` and while it produces the same result, it takes 1.363 seconds.


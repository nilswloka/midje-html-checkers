(ns midje-html-checkers.core
  (:use [midje.checkers.defining :only [defchecker checker]])
  (:use [midje.checkers.extended-equality :only [extended-=]])
  (:use [net.cgrand.enlive-html :only [select text html-resource text-pred]])
  (:import java.io.StringReader))

(defn html-resource-from-string
  "Creates a html-resource for use with Enlive from a string"
  [string]
  (-> string StringReader. html-resource))

(defchecker generic-html-checker
  "Creates a generic html checker with the following components:
  selector must be a valid Enlive selector and will be used to extract a
  sequence of nodes from the html the checker is used with.

  The result of applying node-predicate to each node in that sequence
  will be passed to evaluator.

  For an usage example see definitions of at-any or at-every"
  [selector node-predicate evaluator]
  (checker
   [actual]
   (let [html (html-resource-from-string actual)
         nodes (select html selector)]
     (evaluator (map node-predicate nodes)))))

(defchecker at-any [selector node-predicate]
  "Creates a checker that evaluates to true if node-predicate evaluates
  to true for any node that matches selector.

  Checkers created with at-any evaluate to false if no nodes match selector."
  (generic-html-checker selector node-predicate
                        #(if (empty? %) false (some identity %))))

(defchecker at-every [selector node-predicate]
  "Creates a checker that evaluates to true if node-predicate evaluates
  to true for all nodes that match selector.

  Checkers created with at-all evaluate to false if no nodes match selector."  
  (generic-html-checker selector node-predicate
                        #(if (empty? %) false (every? identity %))))

(defchecker number-of [selector expected]
  "Creates a checker that evaluates to true if the number of nodes
   matched by selector matches `expected`. `expected` can be a number
   or predicate function."
  (generic-html-checker selector identity
                        #(extended-= (count %) expected)))

(defn has-attr [attr v]
  "Creates a node predicate that evaluates to true for nodes that have an
  attribute attr with a value of v."
  (fn [node]
    (extended-= (-> node :attrs attr) v)))

(defn has-text [t]
  "Creates a node predicate that evaluates to true for nodes that have a text
  of t."
  (fn [node]
    (extended-= (text node) t)))

(defchecker contains-text
  "Returns true if actual html contains expected text"
  [expected]
  (at-every [(text-pred #(.contains % expected))] identity))

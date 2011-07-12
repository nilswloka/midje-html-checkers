(ns midje-html-checkers.core
  (:use midje.checkers.defining)
  (:use net.cgrand.enlive-html)
  (:import java.io.StringReader))

(defn html-resource-from-string [string]
  (-> string StringReader. html-resource))

(defchecker contains-text
  "Returns true if actual html contains expected text"
  [expected]
  (checker
   [actual]
   (let [html (html-resource-from-string actual)]
     (seq (select html [(text-pred #(.contains % expected))])))))

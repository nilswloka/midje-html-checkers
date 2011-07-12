(ns midje-html-checkers.core
  (:use midje.checkers.defining)
  (:use net.cgrand.enlive-html))

(defchecker contains-text [html])

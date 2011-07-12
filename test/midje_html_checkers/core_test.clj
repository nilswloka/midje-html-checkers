(ns midje-html-checkers.core-test
  (:use midje-html-checkers.core)
  (:use midje.sweet
        [midje.checkers.defining :only [checker?]]))

(facts "about contains-text"
       #'contains-text => checker?)

(ns midje-html-checkers.core-test
  (:use midje-html-checkers.core)
  (:use midje.sweet
        [midje.checkers.defining :only [checker?]]))

(facts "about contains-text"
       #'contains-text => checker?
       "<p>Hello World!</p>" => (contains-text "World")
       "<a href=\"Hello World\">Whatever</a>" =not=> (contains-text "World"))

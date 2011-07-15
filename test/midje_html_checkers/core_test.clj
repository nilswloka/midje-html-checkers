(ns midje-html-checkers.core-test
  (:use midje-html-checkers.core)
  (:use midje.sweet
        [midje.checkers.defining :only [checker?]]))

(facts "about contains-text"
       #'contains-text => checker?
       "<p>Hello World!</p>" => (contains-text "World")
       "<a href=\"Hello World\">Whatever</a>" =not=> (contains-text "World"))

(facts "about at-every"
       #'at-every => checker?
       "<ul><li class='nav'>Item 1</li><li class='nav'>Item 2</li></ul>" => (at-every [:li] (has-attr :class "nav"))
       "<div class='article'>Some article</div><div class='note'>Some note</div>" =not=> [:div] (has-attr :class "article"))

;.;. FAIL at (NO_SOURCE_FILE:1)
;.;. Actual result did not agree with the checking function.
;.;.         Actual result: "<div class='article'>Some article</div><div class='note'>Some note</div>"
;.;.     Checking function: (at-any [:div] (has-attr :class #"art.*"))
(facts "about at-any"
       #'at-any => checker?
       "<div class='article'>Some article</div><div class='note'>Some note</div>" => (at-any [:div] (has-attr :class "article"))
       "<div class='article'>Some article</div><div class='note'>Some note</div>" => (at-any [:div] (has-attr :class #"art.*"))       
       "<div class='note'>A note</div><div class='note'>Another note</div>" =not=> (at-any [:div] (has-attr :class "article")))

(facts "about number-of"
       #'number-of => checker?
       "<ul><li>1</li><li>2</li><li>3</li></ul>" => (number-of [:li] 3)
       "<ul><li>1</li><li>2</li></ul>" =not=> (number-of [:li] #(> % 2)))

About midje-html-checkers
=========================
This is a set of checkers for the [Midje test
framework](https://github.com/marick/Midje) for stating facts about
HTML code. The checkers' implementation is based on the [Enlive
templating library](https://github.com/cgrand/enlive).

You can use midje-html-checkers by adding the folloing dependency to
your project.clj:

    [midje-html-checkers "1.0.0"]

At the moment, midje-html-checkers provides the generic checkers `at-any`,
`at-every` and `number-of`, which can be used in conjunction with node predicates:

    (use 'midje-html-checkers.core)
    (facts
        "<p>Hello World!</p><p>Nothing here<p/>" => (at-any [:p] (has-text #"Nothing.*"))
        "<p class='a'>A</p><p class='b'>B</p>" =not=> (at-every [:p] (has-attr :class "a"))
        "<ul><li>1</li><li>2</li></ul>" => (number-of [:li] #(= % 2))))

You can build your own checkers with `generic-html-checker`. Have a
look at the predifined checkers' source for details.

Also feel free to suggest checkers you would find useful.

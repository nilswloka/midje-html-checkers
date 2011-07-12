About midje-html-checkers
=========================
This is a set of checkers for the [Midje test
framework](https://github.com/marick/Midje) for stating facts about
HTML code. The checkers' implementation is based on the [Enlive
templating library](https://github.com/cgrand/enlive).

You can use midje-html-checkers by adding the folloing dependency to
your project.clj:

    [midge-html-checkers "1.0.0-SNAPSHOT"]

So far, there is only one checker, which can be used like this:

    (use 'midje-html-checkers.core)
    (facts
        "<p>Hello World!</p>" => (contains-text "Hello")
        "<a href='Hello World!'>Some link text</a>" =not=> (contains-text "Hello")))

Feel free to suggest checkers you would find useful.

# Bank kata initial run

There are a couple of issues with this solution, although it solves the initial problem. First of all, the visitor and the kata package have cycles. Well, that would be fixed in no time. Now to what takes a bit of thinking:

1. Why is there a visitor at all? It's nice, but we should have improved the other code first.
2. The `Account` class has too much responsibilities.
3. The transaction sheet should be a class on its own.
4. The `console` is a `PrintStream`, what if we want to handle the output differently?

Think about it and you may come up with a better solution.
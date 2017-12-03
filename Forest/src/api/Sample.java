package api;

public class Sample {
	public static Forest<String> sampleForrest() {
		Forest<String> forrest = new Forest<>();
		StringNode a = new StringNode("a");

		StringNode b = new StringNode("b");
		b.add(new StringNode("d"));
		b.add(new StringNode("e"));
		b.add(new StringNode("f"));

		a.add(b);

		StringNode c = new StringNode("c");
		c.add(new StringNode("g"));
		c.add(new StringNode("h"));

		a.add(c);

		StringNode i = new StringNode("i");
		i.add(new StringNode("j"));

		forrest.add(a);
		forrest.add(i);

		return forrest;
	}
}

package api;

public class ToTreeStringVisitor<T> implements Visitor<T> {
	private StringBuilder string;

	public ToTreeStringVisitor() {
		clear();
	}

	public ToTreeStringVisitor<T> clear() {
		string = new StringBuilder();
		return this;
	}

	@Override
	public void visit(Tree<T> node) {
		for (int i = 1; i < node.depth(); ++i) {
			string.append("  ");
		}
		string.append(node.get().toString()).append('\n');
	}

	@Override
	public String toString() {
		return string.toString();
	}
}

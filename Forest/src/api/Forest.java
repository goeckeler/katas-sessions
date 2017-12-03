package api;

import java.util.List;

public class Forest<T> extends Node<T> {
	@Override
	public final void visit(Visitor<T> visitor) {
		children().forEach(node -> node.visit(visitor));
	}

	/**
	 *
	 * @return
	 */
	public final List<Tree<T>> getRoots() {
		return children();
	}
}

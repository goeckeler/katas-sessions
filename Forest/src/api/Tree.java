package api;

public class Tree<T> extends Node<T> {
	private T object;

	public T get() {
		return object;
	}

	void set(T object) {
		this.object = object;
	}

	@Override
	public final void visit(Visitor<T> visitor) {
		visitor.visit(this);
		children().forEach(n -> n.visit(visitor));
	}
}

package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Node<T> {
	private List<Tree<T>> children = new ArrayList<>();
    private Node<T> parent;
    
	public final boolean add(Tree<T> child) {
		child.setParent(this);
		return children.add(child);
	}

	public final int depth() {
	  if (parent == null) return 0;
	  return 1 + parent.depth();
	}
	
	public final List<Tree<T>> children() {
		return children;
	}

	public final Optional<Node<T>> getParent() {
		return  Optional.ofNullable(parent);
	}
	
	public final void setParent(Node<T> parent) {
		this.parent = parent;
	}
	
	public abstract void visit(Visitor<T> visitor);
}

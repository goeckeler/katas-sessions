package api;

public class ToStringVisitor<T> implements Visitor<T> {
	private StringBuilder string;

	public ToStringVisitor() {
		clear();
	}

	public ToStringVisitor<T> clear() {
		string = new StringBuilder();
		return this;
	}

	@Override
	public void visit(Tree<T> node) {
      if (string.length() > 0) {
    	  string.append(", ");
      }
      string.append(node.get().toString());
	}
	
	@Override
	public String toString() {
		return string.toString();
	}
}

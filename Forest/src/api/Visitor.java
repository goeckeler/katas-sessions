package api;

public interface Visitor<C> {
  void visit(Tree<C> node);
}

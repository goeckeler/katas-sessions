package kata.visitor;

import java.util.List;

import kata.Statement;

public interface AccountVisitor {
  public void visit(List<Statement> statements);
}

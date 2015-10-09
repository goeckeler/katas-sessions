package kata.visitor;

import java.util.List;

import kata.Statement;

public class CreditDebitVisitor implements AccountVisitor {
    private int credit = 0;
    private int debit = 0;
    
	@Override
	public void visit(List<Statement> statements) {
		for (Statement statement : statements) {
			if (statement.amountOf() < 0) {
				debit -= statement.amountOf();
			} else {
				credit += statement.amountOf();
			}
		}
	}
	
	public String toString() {
		return String.format("Credit %1d.00, debit %2d.00.", credit, debit); 
	}
}

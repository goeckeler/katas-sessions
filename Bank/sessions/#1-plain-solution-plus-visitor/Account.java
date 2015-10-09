package kata;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kata.visitor.AccountVisitor;

public class Account {
	private PrintStream console;
	private Calendar calendar;

	private List<Statement> sheet = new ArrayList<>();
	private int balance = 0;

	public Account(PrintStream console, Calendar calendar) {
		this.console = console;
		this.calendar = calendar;
	}

	public void deposit(int amount) {
		balance += amount;
		sheet.add(0, new Statement(balance, amount, calendar.getCurrentDate()));
	}

	public void withdrawal(int amount) {
		balance -= amount;
		sheet.add(0, new Statement(balance, -amount, calendar.getCurrentDate()));
	}

	public void printStatement() {
		console.println("DATE | AMOUNT | BALANCE");
		for (Statement entry : sheet) {
			console.println(entry.toString());
		}
	}
	
	public void visit(AccountVisitor visitor) {
		visitor.visit(Collections.unmodifiableList(sheet));
	}
}

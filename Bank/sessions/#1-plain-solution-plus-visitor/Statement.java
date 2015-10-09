package kata;

public class Statement {
	private int amount;
	private int balance;
	private String date;

	public Statement(int balance, int amount, String date) {
		this.balance = balance;
		this.amount = amount;
		this.date = date;
	}

	public String toString() {
		return String.format("%1s | %2s.00 | %3s.00", date, amount, balance);
	}

	public int amountOf() {
		return amount;
	}

	public String at() {
		return date;
	}

	public int balanceOf() {
		return balance;
	}
}
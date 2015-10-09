package kata.visitor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kata.Account;
import kata.Calendar;

public class CreditDebitorTest {
	@Mock
	private PrintStream console;

	@Mock
	private Calendar calendar;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void sum_up_credits_and_debits() {
		Account account = new Account(console, calendar);

		account.deposit(1000);
		account.withdrawal(100);
		account.deposit(500);

		CreditDebitVisitor visitor = new CreditDebitVisitor();
		account.visit(visitor);
        
		assertThat(visitor.toString(), equalTo("Credit 1500.00, debit 100.00."));
	}
}

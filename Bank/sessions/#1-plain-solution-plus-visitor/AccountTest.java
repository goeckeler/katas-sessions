package kata;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AccountTest {
	@Mock
	private PrintStream console;

	@Mock
	private Calendar calendar;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void print_statement_containing_transactions_in_reverse_chronological_order() {
		Account account = new Account(console, calendar);

		when(calendar.getCurrentDate()).thenReturn("01/04/2014");
		account.deposit(1000);

		when(calendar.getCurrentDate()).thenReturn("02/04/2014");
		account.withdrawal(100);

		when(calendar.getCurrentDate()).thenReturn("10/04/2014");
		account.deposit(500);

		account.printStatement();

		verify(console).println("DATE | AMOUNT | BALANCE");
		verify(console).println("10/04/2014 | 500.00 | 1400.00");
		verify(console).println("02/04/2014 | -100.00 | 900.00");
		verify(console).println("01/04/2014 | 1000.00 | 1000.00");
	}
}

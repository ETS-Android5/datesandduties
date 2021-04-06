package datesandduties.mock;

import datesandduties.Accounts.AccountController;
import datesandduties.Accounts.Account;
import datesandduties.Accounts.AccountRepository;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Captor;

import org.mockito.Mockito.*;

import org.mockito.junit.*;

import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

// rough :(

public class AccountControllerTest {

	AccountController accountController = null;

	@Mock	
	AccountRepository accountRepository; //= Mockito.mock(AccountRepository.class); 

/*	public static void main(String[] args) {
		AccountControllerTester tester = new AccountControllerTester();
		tester.setUp();
		System.out.println(tester.testFunctions()?"pass":"fail");
	}
*/

	public static void main(String[] args) {
		System.out.println("\n\n\nwe got this \n\n\n");
	}

	public void setUp() {
		accountController = new AccountController(accountRepository);
	}

/*	public void testGetAllAccounts() {
		
	}	
*/

	@Test
	@DisplayName("first test pls work")
	public void justAnExampleTest() {
		assertEquals("Login failed.", "Login failed.");
	}

}



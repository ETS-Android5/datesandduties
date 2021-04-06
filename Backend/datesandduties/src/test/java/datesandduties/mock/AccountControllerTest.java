package datesandduties.mock;

import datesandduties.Accounts.AccountController;
import datesandduties.Accounts.Account;
import datesandduties.Accounts.AccountRepository;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Captor;

import static org.mockito.Mockito.*;

import org.mockito.junit.*;

import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.*;

import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

// rough :(

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

	private static AccountController accountController = null;


	private static AccountRepository accountRepository = mock(AccountRepository.class); 

/*	public static void main(String[] args) {
		AccountControllerTester tester = new AccountControllerTester();
		tester.setUp();
		System.out.println(tester.testFunctions()?"pass":"fail");
	}
*/

	@BeforeAll
	public static void setUp() {
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

	@Test
	public void loginWork() {
		accountController = new AccountController(accountRepository);
		assertEquals(accountController.loginWork("username", "password"), "Login not work");
	}

}



package datesandduties.mock;

import datesandduties.Accounts.AccountController;
import datesandduties.Accounts.Account;
import datesandduties.Accounts.AccountRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito.*;

// rough :(

@ExtendedWith(MockitoExtension.class)
public class AccountControllerTester {
	
	AccountController accountController = null;
	
	@Mock
	AccountRepository accountRepository; //= Mockito.mock(AccountRepository.class); 

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

/*	public static void main(String[] args) {
		AccountControllerTester tester = new AccountControllerTester();
		tester.setUp();
		System.out.println(tester.testFunctions()?"pass":"fail");
	}
*/

	@Before
	public void setUp() {
		accountController = new AccountController(accountRepository);
	}

	public void testGetAllAccounts() {
		when()
	}	

/*	public boolean testFunctions() {
		assertNotNull(accountMock);

		when(accountMock.isAvailable()).thenReturn(true);

		AccountController t = new AccountController();
		t.addNewAccount();
		
	}
*/


}



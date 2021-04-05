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

	@Mock
	Account accountMock;

	public static void main(String[] args) {
		AccountControllerTester tester = new AccountControllerTester();
		tester.setUp();
		System.out.println(tester.testFunctions()?"pass":"fail");
	}

	public void setUp() {
	}

	public boolean testFunctions() {
		assertNotNull(accountMock);

		when(accountMock.isAvailable()).thenReturn(true);

		AccountController t = new AccountController();
		t.addNewAccount();
		
	}

}



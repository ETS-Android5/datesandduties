package datesandduties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import datesandduties.Accounts.Account;
import datesandduties.Accounts.AccountController;
import datesandduties.Accounts.AccountRepository;

//@RunWith(MockitoJUnitRunner.class)
public class AccountTests {
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddNewAccount() {
		Account account = new Account("name", "mockUsername", "password", "email", "gender", 15, 1234567890, "country");
		AccountController testController = mock(AccountController.class);

		when(testController.addNewAccount(account)).thenReturn("Entry Saved!");

		String result = testController.addNewAccount(account);
		assertEquals("Entry Saved!", result);

		verify(testController, times(1)).addNewAccount(account);

	}
	
	@InjectMocks
	AccountController acctController = new AccountController();

	@Mock
	AccountRepository acctRepository;
	
	@Test
	public void testFindByUsername() { 

		when(acctRepository.findByUsername("mockUsername")).thenReturn(new Account("name", "mockUsername", "password", "email@email.com", "gender", 15, 1234567890, "country"));
		Account account = acctController.findByUsername("mockUsername");

		assertEquals("name", account.getName());
		assertEquals("mockUsername", account.getUsername());
		assertEquals("password", account.getPassword());
		assertEquals("email@email.com", account.getEmail());
		assertEquals("gender", account.getGender());
		assertEquals((Integer) 15, account.getAge());
		assertEquals((Integer) 1234567890, account.getPhone());
		assertEquals("country", account.getCountry());


	}
}

package datesandduties.Accounts;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	//public String loginWork(String username, String password);
	Account findByUsername(String username);
}

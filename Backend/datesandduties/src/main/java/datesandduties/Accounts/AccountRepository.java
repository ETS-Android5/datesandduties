package datesandduties.Accounts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	//public String loginWork(String username, String password);
	List<Account> findByUsernameLike(String username);
	Account findByUsername(String username);

}

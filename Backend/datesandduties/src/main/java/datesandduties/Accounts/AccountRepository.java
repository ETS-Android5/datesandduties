package datesandduties.Accounts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByUsername(String username);

}

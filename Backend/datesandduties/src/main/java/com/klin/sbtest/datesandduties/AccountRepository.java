package com.klin.sbtest.datesandduties;
import org.springframework.data.repository.CrudRepository;
import com.klin.sbtest.datesandduties.Account;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AccountRepository extends CrudRepository<Account, Integer> {

}

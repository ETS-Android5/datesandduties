package datesandduties.Tasks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	public Task findByOwnerAndTitle(String owner, String title);

	Task findByTitle(String title);


}

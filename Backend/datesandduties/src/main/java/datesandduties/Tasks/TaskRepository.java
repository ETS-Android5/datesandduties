package datesandduties.Tasks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	public Task findByOwnerAndTitle(String owner, String title);

	public List<Task> findByTitleLike(String title);
	public Task findByTitle(String title);


}

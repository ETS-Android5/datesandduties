package datesandduties.Events;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
	public Event findByOwnerAndTitle(String owner, String title);
	Event findByTitle(String title);

}

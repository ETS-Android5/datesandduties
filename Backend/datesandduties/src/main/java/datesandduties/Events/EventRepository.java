package datesandduties.Events;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
	public Event findByOwnerAndTitle(String owner, String title);
	public List<Event> findByTitleLike(String title);
	public Event findByTitle(String title);
    public List<Event> findByDateLike(LocalDate date);
}

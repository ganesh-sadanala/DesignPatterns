package EventSource.src.main.java.com.app.repository;

import EventSource.src.main.java.com.app.event.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository {
    boolean saveEvent(Event event);
    List<Event> getAllEvents();
}

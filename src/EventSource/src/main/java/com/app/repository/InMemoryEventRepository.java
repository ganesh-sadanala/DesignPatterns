package EventSource.src.main.java.com.app.repository;

import EventSource.src.main.java.com.app.event.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryEventRepository implements EventRepository{

    private List<Event> events = new ArrayList<>();
    @Override
    public boolean saveEvent(Event event) {
        events.add(event);
        return true;
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
}

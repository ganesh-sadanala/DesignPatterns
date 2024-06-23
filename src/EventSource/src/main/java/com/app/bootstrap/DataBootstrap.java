package EventSource.src.main.java.com.app.bootstrap;

import EventSource.src.main.java.com.app.event.AuthorizationEvent;
import EventSource.src.main.java.com.app.event.Event;
import EventSource.src.main.java.com.app.event.LoadEvent;
import EventSource.src.main.java.com.app.repository.EventRepository;
import EventSource.src.main.java.com.app.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataBootstrap implements ApplicationRunner {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public DataBootstrap(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Bootstrap data on applicaiton start

        // Boostrap events data
        Event event1 = new LoadEvent("user123", new BigDecimal("1000.00"), LocalDateTime.now().minusDays(2));
        Event event2 = new LoadEvent("user456", new BigDecimal("1000.00"), LocalDateTime.now().minusDays(2));
        Event event3 = new AuthorizationEvent("user456", new BigDecimal("500.00"), LocalDateTime.now().minusDays(1), true);
        Event event4 = new LoadEvent("user789", new BigDecimal("200.00"), LocalDateTime.now());

        eventRepository.saveEvent(event1);
        eventRepository.saveEvent(event2);
        eventRepository.saveEvent(event3);
        eventRepository.saveEvent(event4);

        // Bootstrap user data
        userRepository.updateUserBalance("user123", new BigDecimal("1000.00"));
        userRepository.updateUserBalance("user456", new BigDecimal("500.00"));
        userRepository.updateUserBalance("user789", new BigDecimal("200.00"));
    }
}

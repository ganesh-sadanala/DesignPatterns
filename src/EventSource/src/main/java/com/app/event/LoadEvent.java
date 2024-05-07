package EventSource.src.main.java.com.app.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;


// immutable Load Event
public class LoadEvent implements Event{
    private final String userId;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public LoadEvent(String userId, BigDecimal amount, LocalDateTime timestamp) {
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

package EventSource.src.main.java.com.app.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;


// immutable Authorization Event
public class AuthorizationEvent implements Event{
    private final String userId;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private final boolean isApproved;

    public AuthorizationEvent(String userId, BigDecimal amount, LocalDateTime timestamp, boolean isApproved) {
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.isApproved = isApproved;
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

    public boolean isApproved() {
        return isApproved;
    }
}

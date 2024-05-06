package Other.EventSource.src.main.java.com.app.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository{

    private final Map<String, BigDecimal> userData;

    public InMemoryUserRepository() {
        this.userData = new HashMap<>();
    }

    @Override
    public BigDecimal getUserBalance(String userId) {
        return userData.getOrDefault(userId, BigDecimal.ZERO);
    }

    @Override
    public boolean updateUserBalance(String userId, BigDecimal amount) {
        BigDecimal currentBalance = userData.getOrDefault(userId, BigDecimal.ZERO);
        BigDecimal updatedBalance = currentBalance.add(amount);
        userData.put(userId, updatedBalance);
        return true;
    }
}

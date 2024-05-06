package Other.EventSource.src.main.java.com.app.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UserRepository {
    BigDecimal getUserBalance(String userId);

    boolean updateUserBalance(String userId, BigDecimal amount);
}


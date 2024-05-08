package EventSource.src.main.java.com.app.service;

import EventSource.src.main.java.com.app.event.AuthorizationEvent;
import EventSource.src.main.java.com.app.event.LoadEvent;
import dev.codescreen.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public LoadResponse processLoadTransaction(LoadRequest request) {
        String userId = request.getUserId();

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID must not be null or empty");
        }

        Amount balance=request.getTransactionAmount();
        BigDecimal amount = balance.getAmount();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Process load transaction for userId
        // Update the user's balance
        boolean userUpdateSaved = userRepository.updateUserBalance(userId, amount);

        // Generate event and save it
        LoadEvent event = new LoadEvent(userId, amount, LocalDateTime.now());
        boolean eventSaved = eventRepository.saveEvent(event);

        // Return the updated balance in the response

        BigDecimal updatedBalance = userRepository.getUserBalance(userId);
        balance.setAmount(updatedBalance);
        return new LoadResponse(userId, request.getMessageId(), balance);
    }

    public AuthorizationResponse processAuthorizationTransaction(AuthorizationRequest request) {
        String userId = request.getUserId();
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID must not be null or empty");
        }

        BigDecimal amount = request.getTransactionAmount().getAmount();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Process authorization transaction for userId
        // Check if user has sufficient balance for authorization
        BigDecimal userBalance = userRepository.getUserBalance(userId);
        boolean isApproved = userBalance.compareTo(amount) >= 0;

        // Generate AuthorizationEvent and save it, even if the transaction fails
        AuthorizationEvent event = new AuthorizationEvent(userId, amount, LocalDateTime.now(), isApproved);
        eventRepository.saveEvent(event);

        // Return the updated balance in the response
        Amount transactionAmount=request.getTransactionAmount();
        BigDecimal updatedBalance = isApproved?userBalance.subtract(transactionAmount.getAmount()):userBalance;
        transactionAmount.setAmount(updatedBalance);
        return new AuthorizationResponse(userId, request.getMessageId(), isApproved?ResponseCode.APPROVED:ResponseCode.DECLINED, transactionAmount);
    }
}

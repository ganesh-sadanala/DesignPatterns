package Other.EventSource.src.test.java.com.app;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import dev.codescreen.model.*;
import Other.EventSource.src.main.java.com.app.repository.UserRepository;
import Other.EventSource.src.main.java.com.app.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Other.EventSource.src.main.java.com.app.event.LoadEvent;
import Other.EventSource.src.main.java.com.app.repository.EventRepository;

public class TransactionServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessLoadTransaction_Success() {
        // Arrange
        LoadRequest request = new LoadRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(100), "USD", DebitCredit.CREDIT));

        // Mock repository to return a lower balance than the requested amount
        when(userRepository.updateUserBalance("testUserId", request.getTransactionAmount().getAmount())).thenReturn(true);
        when(userRepository.getUserBalance("testUserId")).thenReturn(BigDecimal.valueOf(100));

        // Act
        LoadResponse response = transactionService.processLoadTransaction(request);

        // Assert
        verify(eventRepository, times(1)).saveEvent(any(LoadEvent.class));
        assertEquals("testUserId", response.getUserId());
        assertEquals("testMessageId", response.getMessageId());
        assertEquals(BigDecimal.valueOf(100), response.getBalance().getAmount());

        // Verify balance is updated
        BigDecimal expectedBalance = BigDecimal.valueOf(100); // Assuming starting balance is 0
        verify(userRepository, times(1)).updateUserBalance("testUserId", expectedBalance);
    }

    @Test
    public void testProcessLoadTransaction_InvalidAmount() {
        // Arrange
        LoadRequest request = new LoadRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(-100), "USD", DebitCredit.CREDIT));


        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> transactionService.processLoadTransaction(request));
    }

    @Test
    public void testProcessAuthorizationTransaction_InsufficientBalance() {
        // Arrange
        AuthorizationRequest request = new AuthorizationRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(100), "USD", DebitCredit.DEBIT));

        // Mock repository to return a lower balance than the requested amount
        when(userRepository.getUserBalance("testUserId")).thenReturn(BigDecimal.valueOf(50));

        // Act
        AuthorizationResponse response = transactionService.processAuthorizationTransaction(request);

        // Assert
        assertFalse(response.getResponseCode().equals(ResponseCode.APPROVED));
    }

    @Test
    public void testProcessAuthorizationTransaction_SufficientBalance() {
        // Arrange
        AuthorizationRequest request = new AuthorizationRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(50), "USD", DebitCredit.DEBIT));

        // Mock repository to return a higher balance than the requested amount
        when(userRepository.getUserBalance("testUserId")).thenReturn(BigDecimal.valueOf(100));

        // Act
        AuthorizationResponse response = transactionService.processAuthorizationTransaction(request);

        // Assert
        assertTrue(response.getResponseCode().equals(ResponseCode.APPROVED));
    }
}

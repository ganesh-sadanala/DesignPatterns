package Other.EventSource.src.test.java.com.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import dev.codescreen.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import Other.EventSource.src.main.java.com.app.controller.TransactionController;
import Other.EventSource.src.main.java.com.app.service.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    public void testPing_Success() {
        // Act
        ResponseEntity<Ping> response = (ResponseEntity<Ping>) transactionController.ping();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getServerTime());
    }
    // Load Transaction Tests

    @Test
    public void testLoadTransaction_ValidRequest_Success() {
        // Arrange
        LoadRequest request = new LoadRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(100), "USD", DebitCredit.CREDIT));

        LoadResponse expectedResponse = new LoadResponse("testUserId", "testMessageId", request.getTransactionAmount());

        // Mock service method
        when(transactionService.processLoadTransaction(request)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<LoadResponse> responseEntity = (ResponseEntity<LoadResponse>) transactionController.loadTransaction(request);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(expectedResponse.getBalance().getAmount(), responseEntity.getBody().getBalance().getAmount()); // Check if the expected balance is returned
    }

    @Test
    public void testLoadTransaction_InvalidAmount_Failure() {
        // Arrange
        LoadRequest request = new LoadRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(-100), "USD", DebitCredit.CREDIT));

        when(transactionService.processLoadTransaction(request))
                .thenThrow(new IllegalArgumentException("Amount must be greater than zero"));

        // Act
        ResponseEntity<LoadResponse> responseEntity = (ResponseEntity<LoadResponse>) transactionController.loadTransaction(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        verify(transactionService, times(1)).processLoadTransaction(request);
        assertNull(responseEntity.getBody());
    }

       // Authorization Transaction Tests

    @Test
    public void testAuthorizationTransaction_ValidRequest_Success() {
        // Arrange
        AuthorizationRequest request = new AuthorizationRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(100), "USD", DebitCredit.DEBIT));

        AuthorizationResponse expectedResponse = new AuthorizationResponse("testUserId", "testMessageId", ResponseCode.APPROVED, request.getTransactionAmount());

        // Mock service method
        when(transactionService.processAuthorizationTransaction(request)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<AuthorizationResponse> responseEntity = (ResponseEntity<AuthorizationResponse>) transactionController.authorizationTransaction(request);

        //Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testAuthorizationTransaction_InvalidRequest_Failure() {
        // Arrange
        AuthorizationRequest request = new AuthorizationRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(100), "USD", DebitCredit.DEBIT));

        when(transactionService.processAuthorizationTransaction(request))
                .thenThrow(new IllegalArgumentException("Insufficient balance"));

        // Act
        ResponseEntity<AuthorizationResponse> responseEntity = (ResponseEntity<AuthorizationResponse>) transactionController.authorizationTransaction(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        verify(transactionService, times(1)).processAuthorizationTransaction(request);
        assertNull(responseEntity.getBody());
    }
}

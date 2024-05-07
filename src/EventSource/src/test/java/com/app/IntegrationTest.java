package EventSource.src.test.java.com.app;

import dev.codescreen.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPingEndpoint() {
        System.out.println("port: "+port);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/ping", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("serverTime");
    }

    @Test
    public void testLoadTransaction_Success() {
        LoadRequest request = new LoadRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(100), "USD", DebitCredit.CREDIT));

        ResponseEntity<LoadResponse> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/load",
                HttpMethod.PUT,
                new HttpEntity<>(request),
                LoadResponse.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testLoadTransaction_InvalidRequest() {
        LoadRequest request = new LoadRequest(); // Invalid request without required fields

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/load",
                HttpMethod.PUT,
                new HttpEntity<>(request),
                String.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testAuthorizationTransaction_Success() {
        AuthorizationRequest request = new AuthorizationRequest();
        request.setUserId("testUserId");
        request.setMessageId("testMessageId");
        request.setTransactionAmount(new Amount(BigDecimal.valueOf(100), "USD", DebitCredit.DEBIT));

        ResponseEntity<AuthorizationResponse> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/authorization",
                HttpMethod.PUT,
                new HttpEntity<>(request),
                AuthorizationResponse.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testAuthorizationTransaction_InvalidRequest() {
        AuthorizationRequest request = new AuthorizationRequest(); // Invalid request without required fields

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/authorization",
                HttpMethod.PUT,
                new HttpEntity<>(request),
                String.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}


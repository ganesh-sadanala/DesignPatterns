package EventSource.src.main.java.com.app.controller;

import dev.codescreen.model.*;
import EventSource.src.main.java.com.app.model.Error;
import EventSource.src.main.java.com.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /*
    * Tests the availability of the service and returns the current server time.
    * */
    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        try {
            LocalDateTime currentServerTime = LocalDateTime.now();

            // Create a Ping object and set the server time
            Ping ping = new Ping();
            ping.setServerTime(currentServerTime.toString());

            return ResponseEntity.ok(ping);
        } catch (Exception e) {
            // If any exception occurs, return a ResponseEntity with status code 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("500", "Internal Server Error"));
        }
    }

    /*
    * Removes funds from a user's account if sufficient funds are available.
    * */
    @PutMapping("/authorization")
    public ResponseEntity<?> authorizationTransaction(@RequestBody AuthorizationRequest request) {
        try {
            AuthorizationResponse response = transactionService.processAuthorizationTransaction(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("500", "Internal Server Error"));
        }
    }

    /*
    * Adds funds to a user's account.
    * */
    @PutMapping("/load")
    public ResponseEntity<?> loadTransaction(@RequestBody LoadRequest request) {
        try {
            LoadResponse response = transactionService.processLoadTransaction(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("500", "Internal Server Error"));
        }
    }
}

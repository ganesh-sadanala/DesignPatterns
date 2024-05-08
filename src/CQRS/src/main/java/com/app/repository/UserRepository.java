package CQRS.src.main.java.com.app.repository;

import CQRS.src.main.java.com.app.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> store = new HashMap<>();
}

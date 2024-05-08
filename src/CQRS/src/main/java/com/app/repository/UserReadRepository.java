package CQRS.src.main.java.com.app.repository;

public class UserReadRepository {
    private Map<String, UserAddress> userAddress = new HashMap<>();
    private Map<String, UserContact> userContact = new HashMap<>();
}

package CQRS.src.main.java.com.app.read;

import CQRS.src.main.java.com.app.model.Address;

public class UserAddress {
    private Map<String, Set<Address>> addressByRegion = new HashMap<>();
}

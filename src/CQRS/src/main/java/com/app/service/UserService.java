package CQRS.src.main.java.com.app.service;

import CQRS.src.main.java.com.app.model.Address;
import CQRS.src.main.java.com.app.model.Contact;
import CQRS.src.main.java.com.app.model.User;
import CQRS.src.main.java.com.app.repository.UserRepository;

import java.util.Set;

public class UserService {
    private UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(String userId, String firstName, String lastName) {
        User user = new User(userId, firstName, lastName);
        repository.addUser(userId, user);
    }

    public void updateUser(String userId, Set<Contact> contacts, Set<Address> addresses) {
        User user = repository.getUser(userId);
        user.setContacts(contacts);
        user.setAddresses(addresses);
        repository.addUser(userId, user);
    }

    public Set<Contact> getContactByType(String userId, String contactType) {
        User user = repository.getUser(userId);
        Set<Contact> contacts = user.getContacts();
        return contacts.stream()
                .filter(c -> c.getType().equals(contactType))
                .collect(Collectors.toSet());
    }

    public Set<Address> getAddressByRegion(String userId, String state) {
        User user = repository.getUser(userId);
        Set<Address> addresses = user.getAddresses();
        return addresses.stream()
                .filter(a -> a.getState().equals(state))
                .collect(Collectors.toSet());
    }
}

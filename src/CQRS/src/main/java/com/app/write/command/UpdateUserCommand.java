package CQRS.src.main.java.com.app.write.command;

import CQRS.src.main.java.com.app.model.Address;
import CQRS.src.main.java.com.app.model.Contact;

import java.util.Set;

public class UpdateUserCommand {
    private String userId;
    private Set<Address> addresses;
    private Set<Contact> contacts;
}

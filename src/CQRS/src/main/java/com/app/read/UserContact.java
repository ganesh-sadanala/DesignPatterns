package CQRS.src.main.java.com.app.read;

import CQRS.src.main.java.com.app.model.Contact;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserContact {
    private Map<String, Set<Contact>> contactByType = new HashMap<>();
}

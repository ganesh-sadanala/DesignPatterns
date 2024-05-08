package CQRS.src.main.java.com.app.read.projection;

import CQRS.src.main.java.com.app.model.Address;
import CQRS.src.main.java.com.app.model.Contact;
import CQRS.src.main.java.com.app.read.query.AddressByRegionQuery;
import CQRS.src.main.java.com.app.read.query.ContactByTypeQuery;
import CQRS.src.main.java.com.app.repository.UserReadRepository;

import java.util.Set;

public class UserProjection {
    private UserReadRepository readRepository;
    public UserProjection(UserReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    public Set<Contact> handle(ContactByTypeQuery query) {
        UserContact userContact = readRepository.getUserContact(query.getUserId());
        return userContact.getContactByType()
                .get(query.getContactType());
    }

    public Set<Address> handle(AddressByRegionQuery query) {
        UserAddress userAddress = readRepository.getUserAddress(query.getUserId());
        return userAddress.getAddressByRegion()
                .get(query.getState());
    }
}

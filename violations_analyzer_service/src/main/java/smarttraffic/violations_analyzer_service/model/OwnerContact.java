package smarttraffic.violations_analyzer_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ownerContact")
public class OwnerContact {

    @Id
    private long id;
    private String emailAddress;
    private String phoneNumber;
    private Address addressDTO;

    public OwnerContact() {

    }

    public OwnerContact(String emailAddress, String phoneNumber) {
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(Address addressDTO) {
        this.addressDTO = addressDTO;
    }

}

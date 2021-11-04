package smarttraffic.violations_analyzer_service.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("ownerContact")
public class OwnerContactDTO {
    private long id;
    private String emailAddress;
    private String phoneNumber;
    private AddressDTO addressDTO;

    public OwnerContactDTO() {

    }

    public OwnerContactDTO(String emailAddress, String phoneNumber) {
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

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }
}

package smarttraffic.violation_service.dto;

import smarttraffic.violation_service.entity.Address;

public class OwnerContactDTO {
    private long id;
    private String emailAddress;
    private String phoneNumber;
    private Address address;

    public OwnerContactDTO() {

    }

    public OwnerContactDTO(String emailAddress, String phoneNumber, Address address) {
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

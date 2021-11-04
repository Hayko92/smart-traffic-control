package smarttraffic.violations_analyzer_service.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("owner")
public class OwnerDTO {

    private long id;
    private String idNumber;
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private int points;
    private OwnerContactDTO ownerContactDTO;
    private Set<VehicleDTO> vehicleDTOSet;

    public OwnerDTO() {
    }

    public OwnerDTO(String idNumber, String licenseNumber, String firstName, String lastName, int points) {
        this.idNumber = idNumber;
        this.licenseNumber = licenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public OwnerContactDTO getOwnerContactDTO() {
        return ownerContactDTO;
    }

    public void setOwnerContactDTO(OwnerContactDTO ownerContactDTO) {
        this.ownerContactDTO = ownerContactDTO;
    }

    public Set<VehicleDTO> getVehicleDTOSet() {
        return vehicleDTOSet;
    }

    public void setVehicleDTOSet(Set<VehicleDTO> vehicleDTOSet) {
        this.vehicleDTOSet = vehicleDTOSet;
    }

    public int getRedusedPoint() {
        points--;
        return points;
    }
}

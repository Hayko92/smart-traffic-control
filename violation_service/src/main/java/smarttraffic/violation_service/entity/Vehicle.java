package smarttraffic.violation_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "plate_numbers")
    private String number;

    @Column(name = "mark")
    private String mark;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "model")
    private String model;

    @Column(name = "registration_certificate_numbers")
    private String registrationCertificate;

    @Column(name = "insurance_expiry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Asia/Yerevan")
    private Date insuranceExpiry;

    @Column(name = "tech_inspection_expiry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Asia/Yerevan")
    private Date techInspectionExpiry;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Vehicle() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRegistrationCertificate() {
        return registrationCertificate;
    }

    public void setRegistrationCertificate(String registrationCertificate) {
        this.registrationCertificate = registrationCertificate;
    }

    public Date getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public void setInsuranceExpiry(Date insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
    }

    public Date getTechInspectionExpiry() {
        return techInspectionExpiry;
    }

    public void setTechInspectionExpiry(Date techInspectionExpiry) {
        this.techInspectionExpiry = techInspectionExpiry;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

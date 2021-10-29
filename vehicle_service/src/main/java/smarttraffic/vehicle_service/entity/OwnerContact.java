package smarttraffic.vehicle_service.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
    public class OwnerContact implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "email_address")
        private String emailAddress;

        @Column(name = "phone_number")
        private String phoneNumber;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "owner_id")
        private Owner owner;


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

        public Owner getOwner() {
            return owner;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }
    }

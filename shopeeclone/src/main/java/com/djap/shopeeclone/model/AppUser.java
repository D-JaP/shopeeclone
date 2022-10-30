package com.djap.shopeeclone.model;

import com.djap.shopeeclone.enums.Gender;
import com.djap.shopeeclone.enums.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", initialValue = 4, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name =  "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "postcode")
    private int postcode;

    @Column(name = "address")
    private String address;

    @Column(name ="city")
    private String city;

    @Column(name = "activation_token")
    private String activationToken;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "provider")
    private String provider;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        AppUser User = (AppUser) o;
        return Objects.equals(id, User.id);
    }

}

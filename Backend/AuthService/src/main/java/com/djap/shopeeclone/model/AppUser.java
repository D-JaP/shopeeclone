package com.djap.shopeeclone.model;

import com.djap.shopeeclone.enums.Provider;
import com.djap.shopeeclone.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    @Column(name = "username")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ActivationToken activationToken;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private RefreshToken refreshToken;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        AppUser User = (AppUser) o;
        return Objects.equals(id, User.id);
    }


}

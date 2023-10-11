package com.djap.shopeeclone.model;

import com.djap.shopeeclone.enums.Provider;
import com.djap.shopeeclone.enums.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class AppUser implements UserDetails, OidcUser {
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
    RefreshToken refreshToken;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;
//    Oidc parameter
    @Transient
    private String oidcId;
    @Transient
    private String imgUrl;



    @Transient
    Map<String, Object> attributes;


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


    @Override
    public String getName() {
        return Objects.nonNull(oidcId)? oidcId: (firstname + lastname) ;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userRole != null){
            authorities.add(new SimpleGrantedAuthority(this.userRole.toString()));
            return authorities;
        }
        else {
            return this.authorities;
        }
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }

    @Override
    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }
}

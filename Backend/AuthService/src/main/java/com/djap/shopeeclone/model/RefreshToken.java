package com.djap.shopeeclone.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    private static final int EXPIRATION  = 7;

    @Id
    @Column(name = "user_id")
    private long id;

    @Column(name =  "token")
    private String token;

    @Column(name = "expired")
    private LocalDateTime expirationDate;

    @OneToOne
    @MapsId
    @JoinColumn(name= "user_id")
    private AppUser user;

    public LocalDateTime setExpirationDate(){
        LocalDateTime now = LocalDateTime.now();
        this.expirationDate = now.plusDays(EXPIRATION);
        return expirationDate;
    }


}

package com.djap.shopeeclone.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {
    private static final int EXPIRATION = 10;

    @Id
    @Column(name = "user_id")
    private long id;

    @Column(name = "password_reset_token")
    private String token;

    @Column(name = "expiration_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime expiryDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private AppUser user;

    public LocalDateTime setExpiryDate(){
        LocalDateTime dateTime = LocalDateTime.now();
        this.expiryDate = dateTime.plusMinutes(EXPIRATION);
        return dateTime;
    }
}

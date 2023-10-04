package com.djap.shopeeclone.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "activation_token")
public class ActivationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @Column(name = "user_id")
    private long id;

    @Column(name = "activation_token")
    private String token;

    @Column(name = "expiration_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime expiryDate;

    @OneToOne()
    @MapsId
    @JoinColumn(name = "user_id")
    private AppUser user;

    public LocalDateTime setExpiryDate() {
        LocalDateTime dateTime = LocalDateTime.now();
        this.expiryDate = dateTime.plusMinutes(EXPIRATION);
        return dateTime;
    }
}

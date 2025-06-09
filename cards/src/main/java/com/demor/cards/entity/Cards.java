package com.demor.cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Cards extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobileNumber;

    private String cardNumber;

    private int cvv;

    private String cardType;

    // int for simplicity
    private int cardLimit;

    // int for simplicity
    private int balance;

}

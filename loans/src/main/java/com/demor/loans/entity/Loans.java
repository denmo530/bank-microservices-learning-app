package com.demor.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Loans extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoanAmount;

    private int loanAmountPaid;

    private int outstandingLoanAmount;
}

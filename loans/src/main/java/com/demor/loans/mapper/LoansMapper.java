package com.demor.loans.mapper;

import com.demor.loans.dto.LoansDto;
import com.demor.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotalLoanAmount(loans.getTotalLoanAmount());
        loansDto.setLoanAmountPaid(loans.getLoanAmountPaid());
        loansDto.setOutstandingLoanAmount(loans.getOutstandingLoanAmount());
        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoanAmount(loansDto.getTotalLoanAmount());
        loans.setLoanAmountPaid(loansDto.getLoanAmountPaid());
        loans.setOutstandingLoanAmount(loansDto.getOutstandingLoanAmount());
        return loans;
    }
}

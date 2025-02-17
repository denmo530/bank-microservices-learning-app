package com.demor.accounts.services;

import com.demor.accounts.dtos.CustomerDto;

public interface AccountService {

    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto getAccountDetails(String mobileNumber);

    boolean UpdateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}

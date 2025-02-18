package com.demor.accounts.service;

import com.demor.accounts.dto.CustomerDto;

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

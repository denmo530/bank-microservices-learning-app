package com.demor.accounts.service;

import com.demor.accounts.dto.AccountDto;
import com.demor.accounts.dto.CardsDto;
import com.demor.accounts.dto.CustomerDetailsDto;
import com.demor.accounts.dto.LoansDto;
import com.demor.accounts.entity.Account;
import com.demor.accounts.entity.Customer;
import com.demor.accounts.exception.ResourceNotFoundException;
import com.demor.accounts.mapper.AccountMapper;
import com.demor.accounts.mapper.CustomerMapper;
import com.demor.accounts.repository.AccountRepository;
import com.demor.accounts.repository.CustomerRepository;
import com.demor.accounts.service.client.CardsFeignClient;
import com.demor.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto((AccountMapper.mapToAccountsDto(account, new AccountDto())));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);;
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);;
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}

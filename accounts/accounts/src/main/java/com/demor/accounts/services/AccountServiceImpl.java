package com.demor.accounts.services;

import com.demor.accounts.dtos.AccountDto;
import com.demor.accounts.dtos.CustomerDto;
import com.demor.accounts.entities.Account;
import com.demor.accounts.entities.Customer;
import com.demor.accounts.exception.CustomerAlreadyExistsException;
import com.demor.accounts.exception.ResourceNotFoundException;
import com.demor.accounts.mapper.AccountMapper;
import com.demor.accounts.mapper.CustomerMapper;
import com.demor.accounts.repositories.AccountRepository;
import com.demor.accounts.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        var isCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if (isCustomer.isPresent()) throw new CustomerAlreadyExistsException(
                "Customer already exists with given mobile number: " + customer.getMobileNumber()
        );

        Customer savedCustomer = customerRepository.save(customer);

        accountRepository.save(createAccountFromCustomer(savedCustomer));
    }

    @Override
    public CustomerDto getAccountDetails(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        var account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccount(AccountMapper.mapToAccountsDto(account, new AccountDto()));

        return customerDto;
    }

    // Not a proper update function, only for learning purposes
    @Override
    public boolean UpdateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccount();

        if (accountDto != null) {
            Account customerAccount = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            "Account", "AccountNumber", accountDto.getAccountNumber().toString()
                    )
            );
        AccountMapper.mapToAccount(accountDto, customerAccount);
        customerAccount = accountRepository.save(customerAccount);

        var customerId = customerAccount.getCustomerId();
        var customer = customerRepository.findById(customerAccount.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Customer", "customerId", customerId.toString()
                )
        );

        CustomerMapper.mapToCustomer(customerDto, customer);

        customerRepository.save(customer);

        isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customerDetails = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        accountRepository.deleteByCustomerId(customerDetails.getCustomerId());
        customerRepository.deleteById(customerDetails.getCustomerId());

        return true;
    }

    private Account createAccountFromCustomer(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        // Fake account number generation
        long accountNUmber = 1000000000L + new Random().nextLong(9000000);

        account.setAccountNumber(accountNUmber);
        account.setAccountType("Savings Account");
        account.setBranchAddress("Västgötegatan 15A");

        return account;
    }
}

package com.demor.accounts.repository;

import com.demor.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer>  findByEmail(String email);

    Optional<Customer>  findByMobileNumber(String mobileNumber);

    Optional<Customer>  findByCustomerId(Long customerId);
}

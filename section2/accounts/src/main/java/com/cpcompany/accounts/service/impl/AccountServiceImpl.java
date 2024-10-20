package com.cpcompany.accounts.service.impl;

import com.cpcompany.accounts.dto.CustomerDTO;
import com.cpcompany.accounts.repository.AccountRepository;
import com.cpcompany.accounts.repository.CustomerRepository;
import com.cpcompany.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    /**
     * Creates a new account with the given customer information.
     *
     * @param customerDTO
     *            the customer information
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {

    }
}

package com.cpcompany.accounts.service;

import com.cpcompany.accounts.dto.CustomerDTO;

public interface IAccountService {
    /**
     * Create a new account and return the account details.
     *
     * @param customerDTO
     *            the customer details
     */
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccount(String mobileNumber);
}

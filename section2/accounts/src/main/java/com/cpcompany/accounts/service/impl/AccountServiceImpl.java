package com.cpcompany.accounts.service.impl;

import com.cpcompany.accounts.constants.AccountsConstants;
import com.cpcompany.accounts.dto.AccountsDTO;
import com.cpcompany.accounts.dto.CustomerDTO;
import com.cpcompany.accounts.entity.Accounts;
import com.cpcompany.accounts.entity.Customer;
import com.cpcompany.accounts.exception.CustomerAlreadyExistsException;
import com.cpcompany.accounts.exception.ResourceNotFoundException;
import com.cpcompany.accounts.mapper.AccountsMapper;
import com.cpcompany.accounts.mapper.CustomerMapper;
import com.cpcompany.accounts.repository.AccountRepository;
import com.cpcompany.accounts.repository.CustomerRepository;
import com.cpcompany.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

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
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with give mobile number : " + customerDTO.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("SYSTEM");
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountDTO();
        if (accountsDTO != null) {
            Accounts accounts = accountRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountsDTO.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDTO, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
 * Creates a new account for the given customer.
 *
 * @param customer the customer for whom the account is being created
 * @return the newly created account
 */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber =  1000000000L + new Random().nextInt( 900000000);

        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("SYSTEM");
        return newAccount;
    }


}

package com.chandan.resourcemgmt.loans.service;

import com.chandan.resourcemgmt.loans.dto.LoansDto;

public interface ILoansService {
    void createLoan(String mobileNumber);
    LoansDto fetchLoan(String mobileNumber);
    boolean updateLoan(LoansDto loansDto);
    boolean deleteLoan(String mobileNumber);
}

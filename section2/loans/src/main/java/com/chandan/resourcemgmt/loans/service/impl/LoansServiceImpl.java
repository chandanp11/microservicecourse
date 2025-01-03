package com.chandan.resourcemgmt.loans.service.impl;

import com.chandan.resourcemgmt.loans.constants.LoanConstants;
import com.chandan.resourcemgmt.loans.dto.LoansDto;
import com.chandan.resourcemgmt.loans.entity.Loans;
import com.chandan.resourcemgmt.loans.exception.LoanAlreadyExistsException;
import com.chandan.resourcemgmt.loans.exception.ResourceNotFoundException;
import com.chandan.resourcemgmt.loans.mapper.LoansMapper;
import com.chandan.resourcemgmt.loans.repository.LoansRepository;
import com.chandan.resourcemgmt.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;
    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already exists with given mobile numeber: "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));

    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(String.valueOf(randomNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return  true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}

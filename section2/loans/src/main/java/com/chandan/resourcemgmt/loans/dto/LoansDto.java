package com.chandan.resourcemgmt.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {

    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Loan number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number must be 12 digits")
    private String loanNumber;

    @NotEmpty(message = "Loan type cannot be empty")
    private String loanType;

    @Positive(message = "Total Loan Amount cannot be negative")
    private int totalLoan;

    @PositiveOrZero(message = "Amount paid cannot be negative")
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount cannot be negative")
    private int outstandingAmount;


}
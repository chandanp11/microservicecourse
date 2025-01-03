package com.cpcompany.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "DTO to hold accounts information"
)
public class AccountsDTO {

    @NotEmpty(message = "Account number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be 10 digits")
    @Schema(description = "Account number", example = "1234567890")
    private Long accountNumber;

    @NotEmpty(message = "Account type should not be empty")
    @Schema(description = "Account type", example = "Savings")
    private String accountType;

    @Schema(description = "Branch address", example = "123 main street, New York")
    @NotEmpty(message = "Branch address should not be empty")
    private String branchAddress;
}

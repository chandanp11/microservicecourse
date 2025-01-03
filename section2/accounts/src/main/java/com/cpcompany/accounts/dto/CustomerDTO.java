package com.cpcompany.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name="Customer",
        description = "Schema to hold customer and account information"
)
public class CustomerDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, max = 30, message = "Length of customer name should be between 5 and 30")
    @Schema(description = "Name of the customer", example = "John Doe")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Schema(description = "Email of the customer", example = "jdoe@cp.com")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    @Schema(description = "Mobile number of the customer", example = "1234567890")
    private String mobileNumber;

    @Schema(description = "Account details of the customer")
    private AccountsDTO accountDTO;
}

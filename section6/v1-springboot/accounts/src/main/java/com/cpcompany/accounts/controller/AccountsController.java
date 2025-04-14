package com.cpcompany.accounts.controller;

import com.cpcompany.accounts.constants.AccountsConstants;
import com.cpcompany.accounts.dto.CustomerDTO;
import com.cpcompany.accounts.dto.ErrorResponseDTO;
import com.cpcompany.accounts.dto.ResponseDTO;
import com.cpcompany.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD API for Accounts microservice", description = "CRUD API for Accounts microservice to create, fetch, update and delete account details")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {
    private IAccountService accountService;

    @Operation(
            summary = "Create account REST API",
            description = "Create a new account and return the account details"
    )
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "fetch account REST API",
            description = "returns the account details"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                               String mobileNumber) {
        CustomerDTO customerDTO = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account updated successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountService.updateAccount(customerDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                               String mobileNumber){
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }
}

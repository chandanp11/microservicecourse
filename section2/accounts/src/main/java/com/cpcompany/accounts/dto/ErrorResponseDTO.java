package com.cpcompany.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name="ErrorResponse",
        description = "Error Response"
)
public class ErrorResponseDTO {
    @Schema(description = "API path", example = "/accounts")
    private String apiPath;

    @Schema(description = "Error code", example = "404")
    private HttpStatus errorCode;

    @Schema(description = "Error message", example = "Account not found")
    private String errorMessage;

    @Schema(description = "Error time", example = "2023-08-20T10:15:30")
    private LocalDateTime errorTime;
}

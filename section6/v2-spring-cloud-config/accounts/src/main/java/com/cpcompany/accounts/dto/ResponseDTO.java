package com.cpcompany.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(
        name ="Response",
        description = "Schema to hold response details"
)
public class ResponseDTO {
    @Schema(description = "Status code", example = "201")
    private String statusCode;

    @Schema(description = "Status message", example = "Account created successfully")
    private String statusMessage;
}

package com.acolque.miniwalletapi.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    @Schema(example = "RuntimeException.class")
    private String exName;
    private String message;
}

package com.acolque.miniwalletapi.controllers;

import com.acolque.miniwalletapi.entities.ErrorDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ApiResponse(
            responseCode = "404",
            description = "Alguna api externa que consumimos dejó de responder",
            content = @Content(schema = @Schema(implementation = ErrorDto.class), examples = { @ExampleObject("{\n\"message\":\"Error en una api\"\n}")})
    )
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorDto> handleRestClientException(RestClientException ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .exName(ex.getClass().getName())
                .message(String.format("Error en una api -> %s", ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .exName(ex.getClass().getName())
                .message("Ocurrió un error inesperado -> " + ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}

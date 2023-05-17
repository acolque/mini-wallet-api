package com.acolque.miniwalletapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Operation(summary = "Devuelve pong si la api está viva")
    @ApiResponse(
            responseCode = "200",
            description = "nice",
            content = @Content(schema = @Schema(implementation = String.class), examples = { @ExampleObject("pong")})
    )
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}

package com.acolque.miniwalletapi.controllers;

import com.acolque.miniwalletapi.services.DollarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Información sobre el dolar",
    description = "Podemos obtener el valor del dolar blue y la brecha cambiaria vs el oficial"
)
@RestController
@RequestMapping("/dolar")
public class DollarController {

    private final DollarService dollarService;

    public DollarController(DollarService dollarService) {
        this.dollarService = dollarService;
    }

    @Operation(summary = "Obtener dolar blue", description = "El precio que se obtiene es el valor en vivo")
    @ApiResponse(
            responseCode = "200",
            description = "nice",
            content = @Content(schema = @Schema(implementation = Double.class), examples = { @ExampleObject("499.9")})
    )
    @GetMapping("/blue")
    public double getDollarBluePrice() {
        return dollarService.getDollarBluePrice();
    }

    @Operation(summary = "Obtener brecha cambiaria", description = "El resultado es la diferencia expresada en porcentaje")
    @ApiResponse(
            responseCode = "200",
            description = "nice",
            content = @Content(schema = @Schema(implementation = String.class), examples = { @ExampleObject("98%")})
    )
    @GetMapping("/brecha")
    public String getDiffBetweenDollarBlueAndOfficial() {
        return dollarService.getDiffBetweenDollarBlueAndOfficial();
    }
}

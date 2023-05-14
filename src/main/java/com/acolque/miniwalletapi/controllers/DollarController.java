package com.acolque.miniwalletapi.controllers;

import com.acolque.miniwalletapi.services.DollarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dolar")
public class DollarController {

    private final DollarService dollarService;

    public DollarController(DollarService dollarService) {
        this.dollarService = dollarService;
    }

    @GetMapping("/blue")
    public double getDollarBluePrice() {
        return dollarService.getDollarBluePrice();
    }

    @GetMapping("/brecha")
    public String getDiffBetweenDollarBlueAndOfficial() {
        return dollarService.getDiffBetweenDollarBlueAndOfficial();
    }
}

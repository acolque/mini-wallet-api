package com.acolque.miniwalletapi.controllers;

import com.acolque.miniwalletapi.services.DollarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final DollarService dollarService;

    public WalletController(DollarService dollarService) {
        this.dollarService = dollarService;
    }

    @GetMapping("/dolar/blue")
    public double getDollarBluePrice() {
        return dollarService.getDollarBluePrice();
    }

    @GetMapping("/dolar/brecha")
    public String getDiffBetweenDollarBlueAndOfficial() {
        return dollarService.getDiffBetweenDollarBlueAndOfficial();
    }
}

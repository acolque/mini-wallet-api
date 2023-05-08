package com.acolque.miniwalletapi.controllers;

import com.acolque.miniwalletapi.services.DollarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private DollarService dollarService;

    public WalletController(DollarService dollarService) {
        this.dollarService = dollarService;
    }

    @GetMapping("/dolar/blue")
    public double getDollarBluePrice() {
        return dollarService.getDollarBluePrice();
    }
}

package com.acolque.miniwalletapi.controllers;

import com.acolque.miniwalletapi.datasource.CryptoDataSource;
import com.acolque.miniwalletapi.entities.CryptoInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    private CryptoDataSource cryptoDataSource;

    public CryptoController(CryptoDataSource cryptoDataSource) {
        this.cryptoDataSource = cryptoDataSource;
    }

    @GetMapping("/all")
    public List<CryptoInfoDto> getAllCryptoInfo() {
        return cryptoDataSource.getCryptoInfo();
    }
}

package com.acolque.miniwalletapi.services;

import com.acolque.miniwalletapi.datasource.apis.DollarDataSource;
import org.springframework.stereotype.Service;

@Service
public class DollarService {

    private DollarDataSource dollarDataSource;

    public DollarService(DollarDataSource dollarDataSource) {
        this.dollarDataSource = dollarDataSource;
    }

    public double getDollarBluePrice() {
        return dollarDataSource.getDollarBluePrice();
    }
}

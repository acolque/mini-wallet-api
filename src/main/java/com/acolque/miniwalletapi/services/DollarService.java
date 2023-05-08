package com.acolque.miniwalletapi.services;

import com.acolque.miniwalletapi.datasource.apis.DollarDataSource;
import org.springframework.stereotype.Service;

@Service
public class DollarService {

    private final DollarDataSource dollarDataSource;

    public DollarService(DollarDataSource dollarDataSource) {
        this.dollarDataSource = dollarDataSource;
    }

    public double getDollarBluePrice() {
        return dollarDataSource.getDollarBluePrice();
    }

    public String getDiffBetweenDollarBlueAndOfficial() {
        double dollarBlue = dollarDataSource.getDollarBluePrice();
        double dollarOfficial = dollarDataSource.getDollarOfficial();
        Double diffPercentage = (dollarBlue-dollarOfficial)*100/dollarBlue;
        return String.format("%s%%", diffPercentage.intValue());
    }
}

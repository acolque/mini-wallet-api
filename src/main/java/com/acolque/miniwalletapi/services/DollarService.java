package com.acolque.miniwalletapi.services;

import com.acolque.miniwalletapi.datasource.apis.DollarDataSource;
import com.acolque.miniwalletapi.entities.DollarInfoDto;
import org.springframework.stereotype.Service;

@Service
public class DollarService {

    private final DollarDataSource dollarDataSource;

    public DollarService(DollarDataSource dollarDataSource) {
        this.dollarDataSource = dollarDataSource;
    }

    public double getDollarBluePrice() {
        return dollarDataSource.getDollarInfo()
                .map(DollarInfoDto::getBlue)
                .orElse(0D);
    }

    public String getDiffBetweenDollarBlueAndOfficial() {
        return dollarDataSource.getDollarInfo()
                .map(info -> (info.getBlue() - info.getOficial())*100/info.getBlue())
                .map(diff -> String.format("%s%%", diff.intValue()))
                .orElse("no data");
    }
}

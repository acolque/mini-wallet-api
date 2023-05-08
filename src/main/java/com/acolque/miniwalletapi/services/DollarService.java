package com.acolque.miniwalletapi.services;

import com.acolque.miniwalletapi.datasource.apis.DollarDataSource;
import com.acolque.miniwalletapi.entities.DollarInfoDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DollarService {

    private final DollarDataSource dollarDataSource;

    public DollarService(DollarDataSource dollarDataSource) {
        this.dollarDataSource = dollarDataSource;
    }

    public double getDollarBluePrice() {
        return dollarDataSource.getDollarInfo().map(DollarInfoDto::getBlue).orElse(0D);
    }

    public String getDiffBetweenDollarBlueAndOfficial() {
        Optional<DollarInfoDto> dollarInfo = dollarDataSource.getDollarInfo();
        if (dollarInfo.isPresent()) {
            Double diffPercentage = (dollarInfo.get().getBlue()-dollarInfo.get().getOficial())*100/dollarInfo.get().getBlue();
            return String.format("%s%%", diffPercentage.intValue());
        }
        return "no data";
    }
}

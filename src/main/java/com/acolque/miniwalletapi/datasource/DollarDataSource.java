package com.acolque.miniwalletapi.datasource;

import com.acolque.miniwalletapi.entities.DollarInfoDto;

import java.util.Optional;

public interface DollarDataSource {

    Optional<DollarInfoDto> getDollarInfo();
}

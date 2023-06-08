package com.acolque.miniwalletapi.datasource;

import com.acolque.miniwalletapi.entities.CryptoInfoDto;

import java.util.List;

public interface CryptoDataSource {
    List<CryptoInfoDto> getCryptoInfo();
}

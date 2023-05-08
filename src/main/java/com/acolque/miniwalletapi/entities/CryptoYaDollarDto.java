package com.acolque.miniwalletapi.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CryptoYaDollarDto {

    private double oficial;
    private double solidario;
    private double blue;
}

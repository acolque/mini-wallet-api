package com.acolque.miniwalletapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DollarInfoDto {

    private double oficial;
    private double solidario;
    private double blue;
}

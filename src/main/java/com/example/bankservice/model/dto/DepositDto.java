package com.example.bankservice.model.dto;

import lombok.Data;

@Data
public class DepositDto {
    private Integer accountNumber;
    private Integer amount;

}

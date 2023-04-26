package com.example.bankservice.model.dto;

import lombok.Data;

@Data
public class DepositParam {
    private Integer accountNumber;
    private Integer amount;

}

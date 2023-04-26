package com.example.bankservice.model.dto;

import lombok.Data;

@Data
public class WithdrawParam {
    private Integer accountNumber;
    private Integer amount;
}

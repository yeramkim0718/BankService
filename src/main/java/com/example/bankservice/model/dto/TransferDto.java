package com.example.bankservice.model.dto;

import lombok.Data;

@Data
public class TransferDto {
    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private Integer amount;

}

package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class TransactionDTO extends AbstractDTO{
    private String code;
    private String note;
    private Long customerId;
    private Long staffId;
}

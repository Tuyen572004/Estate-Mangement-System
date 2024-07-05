package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TransactionType {
    CSKH ("Chăm sóc khách hàng"),
    DDX ("Dẫn đi xem");

    private final String transactionTypeName;

    TransactionType(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new LinkedHashMap<>();
        for(TransactionType transactionType : TransactionType.values()){
            listType.put(transactionType.name(),transactionType.getTransactionTypeName());
        }
        return listType;
    }
}

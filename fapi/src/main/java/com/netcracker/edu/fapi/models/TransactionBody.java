package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionBody {
    private Long from;
    private Long to;
    private Double value;

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }

    public Double getValue() {
        return value;
    }

}

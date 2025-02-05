package com.askarakhmetov.hibernate.sample2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EngineType {
    ATMOSPHERIC("ATM"), TURBO("TU");

    private final String code;
}

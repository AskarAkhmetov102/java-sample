package com.askarakhmetov.hibernate.sample2.converter;

import com.askarakhmetov.hibernate.sample2.entity.EngineType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter
public class EngineTypeConverter implements AttributeConverter<EngineType, String> {
    @Override
    public String convertToDatabaseColumn(EngineType engineType) {
        if (engineType == null) {
            return null;
        }
        return engineType.getCode();
    }

    @Override
    public EngineType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(EngineType.values())
                .filter(engineType -> engineType.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

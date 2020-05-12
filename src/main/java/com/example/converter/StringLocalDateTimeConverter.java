package com.example.converter;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StringLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(MappingContext<String, LocalDateTime> context) {
        if (context.getSource() == null) {
            return null;
        }
        return LocalDateTime.parse(context.getSource());
    }

}

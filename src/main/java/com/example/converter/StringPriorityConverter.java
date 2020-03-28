package com.example.converter;

import com.example.model.Priority;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class StringPriorityConverter implements Converter<String, Priority> {

    @Override
    public Priority convert(MappingContext<String, Priority> context) {
        if (context.getSource() != null) {
            return Priority.getFromName(context.getSource());
        }
        return null;
    }

}

package com.example.config;

import com.example.converter.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MapperConfig {
    private final StringTrustLevelConverter stringTrustLevelConverter;
    private final StringStatusConverter stringStatusConverter;
    private final StringPriorityConverter stringPriorityConverter;
    private final StringLocalDateConverter stringLocalDateConverter;
//    private final UserTaskListConverter userTaskListConverter;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(stringTrustLevelConverter);
        modelMapper.addConverter(stringStatusConverter);
        modelMapper.addConverter(stringPriorityConverter);
        modelMapper.addConverter(stringLocalDateConverter);
//        modelMapper.addConverter(userTaskListConverter);

        return modelMapper;
    }
}

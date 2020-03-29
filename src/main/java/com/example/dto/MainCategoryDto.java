package com.example.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Model that represents Category. Used only for GET requests")
public class MainCategoryDto {

    private int id;
    private String name;
    private String description;
    private Set<MainTaskDto> tasks = new HashSet<>();

}

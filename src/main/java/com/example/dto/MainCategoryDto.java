package com.example.dto;

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
public class MainCategoryDto {

    private int id;

    private String name;

    private String description;

    private Set<MainTaskDto> tasks = new HashSet<>();

}

package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    @NotBlank(message = "{category.name.blank}")
    @NotNull(message = "{category.name.null}")
    private String name;

    @NotBlank(message = "{category.description.blank}")
    @NotNull(message = "{category.description.null}")
    private String description;

}

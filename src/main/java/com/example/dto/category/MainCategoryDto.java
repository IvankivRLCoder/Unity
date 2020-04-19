package com.example.dto.category;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Model that represents Category. Used only for GET requests")
public class MainCategoryDto {

    private int id;
    private String name;
    private String description;

}

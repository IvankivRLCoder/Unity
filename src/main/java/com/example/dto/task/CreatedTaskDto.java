package com.example.dto.task;

import com.example.dto.category.MainCategoryDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Model that represents Task.")
public class CreatedTaskDto {

    private int id;
    private String name;
    private String description;
    private String creationDate;
    private String title;
    private int numberOfParticipants;
    private String status;
    private String priority;
    private MainCategoryDto category;
    private boolean active;

}

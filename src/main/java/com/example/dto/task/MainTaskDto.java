package com.example.dto.task;

import com.example.dto.category.MainCategoryDto;
import com.example.dto.user.MainUserDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Model that represents Task. Used only for GET requests")
public class MainTaskDto {

    private int id;
    private String name;
    private String description;
    private String creationDate;
    private String title;
    private int numberOfParticipants;
    private String status;
    private String priority;
    private MainCategoryDto category;
    private MainUserDto creator;
    private boolean active;

}

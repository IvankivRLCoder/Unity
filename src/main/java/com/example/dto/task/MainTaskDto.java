package com.example.dto.task;

import com.example.dto.category.TaskCategoryDto;
import com.example.dto.user.TaskUserDto;
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
    private TaskCategoryDto category;
    private TaskUserDto creator;
    private boolean active;

}

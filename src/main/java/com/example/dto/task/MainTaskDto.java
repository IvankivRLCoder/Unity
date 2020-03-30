package com.example.dto.task;

import com.example.dto.category.MainCategoryDto;
import com.example.model.Priority;
import com.example.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Set<MainUserTaskDto> userTasks = new HashSet<>();

}

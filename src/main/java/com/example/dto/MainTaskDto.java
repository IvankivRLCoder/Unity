package com.example.dto;

import com.example.model.Priority;
import com.example.model.Status;
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

    private Long id;
    private String name;
    private String description;
    private String creationDate;
    private String title;
    private int numberOfParticipants;
    private Status status;
    private Priority priority;
    private MainCategoryDto category;
    private MainUserDto creator;
    private Set<MainUserTaskDto> userTasks = new HashSet<>();

}

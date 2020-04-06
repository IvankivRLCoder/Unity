package com.example.dto.user;

import com.example.dto.task.TaskDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Model that represents User. Used only for GET requests")
public class MainUserDto {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String trustLevel;
    private Set<TaskDto> createdTasks;
    private boolean blocked;

}

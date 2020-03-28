package com.example.dto;

import com.example.validation.CategoryType;
import com.example.validation.LocalDateType;
import com.example.validation.PriorityType;
import com.example.validation.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

    @NotNull(message = "{task.name.null}")
    @Pattern(regexp = "^[a-zA-z]{2,20}$", message = "{task.name.regex}")
    private String name;

    @NotBlank(message = "{task.description.blank}")
    @NotNull(message = "{task.description.null}")
    private String description;

    @NotNull(message = "{task.creation.null}")
    @NotBlank(message = "{task.creation.blank}")
    @LocalDateType
    private String creationDate;

    @NotNull(message = "{task.title.null}")
    @NotBlank(message = "{task.title.blank}")
    @Size(min = 5, max = 100, message = "{task.title.size}")
    private String title;

    @Size(min = 1, message = "{task.participants.size}")
    private int numberOfParticipants;

    @NotNull(message = "{task.status.null}")
    @NotBlank(message = "{task.status.blank}")
    @StatusType
    private String status;

    @NotNull(message = "{task.priority.null}")
    @NotBlank(message = "{task.priority.blank}")
    @PriorityType
    private String priority;

    @CategoryType
    private MainCategoryDto category;

    private Set<MainUserTaskDto> userTasks = new HashSet<>();

}

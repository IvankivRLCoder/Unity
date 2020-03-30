package com.example.dto;

import com.example.model.Priority;
import com.example.model.Status;
import com.example.validation.CategoryType;
import com.example.validation.LocalDateType;
import com.example.validation.PriorityType;
import com.example.validation.StatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.objects.NativeObject;
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
@ApiModel(description = "Model that represents Task. Used for UPDATE and CREATE requests")
public class TaskDto {

    @NotNull(message = "{task.name.null}")
    @Pattern(regexp = "^[a-zA-z]{7,25}$", message = "{task.name.regex}")
    @ApiModelProperty(example = "Helping elderly", notes = "Minimum 7 characters, maximum 25, not blank")
    private String name;

    @NotBlank(message = "{task.description.blank}")
    @NotNull(message = "{task.description.null}")
    @Pattern(regexp = "^[a-zA-z]{15,150}$")
    @ApiModelProperty(example = "Helping old granny with some housework", notes = "Minimum 15 characters, maximum 150, not blank")
    private String description;

    @NotNull(message = "{task.creation.null}")
    @NotBlank(message = "{task.creation.blank}")
    @LocalDateType
    @ApiModelProperty(example = "2020-02-02", notes = "Date must be valid")
    private String creationDate;

    @NotNull(message = "{task.title.null}")
    @NotBlank(message = "{task.title.blank}")
    @Size(min = 5, max = 100, message = "{task.title.size}")
    @ApiModelProperty(example = "Some task title", notes = "Not blank")
    private String title;

    @Size(min = 1, max = 100, message = "{task.participants.size}")
    @ApiModelProperty(example = "7")
    private int possibleNumberOfParticipants;

    @NotNull(message = "{task.status.null}")
    @NotBlank(message = "{task.status.blank}")
    @StatusType
    @ApiModelProperty(example = "Active")
    private String status;

    @NotNull(message = "{task.priority.null}")
    @NotBlank(message = "{task.priority.blank}")
    @PriorityType
    @ApiModelProperty(example = "Critical")
    private String priority;

    @CategoryType
    private MainCategoryDto category;

    @ApiModelProperty(notes = "Field from transitive table between User and Task. Used for business logic")
    private Set<MainUserTaskDto> userTasks = new HashSet<>();

}
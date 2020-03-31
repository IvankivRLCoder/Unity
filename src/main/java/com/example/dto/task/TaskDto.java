package com.example.dto.task;

import com.example.dto.category.MainCategoryDto;
import com.example.validation.CategoryType;
import com.example.validation.LocalDateType;
import com.example.validation.PriorityType;
import com.example.validation.StatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Model that represents Task. Used for UPDATE and CREATE requests")
public class TaskDto {

    @NotNull(message = "{task.name.null}")
    @Pattern(regexp = "^[a-z A-z]{7,25}$",  message = "{description.regex}")
    @ApiModelProperty(example = "Helping elderly", notes = "Minimum 7 characters, maximum 25, not blank")
    private String name;

    @NotBlank(message = "{task.description.blank}")
    @NotNull(message = "{task.description.null}")
    @Pattern(regexp = "^[a-z A-z]{10,150}$",  message = "{description.regex}")
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

    @Min(value = 2, message = "{task.participants.minimum}")
    @Max(value = 100, message = "{task.participants.maximum}")
    @ApiModelProperty(example = "7")
    private int possibleNumberOfParticipants;

    @NotNull(message = "{task.status.null}")
    @NotBlank(message = "{task.status.blank}")
    @StatusType
    @ApiModelProperty(example = "ACTIVE")
    private String status;

    @NotNull(message = "{task.priority.null}")
    @NotBlank(message = "{task.priority.blank}")
    @PriorityType
    @ApiModelProperty(example = "CRITICAL")
    private String priority;

    @CategoryType
    private MainCategoryDto category;

}
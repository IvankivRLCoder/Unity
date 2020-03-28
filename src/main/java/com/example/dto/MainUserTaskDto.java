package com.example.dto;

import com.example.validation.LocalDateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainUserTaskDto {

    private MainUserDto user;

    private MainTaskDto task;

    @NotNull(message = "{user.task.creation.null}")
    @NotBlank(message = "{user.task.creation.blank}")
    @LocalDateType
    private String participationDate;

    private String comment;

    @NotNull(message = "{user.task.approved.null}")
    private boolean approved;

}

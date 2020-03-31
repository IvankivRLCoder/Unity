package com.example.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Model that represents Category. Used only for GET requests")
public class MainCategoryDto {

    @NotNull(message = "{category.id.null}")
    @ApiModelProperty(example = "3")
    private int id;

    @NotBlank(message = "{category.name.blank}")
    @NotNull(message = "{category.name.null}")
    @Pattern(regexp = "^[a-z A-z]{2,20}$", message = "{category.name.regex}")
    @ApiModelProperty(example = "Fundraising", notes = "Minimum 2 characters, maximum 20, not blank")
    private String name;

    @NotBlank(message = "{category.description.blank}")
    @NotNull(message = "{category.description.null}")
    @Pattern(regexp = "^[a-z A-z]{10,150}$", message = "{category.description.regex}")
    @ApiModelProperty(example = "Raising funds", notes = "Minimum 10 characters, maximum 150, not blank")
    private String description;

}

package com.example.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Model that represents Login. Used only for GET requests")
public class ReturnLoginDto {
    private int id;
    private String firstName;
    private String apiKey;
}

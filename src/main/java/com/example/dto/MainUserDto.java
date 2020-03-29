package com.example.dto;

import com.example.model.TrustLevel;
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
@ApiModel(description = "Model that represents User. Used only for GET requests")
public class MainUserDto {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String dateOfBirth;
    private TrustLevel trustLevel;
    private boolean blocked;
    private Set<MainTaskUserDto> participatedTasks = new HashSet<>();

}

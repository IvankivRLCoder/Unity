package com.example.dto;

import com.example.model.TrustLevel;
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
public class MainUserDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String phoneNumber;

    private String dateOfBirth;

    private TrustLevel trustLevel;

    private boolean blocked;

    private Set<MainTaskDto> createdTasks = new HashSet<>();

    private Set<MainUserTaskDto> participatedTasks = new HashSet<>();

}

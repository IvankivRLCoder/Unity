package com.example.dto.user;

import com.example.model.TrustLevel;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Model that represents User. Used only for Post requests")
public class RegisteredUserDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String dateOfBirth;
    private TrustLevel trustLevel;
    private boolean blocked;
}

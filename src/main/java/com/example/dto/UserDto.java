package com.example.dto;

import com.example.validation.LocalDateType;
import com.example.validation.TrustLevelType;
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
public class UserDto {

    @NotNull(message = "{user.name.null}")
    @Pattern(regexp = "^[a-zA-z]{2,20}$", message = "{user.name.regex}")
    private String name;

    @NotNull(message = "{user.surname.null}")
    @Pattern(regexp = "^[a-zA-z]{2,20}$", message = "{user.surname.regex}")
    private String surname;

    @NotNull(message = "{user.password.null}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,30}$", message = "{user.password.regex}")
    private String password;

    @NotNull(message = "{user.email.null}")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "{user.email.regex}")
    private String email;

    @Pattern(regexp = "^((\\+38)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "{user.phoneNumber.regex}")
    private String phoneNumber;

    @NotNull(message = "{user.birthday.null}")
    @NotBlank(message = "{user.birthday.blank}")
    @LocalDateType
    private String dateOfBirth;

    @NotNull(message = "{user.trust.level.null}")
    @NotBlank(message = "{user.trust.level.blank}")
    @TrustLevelType
    private String trustLevel;

    @NotNull(message = "{user.blocked.null}")
    private boolean blocked;

}

package com.example.controller;

import com.example.dto.user.AuthDto;
import com.example.dto.user.LoginDto;
import com.example.dto.user.ReturnLoginDto;
import com.example.error.ApiError;
import com.example.service.LoginService;
import com.example.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(path = "/Unity")
@RequiredArgsConstructor
@Api(tags = "Login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "Login user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User log in"),
            @ApiResponse(code = 400, message = "Validation error", response = ApiError.class)
    })
    public ReturnLoginDto register(@Valid @RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }
}

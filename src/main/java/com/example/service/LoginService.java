package com.example.service;

import com.example.dto.user.LoginDto;
import com.example.dto.user.ReturnLoginDto;

public interface LoginService {

    ReturnLoginDto login(LoginDto loginDto);

}

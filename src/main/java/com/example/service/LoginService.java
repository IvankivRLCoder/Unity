package com.example.service;

import com.example.dto.user.LoginDto;

import java.util.HashMap;

public interface LoginService {

    HashMap<String, String> login(LoginDto loginDto);

}

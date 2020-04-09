package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dto.user.LoginDto;
import com.example.dto.user.ReturnLoginDto;
import com.example.error.BadCredentialsException;
import com.example.error.EntityNotFountException;
import com.example.model.User;
import com.example.service.LoginService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserDao userDao;

    private final UserService userService;

    @Override
    public ReturnLoginDto login(LoginDto loginDto) {
        User user;

        try {
            user = getByEmail(loginDto.getEmail());
        } catch(EntityNotFoundException exception) {
            throw new BadCredentialsException("User credentials are incorrect");
        }

        UUID uuid = null;

        if (loginDto.getPassword().equals(userService.decode(user.getPassword()))) {
            boolean checker = true;
            while (checker) {
                uuid = UUID.randomUUID();
                try {
                    userDao.getByApiKey(userService.encode(uuid.toString()));
                } catch (NoResultException | EmptyResultDataAccessException exception) {
                    checker = false;
                }
            }
            user.setApiKey(userService.encode(uuid.toString()));
        }
        user = userDao.update(user);
        ReturnLoginDto returnLoginDto = ReturnLoginDto.builder()
                .id(user.getId())
                .name(user.getFirstName())
                .apiKey(uuid.toString()).build();

        return returnLoginDto;
    }

    private User getByEmail(String email) {
        try {
            return userDao.getByEmail(email);
        } catch (NoResultException | EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("User wa not found with email: " + email);
        }
    }



}

package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dto.login.LoginDto;
import com.example.dto.login.MainLoginDto;
import com.example.error.BadCredentialsException;
import com.example.error.EntityNotFountException;
import com.example.model.User;
import com.example.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserDao userDao;

    private final EncodingService encodingService;

    @Override
    public MainLoginDto login(LoginDto loginDto) {
        User user;

        try {
            user = getByEmail(loginDto.getEmail());
        } catch (EntityNotFountException exception) {
            throw new BadCredentialsException("User credentials are incorrect");
        }

        UUID uuid = null;

        if (loginDto.getPassword().equals(encodingService.decode(user.getPassword()))) {
            boolean checker = true;
            while (checker) {
                uuid = UUID.randomUUID();
                try {
                    userDao.getByApiKey(encodingService.encode(uuid.toString()));
                } catch (NoResultException | EmptyResultDataAccessException exception) {
                    checker = false;
                }
            }
            user.setApiKey(encodingService.encode(uuid.toString()));
        }
        user = userDao.update(user);
        assert uuid != null;
        return MainLoginDto
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .apiKey(uuid.toString()).build();
    }

    private User getByEmail(String email) {
        try {
            return userDao.getByEmail(email);
        } catch (NoResultException | EmptyResultDataAccessException ex) {
            throw new EntityNotFountException("User wa not found with email: " + email);
        }
    }

}
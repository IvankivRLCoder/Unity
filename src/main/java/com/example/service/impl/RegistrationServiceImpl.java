package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dto.user.AuthDto;
import com.example.error.BadCredentialsException;
import com.example.model.User;
import com.example.service.RegistrationService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserDao userDao;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Override
    public void register(AuthDto authDto) {
        try {
            User user = getByEmail(authDto.getEmail());
            if (user != null) {
                throw new BadCredentialsException("User already exists");
            }
        } catch (EntityNotFoundException exception) {
        }
        authDto.setPassword(userService.encode(authDto.getPassword()));
        userDao.save(modelMapper.map(authDto, User.class));
    }

    private User getByEmail(String email) {
        try {
            return userDao.getByEmail(email);
        } catch (NoResultException | EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("User was not found with email: " + email);
        }
    }

}

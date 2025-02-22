package com.belisa.delivery.service.impl;

import com.belisa.delivery.converter.UserConverter;
import com.belisa.delivery.dto.UserDto;
import com.belisa.delivery.repository.UserRepository;
import com.belisa.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserConverter::EntityToDto).collect(Collectors.toList());
    }
}

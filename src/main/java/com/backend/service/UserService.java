package com.backend.service;

import com.backend.data.dto.response.UserDTO;
import com.backend.data.entity.UserEntity;
import com.backend.data.mapstruct.UserMapper;
import com.backend.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<UserDTO> getList() {
        return UserMapper.INSTANCE.toDtoList(userRepository.findAll());
    }

    public Optional<UserEntity> getListByUserId(String userId) {
        return userRepository.findById(userId);
    }
}

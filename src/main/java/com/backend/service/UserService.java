package com.backend.service;

import com.backend.data.dto.request.UserReqDTO;
import com.backend.data.dto.response.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    UserDTO createUser(UserReqDTO userDto);

    UserDTO getUserByUserId(String userId);
    List<UserDTO> getUserByAll();


    UserDTO getUserDetailsByEmail(String userName);
}

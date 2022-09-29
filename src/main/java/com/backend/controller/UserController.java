package com.backend.controller;

import com.backend.data.dto.response.UserDTO;
import com.backend.data.entity.UserEntity;
import com.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/user")
    public String user(){
        return "backend - user";
    }

    @GetMapping("/list")
    public List<UserDTO> getList(){
        return userService.getList();
    }

    @GetMapping("/list/{userId}")
    public Optional<UserEntity> getListByUserId(@PathVariable String userId){
        return userService.getListByUserId(userId);
    }
}

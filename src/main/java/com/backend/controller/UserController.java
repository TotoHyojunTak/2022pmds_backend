package com.backend.controller;

import com.backend.data.dto.request.UserReqDTO;
import com.backend.data.dto.response.UserDTO;
import com.backend.service.UserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value="UserController Swagger", tags = "UserController")
public class UserController {

    private final UserService userService;
    private Environment env;
    @GetMapping("/health_check")
    public String status(){
        return String.format("It's working in User Service on PORT %s", env.getProperty("local.server.port")
                + ", port(local.server.port) = " + env.getProperty("local.server.port")
                + ", port(server.port) = " + env.getProperty("server.port")
                + ", token secret = " + env.getProperty("token.secret")
                + ", token expiration time = " + env.getProperty("token.expiration_time")
        );
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserReqDTO user){
        UserDTO result = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUserId(userId));
    }

    @GetMapping("/feign/list")
    public ResponseEntity<List<Map<String, String>>> getUserListForFeign(){
        List<Map<String, String>> returnValue = new ArrayList<>();

        List<UserDTO> result = userService.getUserByAll();
        result.forEach(e->{

            Map<String, String> temp = new HashMap<>();
            temp.put("email", e.getEmail());
            temp.put("name", e.getName());
            temp.put("userId", e.getUserId());

            returnValue.add(temp);
        });

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}

package com.backend.service.impl;

import com.backend.data.dto.request.UserReqDTO;
import com.backend.data.dto.response.OrderDTO;
import com.backend.data.dto.response.UserDTO;
import com.backend.data.entity.UserEntity;
import com.backend.data.mapstruct.UserMapper;
import com.backend.data.repository.UserRepository;
import com.backend.feign.OrderServiceClient;
import com.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder pwdEncoder;

    OrderServiceClient orderServiceClient;

    @Value("${profiles.now}")
    private String profileNow;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder pwdEncoder, OrderServiceClient orderServiceClient) {
        this.userRepository = userRepository;
        this.pwdEncoder = pwdEncoder;
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null)
            throw new UsernameNotFoundException(username + ": not found");

        return new User(userEntity.getEmail(), userEntity.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }

    @Override
    public UserDTO createUser(UserReqDTO user) {

        UserEntity userEntity = UserMapper.INSTANCE.toEntity(user);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEncryptedPwd(pwdEncoder.encode(user.getPwd( )));
        userRepository.save(userEntity);

        UserEntity result = userRepository.findByUserId(userEntity.getUserId());
        return UserMapper.INSTANCE.toDto(result);
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if(userEntity== null){
            throw new UsernameNotFoundException("user not found");
        }

        UserDTO userDto = UserMapper.INSTANCE.toDto(userEntity);

        // FeignClient Error Decoder 활용하기
        if(profileNow.equals("local")){
            List<OrderDTO> orderList = orderServiceClient.getOrders(userId);
            userDto.setOrders(orderList);
        }


        return userDto;
    }

    @Override
    public List<UserDTO> getUserByAll() {
        List<UserEntity> userList = userRepository.findAll();
        return UserMapper.INSTANCE.toDtoList(userList);
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        return UserMapper.INSTANCE.toDto(userEntity);
    }
}

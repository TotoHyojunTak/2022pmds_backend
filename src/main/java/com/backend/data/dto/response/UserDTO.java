package com.backend.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String userNm;
    private String contactNumber;
    private String email;
    private String address;
    private String jobCode;
    private String ingYn;
}

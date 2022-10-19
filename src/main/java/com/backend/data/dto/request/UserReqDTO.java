package com.backend.data.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class UserReqDTO {
    @NotNull()
    @Size(min = 2)
    @Email
    private String email;

    @NotNull()
    @Size(min = 2)
    private String name;

    @NotNull()
    @Size(min = 8)
    private String pwd;
}

package com.backend.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "tb_user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_nm")
    private String userNm;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserJobEntity> jobList = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserSchoolEntity> schoolList = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserStatusEntity> statusList = new ArrayList<>();
}



package com.backend.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "tb_user_status")
@EntityListeners(AuditingEntityListener.class)
public class UserStatusEntity {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "dept_code")
    private String deptCd;

    @Column(name = "dept_number")
    private String deptNumber;

    @Column(name = "dept_name")
    private String deptName;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

}



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
@Table(name = "tb_user_job")
@EntityListeners(AuditingEntityListener.class)
public class UserJobEntity {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "job_code")
    private String jobCode;

    @Column(name = "ing_yn")
    private String ingYn;

}


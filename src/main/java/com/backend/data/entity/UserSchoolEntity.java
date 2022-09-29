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
@Table(name = "tb_user_school")
@EntityListeners(AuditingEntityListener.class)
public class UserSchoolEntity {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "category")
    private String category;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_major")
    private String schoolMajor;

}


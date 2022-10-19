package com.backend.data.repository;

import com.backend.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String username);

    @Query(value = "select id, email, name, user_id from tb_users where user_id = :userId", nativeQuery = true)
    UserEntity findByUserId(String userId);

}



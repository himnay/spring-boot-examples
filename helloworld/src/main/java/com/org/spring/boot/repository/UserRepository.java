package com.org.spring.boot.repository;

import com.org.spring.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Signature repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    @Query("select u from User u join u.signature where u =:user")
    User findSignatureByUser(@Param("user") User user);

    @Query("select u from User u where u.username =:userName")
    User findSignatureByUser(@Param("userName") String user);


}
package com.microservice.productService.repository;

import com.microservice.productService.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInforRepository extends JpaRepository<UserInfo,Integer> {

    //  @Query("SELECT u FROM UserInfo u WHERE u.name = ?1")
    Optional<UserInfo> findByName(String username);
}

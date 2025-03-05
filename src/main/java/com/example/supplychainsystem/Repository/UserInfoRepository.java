package com.example.supplychainsystem.Repository;

import com.example.supplychainsystem.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUsername(String username);

    @Query(value = "select * from user_info ui where username = ?",nativeQuery = true)
    UserInfo findByEmail(String username);
}

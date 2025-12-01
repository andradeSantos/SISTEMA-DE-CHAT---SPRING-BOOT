package com.dev.chatsystem.domain.repository;

import com.dev.chatsystem.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users save(Users user);
    @Query("SELECT COUNT(u) FROM Users u WHERE u.online = true")
    long countOnlineUsers();
    @Query("SELECT COUNT(*) As total FROM Users ")
    long countUsers();
}

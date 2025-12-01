package com.dev.chatsystem.domain.repository;

import com.dev.chatsystem.domain.entity.Menssage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenssageRepository extends JpaRepository<Menssage, String> {
    @Query("SELECT COUNT(*) As total FROM Menssage")
    long countMenssages();
}

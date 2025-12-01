package com.dev.chatsystem.domain.service;

import com.dev.chatsystem.domain.entity.Users;
import com.dev.chatsystem.domain.repository.UserRepository;
import com.dev.chatsystem.infrastructure.dto.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UsersDTO create(UsersDTO dto) {
        long onlineCount = userRepository.countOnlineUsers();
        if (onlineCount >= 10) {
            throw new RuntimeException("Limite máximo de 10 usuários online atingido.");
        }

        Users user = new Users();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setOnline(true);

        Users saved = userRepository.save(user);

        return new UsersDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getOnline()
        );

    }

    public UsersDTO logOut(Long id) {
        Users user = userRepository.findById(id).
                orElseThrow(() ->  new RuntimeException("Usuário não identificado"));

        user.setOnline(false);
        Users saved = userRepository.save(user);

        return new UsersDTO(
           saved.getId(),
           saved.getName(),
           saved.getEmail(),
           saved.getOnline()
        );
    }

    public List<UsersDTO> listTotal(){
        long usersCount = userRepository.countUsers();
        if(usersCount == 0) {
            throw new RuntimeException("Nenhum usuário cadastrado!");
        }

        return userRepository.findAll()
                .stream()
                .map(u -> new UsersDTO(
                        u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getOnline()
                ))
                .toList();
    }

    public List<UsersDTO> listTotalOnline(){
        long usersCount = userRepository.countOnlineUsers();
        if(usersCount == 0) {
            throw new RuntimeException("Nenhum usuário cadastrado!");
        }

        return userRepository.findAll()
                .stream()
                .filter(u -> u.getOnline().equals(true))
                .map(user -> new UsersDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getOnline()
                ))
                .toList();
    }
}
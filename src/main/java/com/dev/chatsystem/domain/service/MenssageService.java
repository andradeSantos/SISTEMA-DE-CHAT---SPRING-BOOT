package com.dev.chatsystem.domain.service;

import com.dev.chatsystem.domain.entity.Menssage;
import com.dev.chatsystem.domain.entity.Users;
import com.dev.chatsystem.domain.repository.MenssageRepository;
import com.dev.chatsystem.domain.repository.UserRepository;
import com.dev.chatsystem.infrastructure.dto.MsgDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Data
public class MenssageService {
    private final MenssageRepository menssageRepository;
    private final UserRepository userRepository;

    public MsgDTO send(MsgDTO msgDTO, Long id) {
        Menssage menssage = new Menssage();
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não identificado!"));

        menssage.setMessage(msgDTO.message());
        menssage.setUser(user);

        Menssage saved = menssageRepository.save(menssage);

        return new MsgDTO(
           saved.getId(),
           saved.getMessage(),
           saved.getUser().getName()
        );
    }

    public List<MsgDTO> list() {
        long menssageCount = menssageRepository.countMenssages();
        if (menssageCount == 0) {
            throw new RuntimeException("Nenhuma mensagem enviada!");
        }

        return menssageRepository.findAll()
                .stream()
                .map(m -> new MsgDTO(
                        m.getId(),
                        m.getMessage(),
                        m.getUser().getName()
                ))
                .toList();
    }
}

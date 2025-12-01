package com.dev.chatsystem.infrastructure.controller;

import com.dev.chatsystem.domain.service.MenssageService;
import com.dev.chatsystem.infrastructure.dto.MsgDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenssageController {

    private final MenssageService menssageService;

    public MenssageController(MenssageService menssageService) {
        this.menssageService = menssageService;
    }

    @PostMapping("/send/{id}")
    public MsgDTO send(@RequestBody MsgDTO msgDTO, @PathVariable Long id) {
        return menssageService.send(msgDTO, id);
    }

    @GetMapping("/list")
    public List<MsgDTO> list() {
        return menssageService.list();
    }
}

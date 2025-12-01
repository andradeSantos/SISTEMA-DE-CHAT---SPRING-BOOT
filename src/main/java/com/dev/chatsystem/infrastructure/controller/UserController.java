package com.dev.chatsystem.infrastructure.controller;

import com.dev.chatsystem.domain.service.UserService;
import com.dev.chatsystem.infrastructure.dto.UsersDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    /*criarUsuário
    listarUsuárioOnline
    logoutUsuário
    MandarMensagem
    VerMensagemNoGrupo*/

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello() {
        return "Servidor funcionando!";
    }

    @PostMapping("/create")
    public UsersDTO create(@RequestBody UsersDTO user){
        return userService.create(user);
    }

    @PutMapping("/logout/{id}")
    public UsersDTO logout(@PathVariable Long id){
        return userService.logOut(id);
    }

    @GetMapping("/listAll")
    public List<UsersDTO> listAll(){
        return userService.listTotal();
    }

    @GetMapping("/listOnline")
    public List<UsersDTO> listOnline(){
        return userService.listTotalOnline();
    }
}

package com.example.projeto_bancario_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.LoginRequest;
import com.example.projeto_bancario_spring.model.Cliente;
import com.example.projeto_bancario_spring.service.ClienteService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/apilogin")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Cliente entity) {

        try {
            Cliente cliente = clienteService.cadastrar(entity);
            return ResponseEntity.ok(cliente);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public Cliente login(@RequestBody LoginRequest resquet) {
        return clienteService.login(resquet.getLogin(), resquet.getSenha());
    }

}
    




    


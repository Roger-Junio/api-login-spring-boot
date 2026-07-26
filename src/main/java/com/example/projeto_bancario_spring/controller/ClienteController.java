package com.example.projeto_bancario_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.example.projeto_bancario_spring.model.Cliente;
import com.example.projeto_bancario_spring.service.ClienteService;
import org.springframework.web.bind.annotation.RestController;
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
    public Cliente cadastrar(@RequestBody Cliente entity) {
        
        return clienteService.cadastrar(entity);
    }




    
    
    @PostMapping("/login")
    public Cliente login(@RequestBody Cliente cliente) {
        return clienteService.login(cliente.getCpf(), cliente.getSenha());
}




}
    




    


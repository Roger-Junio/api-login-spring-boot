package com.example.projeto_bancario_spring.service;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.exception.ValidacaoException;
import com.example.projeto_bancario_spring.model.Cliente;
import com.example.projeto_bancario_spring.repository.ClienteRepository;


@Service
public class ClienteService {

private final ClienteRepository clienteRepository;

        public ClienteService(ClienteRepository clienteRepository)  {
            this.clienteRepository = clienteRepository;
        }

//CADASTRO ------------
      public Cliente cadastrar(Cliente cliente) {

    Map<String, String> erros = new HashMap<>();

    //Nome
    String nome = cliente.getNomeCompleto().trim();
    if (nome.split("\\s+").length < 2) {
        erros.put("nome", "Informe o nome completo.");
    }

    //CPF
    if (!cliente.getCpf().matches("\\d{11}")) {
        erros.put("cpf", "O CPF deve conter exatamente 11 números.");
    }

    //E-mail
    if (!cliente.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
        erros.put("email", "Informe um e-mail válido.");
    }

    //Senha
    if (cliente.getSenha().length() < 8) {
        erros.put("senha", "A senha deve conter no mínimo 8 caracteres.");
    }

    // Verifica CPF duplicado somente se o CPF já passou na validação
    if (!erros.containsKey("cpf") &&
            clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {

        erros.put("cpf", "CPF já cadastrado.");
    }

    // Verifica e-mail duplicado somente se o e-mail já passou na validação
    if (!erros.containsKey("email") &&
            clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {

        erros.put("email", "E-mail já cadastrado.");
    }
 
    if (!erros.isEmpty()) {
                throw new ValidacaoException(erros);
            }

    return clienteRepository.save(cliente);
}

//LOGIN ----------------------
public Cliente login(String login, String senha) {

        Optional<Cliente> clienteOptional;

        if (login.contains("@")) {
            clienteOptional = clienteRepository.findByEmail(login);
        } else {
            clienteOptional = clienteRepository.findByCpf(login);
        }

        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        Cliente cliente = clienteOptional.get();
        if (!cliente.getSenha().equals(senha)) {
            throw new RuntimeException("Senha inválida.");
        }

        return cliente;
    }

}

package com.example.projeto_bancario_spring.service;


import java.util.Optional;
import org.springframework.stereotype.Service;

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

        //nome
        String nome = cliente.getNomeCompleto().trim();
        if (nome.split("\\s+").length < 2) {
            throw new RuntimeException("Informe o nome completo.");
        }
        //cpf
        if (!cliente.getCpf().matches("\\d{11}")) {
            throw new RuntimeException("O CPF deve conter exatamente 11 números.");
        }
        //senha
        if (cliente.getSenha().length() < 8) {
            throw new RuntimeException("A senha deve conter no mínimo 8 caracteres.");
        }

    Optional<Cliente> clienteCpf = clienteRepository.findByCpf(cliente.getCpf());
    Optional<Cliente> clienteEmail = clienteRepository.findByEmail(cliente.getEmail());

    if (clienteCpf.isPresent()) {
        throw new RuntimeException("Cpf já cadastrado.");
    }

    if (clienteEmail.isPresent()) {
        throw new RuntimeException("E-mail já cadastrado.");
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

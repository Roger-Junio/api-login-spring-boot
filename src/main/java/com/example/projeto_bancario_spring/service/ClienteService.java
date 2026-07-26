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


  public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
  }


  
  public Cliente login(String cpf, String senha) {

    Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);

      if (clienteOptional.isEmpty()) {
          throw new RuntimeException("CPF não encontrado.");
      }

      Cliente cliente = clienteOptional.get();

      if (!cliente.getSenha().equals(senha)) {
          throw new RuntimeException("Senha inválida.");
      }

return cliente;
}


 


}

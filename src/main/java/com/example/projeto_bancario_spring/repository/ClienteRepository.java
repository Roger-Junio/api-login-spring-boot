package com.example.projeto_bancario_spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projeto_bancario_spring.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {  



    //REPOSITORY TEM METODOS HERDADOS NÃO A NECESSIDADE
    //  DE ESCRITA APENAS QUANDO NÃO EXISTE ESSE METODO. 
    // - EXEMPLO DA BUSCA POR EMAIL, AI SIM AGENTE ESCREVE
    //Optional<Usuario> findByEmail(String email); 

    Optional<Cliente>   findByCpf(String cpf);
    Optional<Cliente>  findBySenha(String senha);






}

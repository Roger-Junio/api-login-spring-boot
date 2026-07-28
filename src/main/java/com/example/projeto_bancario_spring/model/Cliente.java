package com.example.projeto_bancario_spring.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        private String nomeCompleto;

        @Column(unique = true)
        private String cpf;

        @Column(unique = true)
        private String email;

        private String senha;

        

        public Cliente() {
    }

    public Long getId() {return id;}

    public String getNomeCompleto() { return nomeCompleto;}
        public void setNomeCompleto(String nomeCompleto) {this.nomeCompleto = nomeCompleto;}

    public String getCpf() {return cpf;}
        public void setCpf(String cpf) {this.cpf = cpf;}

    public String getEmail() {return email;}
        public void setEmail(String email) {this.email = email;}      

    public String getSenha() {return senha;}
        public void setSenha(String senha) {this.senha = senha;}

      
}
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

        private String senha;

        @Column(unique = true)
        private String numeroConta;

        private String agencia;
        private BigDecimal saldo;

   
        public Cliente() {
    }

    public Long getId() {return id;}

    public String getNomeCompleto() { return nomeCompleto;}
        public void setNomeCompleto(String nomeCompleto) {this.nomeCompleto = nomeCompleto;}

    public String getCpf() {return cpf;}
        public void setCpf(String cpf) {this.cpf = cpf;}

    public String getSenha() {return senha;}
        public void setSenha(String senha) {this.senha = senha;}

    public String getNumeroConta() {return numeroConta;}
        public void setNumeroConta(String numeroConta) {this.numeroConta = numeroConta;}

    public String getAgencia() {return agencia;}
        public void setAgencia(String agencia) {this.agencia = agencia;}

    public BigDecimal getSaldo() {return saldo;}
        public void setSaldo(BigDecimal saldo) {this.saldo = saldo;}
}
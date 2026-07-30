package com.example.exception;

import java.util.Map;

public class ValidacaoException extends RuntimeException {

    private final Map<String, String> erros;

    public ValidacaoException(Map<String, String> erros) {
        this.erros = erros;
    }

    public Map<String, String> getErros() {
        return erros;
    }
}
package com.manejo_clientes.prueba_tecnica_generation.exception;

public class CorreoYaExisteException extends RuntimeException{
    public CorreoYaExisteException(String message) {
        super(message);
    }
}
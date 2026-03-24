package com.manejo_clientes.prueba_tecnica_generation.repository;

import com.manejo_clientes.prueba_tecnica_generation.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IclienteRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}

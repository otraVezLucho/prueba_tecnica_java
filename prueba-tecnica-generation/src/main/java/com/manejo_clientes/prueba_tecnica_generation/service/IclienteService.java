package com.manejo_clientes.prueba_tecnica_generation.service;

import com.manejo_clientes.prueba_tecnica_generation.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface IclienteService {
List<Cliente>buscarTodos();
Cliente buscarPorId(Long id);
Cliente guardar(Cliente cliente);

void eliminarPorId(Long id);
Cliente editarCliente(Long id, Cliente clienteActualizado);
Optional<Cliente> buscarPorCorreo(String correo);

boolean existeCorreo(String correo);



}

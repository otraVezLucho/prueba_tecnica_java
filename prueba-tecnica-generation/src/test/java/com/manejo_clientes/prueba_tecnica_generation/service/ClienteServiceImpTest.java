package com.manejo_clientes.prueba_tecnica_generation.service;

import com.manejo_clientes.prueba_tecnica_generation.exception.ClienteNoEncontradoException;
import com.manejo_clientes.prueba_tecnica_generation.exception.CorreoYaExisteException;
import com.manejo_clientes.prueba_tecnica_generation.model.Cliente;
import com.manejo_clientes.prueba_tecnica_generation.repository.IclienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImpTest {

    @Mock
    private IclienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImp clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {

        cliente = new Cliente("Juan", "Perez", "juan.perez@email.com", "3001234567", "clave123456");
    }

    @Test
    @DisplayName("Debe guardar al cliente correctamente")
    void guardarCliente_Exitoso() {
        when(clienteRepository.existsByCorreo(anyString())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.guardar(cliente);

        assertNotNull(resultado);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Debe correr la excepcion si el correo ya existe")
    void guardarCliente_CorreoDuplicado_DebeLanzarExcepcion() {
        when(clienteRepository.existsByCorreo(anyString())).thenReturn(true);

        assertThrows(CorreoYaExisteException.class, () -> clienteService.guardar(cliente));
    }

    @Test
    @DisplayName("Debe actualizar un cliente existente")
    void editarCliente_Exitoso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente datosActualizados = new Cliente("Juan Actualizado", "Perez",
                "juan.perez@email.com", "3001234567", "clave123456");

        Cliente resultado = clienteService.editarCliente(1L, datosActualizados);

        assertNotNull(resultado);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción al actualizar cliente que no existe")
    void editarCliente_NoExiste_DebeLanzarExcepcion() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());



        Cliente datosActualizados = new Cliente("TestNombre", "TestApellido", "test@email.com", "1234567", "pass");

        assertThrows(ClienteNoEncontradoException.class,
                () -> clienteService.editarCliente(99L, datosActualizados));
    }

    @Test
    @DisplayName("Debe buscar cliente por ID")
    void buscarPorId_Exitoso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.buscarPorId(1L);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Debe lanzar una excepcion si cliente no existe por id")
    void buscarPorId_NoExiste_DebeLanzarExcepcion() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ClienteNoEncontradoException.class, () -> clienteService.buscarPorId(99L));
    }
}
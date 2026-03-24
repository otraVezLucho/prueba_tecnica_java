package com.manejo_clientes.prueba_tecnica_generation.service;

import com.manejo_clientes.prueba_tecnica_generation.exception.ClienteNoEncontradoException;
import com.manejo_clientes.prueba_tecnica_generation.exception.CorreoYaExisteException;
import com.manejo_clientes.prueba_tecnica_generation.model.Cliente;
import com.manejo_clientes.prueba_tecnica_generation.repository.IclienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteServiceImp implements IclienteService {

    private final IclienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImp(IclienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    @Transactional
    public Cliente editarCliente(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNoEncontradoException("Cliente con numero de id: " + id + " no encotrado."));

        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setApellido(clienteActualizado.getApellido());
        clienteExistente.setContacto(clienteActualizado.getContacto());
        clienteExistente.setCorreo(clienteActualizado.getCorreo());

        return clienteRepository.save(clienteExistente);

    }

    @Override
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));
    }

    @Override
    @Transactional
    public Cliente guardar(Cliente cliente) {
        if(clienteRepository.existsByCorreo(cliente.getCorreo())){
            throw new CorreoYaExisteException("Correo ya se encuentra registrado ");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNoEncontradoException("Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
    }



    @Override
    public Optional<Cliente> buscarPorCorreo(String correo) {

        return Optional.ofNullable(correo) // -> valido si retorna un opcional empty o con algun valor
                .map(String::trim) // -> controlo los espacios ingresados
                .filter(e -> !e.isEmpty()) // lamda de segunda validacion de resultados vacios
                .map(String::toLowerCase) // convertir a minusculas
                .flatMap(clienteRepository::findByCorreo); //referencia a metodo del repository
    }

    @Override
    public boolean existeCorreo(String correo) {
        return buscarPorCorreo(correo).isPresent();
    }
}

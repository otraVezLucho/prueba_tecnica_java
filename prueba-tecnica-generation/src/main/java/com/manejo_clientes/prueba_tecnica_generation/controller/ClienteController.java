package com.manejo_clientes.prueba_tecnica_generation.controller;

import com.manejo_clientes.prueba_tecnica_generation.model.Cliente;
import com.manejo_clientes.prueba_tecnica_generation.service.IclienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IclienteService clienteService;

    @Autowired
    public ClienteController(IclienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listaClientes(){
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<Cliente> buscarPorCorreo(@PathVariable String correo){
        return clienteService.buscarPorCorreo(correo)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new RuntimeException("No se encontraron usuarios con ese correo electronico: " +correo));
    }
    @GetMapping("/correo/existe/{correo}")
    public ResponseEntity<Boolean> existeCorreo(@PathVariable String correo){
        return ResponseEntity.ok(clienteService.existeCorreo(correo));
    }

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@Valid @RequestBody Cliente cliente){
        Cliente clienteGuardado = clienteService.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Long id, @Valid @RequestBody Cliente clienteActualizado){
        Cliente clienteEditado = clienteService.editarCliente(id,clienteActualizado);
        return ResponseEntity.ok(clienteEditado);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id){
        clienteService.eliminarPorId(id);
        return ResponseEntity.ok("Cliente eliminado exitosamente");
    }
}

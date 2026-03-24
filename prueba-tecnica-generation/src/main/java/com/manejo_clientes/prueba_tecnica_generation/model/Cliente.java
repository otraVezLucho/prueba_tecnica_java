package com.manejo_clientes.prueba_tecnica_generation.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter

@Entity
@Table(name ="clientes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message ="nombre obligatorio")
    @Size(min = 2, max = 50)
    @Setter
    private String nombre;


    @NotBlank(message= "apellido obligatorio")
    @Size(min = 2, max = 50)
    @Setter
    private String apellido;


    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")

    @Setter
    private String correo;



    @NotBlank(message= "contacto obligatorio")
    @Size(min = 7, max = 15)
    @Setter
    private String contacto;



    @NotBlank( message = "contraseña obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Setter(AccessLevel.PRIVATE)
    private String contrasena;


    public Cliente(String nombre, String apellido, String correo, String contacto, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contacto = contacto;
        this.contrasena = contrasena;


    }
}
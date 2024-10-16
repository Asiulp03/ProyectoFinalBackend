package com.playtown.dominio.registros;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Registro")
@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nombreUsuario;
    @Column(length = 100, nullable = false, unique = true)
    private String correoElectronico;
    @Column(length = 100, nullable = false)
    private String contrasena;


}

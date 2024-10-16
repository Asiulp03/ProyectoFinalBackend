package com.playtown.dominio.menuPrincipales;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Menu_Entrenador")
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fotoPerfilEntrenador;
    @Column(length = 50, nullable = false)
    private String nombreDelEntrenador;
    @Column(length = 3, nullable = false)
    private int edad;
    @Column(length = 20, nullable = false)
    private int partidos;

}

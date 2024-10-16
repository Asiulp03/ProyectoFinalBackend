package com.playtown.dominio.menuPrincipales;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "Menu_jugador")
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MenuJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fotoPerfilJuagador;
    @Column(length = 50, nullable = false)
    private String nombreDelJugador;
    @Column(length = 3, nullable = false)
    private int edad;
    @Column(length = 10, nullable = false)
    private int goles;
    @Column(length = 10, nullable = false)
    private int asistensias;

}

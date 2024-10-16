package com.playtown.dominio.menuPrincipales;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Mi_equipo")
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MiEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nombreDelEquipo;
    @Column(nullable = false)
    private int partidosJugados;
    @Column(nullable = false)
    private int partidosGanados;
    @Column(nullable = false)
    private int partidosPerdidos;
    @Column(nullable = false)
    private int partidosEmpatados;
    @Column(nullable = false)
    private int golesAFavor;
    @Column(nullable = false)
    private int golesEnContra;
}

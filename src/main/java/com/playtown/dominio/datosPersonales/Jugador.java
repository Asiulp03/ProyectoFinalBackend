package com.playtown.dominio.datosPersonales;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@Entity
@Table(name = "Jugador")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nombreJugador;
    @Column(length = 18, nullable = false)
    private LocalDate fechaDeNacimiento;
    @Column(length = 100, nullable = false, unique = true)
    private String correoElectronico;
    @Column(length = 50, nullable = false)
    private String club;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Jugador.posicion porsiciones;

    public enum posicion {
        Delantero, MedioCampo, Defensor, Portero
    }
}

package com.playtown.dominio.datosPersonales;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "Directivo")
@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Directivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nombreDirectivo;
    @Column(length = 18, nullable = false)
    private LocalDate fechaDeNacimiento;
    @Column(length = 100, nullable = false, unique = true)
    private String correoElectronico;
    @Column(length = 10, nullable = false)
    private Long telefono;
}

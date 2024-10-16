package com.playtown.dominio.menuPrincipales;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Menu_directivo")
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MenuDirectivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fotoPerfilDirectivo;
    @Column(length = 50, nullable = false)
    private String nombreDelDirectivo;
    @Column(length = 3, nullable = false)
    private int edad;
    @Column(length = 50, nullable = false)
    private String torneos;


}

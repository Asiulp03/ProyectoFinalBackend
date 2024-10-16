
package com.playtown.dominio.registros;

import com.playtown.dominio.login.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Registro_extra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fotoPerfilPath;

    @Column(nullable = false)
    private String nombreCompleto;

    @Column(nullable = false)
    private LocalDate fechaDeNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Usuario.Rol rol;


}

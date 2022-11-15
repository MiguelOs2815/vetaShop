package co.edu.uniquindio.veterinaria.entidades;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AfiliacionMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    private Cliente cliente;

    @Column(nullable = false)
    private String medioPago;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @ManyToOne
    private Mascota mascota;

    @ManyToOne
    private Afiliacion afiliacion;
}

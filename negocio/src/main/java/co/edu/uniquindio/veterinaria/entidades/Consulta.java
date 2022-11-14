package co.edu.uniquindio.veterinaria.entidades;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consulta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String tipo_consulta;

    private LocalDate fecha;

    private String descripcion;

    @Nullable
    private String metodoPago;

    @ManyToOne
    private Mascota mascota;

  /*  @Nullable
    @ToString.Exclude
    @ManyToOne
    private Historial historial;

   */


    public Consulta(String tipo_consulta, LocalDate fecha, String descripcion, String metodoPago) {
        this.tipo_consulta = tipo_consulta;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.metodoPago = metodoPago;
    }
}

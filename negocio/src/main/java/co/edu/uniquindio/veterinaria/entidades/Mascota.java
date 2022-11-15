package co.edu.uniquindio.veterinaria.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mascota implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String nombre;

    private String edad;

    private TipoMascotas tipoMascotas;

    private String raza;

    @ToString.Exclude
    @ManyToOne
    private Cliente cliente ;

    @OneToMany(mappedBy = "mascota")
    private List<Consulta> consultas;

    @OneToOne(mappedBy = "mascota")
    private Afiliacion afiliacion;

    /*@ToString.Exclude
    @OneToOne(mappedBy = "mascota")
    private Historial historial;

     */

    public Mascota(String nombre, String edad, TipoMascotas tipoMascotas, String raza) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipoMascotas = tipoMascotas;
        this.raza = raza;
    }
}

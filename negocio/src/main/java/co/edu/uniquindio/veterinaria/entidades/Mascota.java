package co.edu.uniquindio.veterinaria.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
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

    @OneToMany(mappedBy = "producto" ,cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    @OneToMany(mappedBy = "mascota" ,cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<AfiliacionMascota> afiliacionMascotas;

    /*@ToString.Exclude
    @OneToOne(mappedBy = "mascota")
    private Historial historial;

     */


}

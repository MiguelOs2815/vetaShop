package co.edu.uniquindio.veterinaria.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Afiliacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private String nombrePublicacion;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Column(nullable = false, length = 100)
    private List<AtencionMascotas> atencionMascotas;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Column(nullable = false, length = 100)
    private List<Asistencia> asistenciaMascotas;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Column(nullable = false, length = 100)
    private List<TipoMascotas> tipoMascotas;

    @Positive
    private Double precioMensual;

    @Column(nullable = false, length = 300)
    private String descripcion;

    @OneToMany(mappedBy = "afiliacion" ,cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<AfiliacionMascota> afiliacionMascotas;

    @Positive
    private Double precioAnual;

    @ManyToOne
    private Cliente usuario;

    @OneToOne
    private Mascota mascota;


}

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

    private String peso;

    private String raza;

    @ToString.Exclude
    @ManyToOne
    private Cliente cliente ;

    @OneToMany(mappedBy = "mascota")
    private List<Consulta> consultas;

    /*@ToString.Exclude
    @OneToOne(mappedBy = "mascota")
    private Historial historial;

     */


    public Mascota(Integer codigo, String nombre, String edad, String peso, String raza) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.raza = raza;
    }
}

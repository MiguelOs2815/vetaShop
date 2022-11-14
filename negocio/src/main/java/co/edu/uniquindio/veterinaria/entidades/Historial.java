package co.edu.uniquindio.veterinaria.entidades;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Historial implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    /*
    @ToString.Exclude
    @OneToOne
    private Mascota mascota;

    @Nullable
    @ToString.Exclude
    @OneToMany(mappedBy = "historial")
    private List<Consulta> consultas;



     */
}

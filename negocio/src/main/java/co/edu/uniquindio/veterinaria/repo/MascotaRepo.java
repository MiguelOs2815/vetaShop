package co.edu.uniquindio.veterinaria.repo;

import co.edu.uniquindio.veterinaria.entidades.Consulta;
import co.edu.uniquindio.veterinaria.entidades.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepo extends JpaRepository<Mascota,Integer> {

    @Query("select consulta from Consulta consulta where consulta.mascota.cliente.codigo = :codigo")
    List<Consulta> obtenerConsultasMascotas(String codigo);


    @Query("select mascota from Mascota mascota where mascota.cliente.codigo = :codigo")
    List<Mascota> obtenerMascotasCliente(String codigo);
}

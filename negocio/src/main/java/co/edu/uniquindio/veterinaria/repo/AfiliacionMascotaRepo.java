package co.edu.uniquindio.veterinaria.repo;

import co.edu.uniquindio.veterinaria.entidades.Afiliacion;
import co.edu.uniquindio.veterinaria.entidades.AfiliacionMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfiliacionMascotaRepo extends JpaRepository<AfiliacionMascota,Integer> {
}

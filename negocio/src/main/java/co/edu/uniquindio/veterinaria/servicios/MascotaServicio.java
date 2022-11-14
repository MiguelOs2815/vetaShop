package co.edu.uniquindio.veterinaria.servicios;

import co.edu.uniquindio.veterinaria.entidades.*;

import java.util.List;

public interface MascotaServicio {


    Mascota publicarMascota(Mascota mascota) throws Exception;

    void actualizarMascota(Mascota mascota)throws Exception;


    void eliminarMascota(Integer codigo) throws Exception;

    Mascota obtenerMascota(Integer codigo) throws Exception;

    List<Mascota> listarTodosMascotas();

    List<TipoMascotas> listarMascotas();
}

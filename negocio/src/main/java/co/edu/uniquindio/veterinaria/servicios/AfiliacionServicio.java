package co.edu.uniquindio.veterinaria.servicios;

import co.edu.uniquindio.veterinaria.entidades.*;

import java.util.List;

public interface AfiliacionServicio {

    Afiliacion publicarAfiliacion(Afiliacion afiliacion) throws Exception;

    void actualizarAfiliacion(Afiliacion afiliacion)throws Exception;


    void eliminarAfiliacion(Integer codigo) throws Exception;

    Afiliacion obtenerAfiliacion(Integer codigo) throws Exception;

    AfiliacionMascota afiliarMascota()throws Exception;

    List<Afiliacion> listarTodosAfiliacion();

    List<AtencionMascotas> listarAtencionMascotas();

    List<Asistencia> listarAsistencias();

    List<TipoMascotas> listarMascotas();
}

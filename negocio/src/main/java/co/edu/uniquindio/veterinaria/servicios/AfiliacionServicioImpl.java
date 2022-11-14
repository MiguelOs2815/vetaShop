package co.edu.uniquindio.veterinaria.servicios;

import co.edu.uniquindio.veterinaria.entidades.*;
import co.edu.uniquindio.veterinaria.repo.AfiliacionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AfiliacionServicioImpl implements AfiliacionServicio{

    @Autowired
    private AfiliacionRepo afiliacionRepo;

    @Override
    public Afiliacion publicarAfiliacion(Afiliacion afiliacion) throws Exception {
        try {
            return afiliacionRepo.save(afiliacion);
        }catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }

    @Override
    public void actualizarAfiliacion(Afiliacion afiliacion) throws Exception {

    }

    @Override
    public void eliminarAfiliacion(Integer codigo) throws Exception {
        Optional<Afiliacion> afiliacion = afiliacionRepo.findById(codigo);

        if(afiliacion.isEmpty()){
            throw new Exception("El codigo de la afiliacion no existe");
        }

        afiliacionRepo.deleteById(codigo);
    }

    @Override
    public Afiliacion obtenerAfiliacion(Integer codigo) throws Exception {
        return afiliacionRepo.findById(codigo).orElseThrow( ()-> new Exception("La afilicion no existe"));
    }

    @Override
    public List<Afiliacion> listarTodosAfiliacion() {
        return afiliacionRepo.findAll();
    }

    @Override
    public List<AtencionMascotas> listarAtencionMascotas() {
        return Arrays.asList(AtencionMascotas.values());
    }

    @Override
    public List<Asistencia> listarAsistencias() {
        return Arrays.asList(Asistencia.values());
    }

    @Override
    public List<TipoMascotas> listarMascotas() {
        return Arrays.asList(TipoMascotas.values());
    }
}

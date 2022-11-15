package co.edu.uniquindio.veterinaria.servicios;

import co.edu.uniquindio.veterinaria.entidades.Afiliacion;
import co.edu.uniquindio.veterinaria.entidades.Mascota;
import co.edu.uniquindio.veterinaria.entidades.TipoMascotas;
import co.edu.uniquindio.veterinaria.repo.MascotaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaServivioImpl implements MascotaServicio{

    @Autowired
    private MascotaRepo mascotaRepo;

    @Override
    public Mascota publicarMascota(Mascota mascota) throws Exception {
        try {
            return mascotaRepo.save(mascota);
        }catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }

    @Override
    public void actualizarMascota(Mascota mascota) throws Exception {

    }

    @Override
    public void eliminarMascota(Integer codigo) throws Exception {
        Optional<Mascota> mascota = mascotaRepo.findById(codigo);

        if(mascota.isEmpty()){
            throw new Exception("El codigo de la amascota no existe");
        }

        mascotaRepo.deleteById(codigo);
    }

    @Override
    public Mascota obtenerMascota(Integer codigo) throws Exception {
        return mascotaRepo.findById(codigo).orElseThrow( ()-> new Exception("La mascota no existe"));
    }

    @Override
    public List<Mascota> listarTodosMascotas() {
        return mascotaRepo.findAll();
    }

    @Override
    public List<TipoMascotas> listarMascotas() {
        return Arrays.asList(TipoMascotas.values());
    }
}

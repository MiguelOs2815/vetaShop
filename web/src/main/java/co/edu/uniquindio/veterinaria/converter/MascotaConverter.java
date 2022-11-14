package co.edu.uniquindio.veterinaria.converter;

import co.edu.uniquindio.veterinaria.entidades.Mascota;
import co.edu.uniquindio.veterinaria.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class MascotaConverter implements Converter<Mascota> {

    @Autowired
    private ClienteServicio clienteServicio;

    @Override
    public Mascota getAsObject(FacesContext context, UIComponent component, String value) {

        Mascota mascota ;
        try {
            mascota = clienteServicio.obtenerMascota(Integer.parseInt(value));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return mascota;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Mascota value) {
        if(value != null){
            return ""+value.getCodigo();
        }
        return "";
    }
}

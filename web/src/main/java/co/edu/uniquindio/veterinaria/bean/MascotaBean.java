package co.edu.uniquindio.veterinaria.bean;

import co.edu.uniquindio.veterinaria.entidades.*;
import co.edu.uniquindio.veterinaria.servicios.AfiliacionServicio;
import co.edu.uniquindio.veterinaria.servicios.MascotaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class MascotaBean implements Serializable {

    @Getter
    @Setter
    private Mascota mascota;

    @Autowired
    private MascotaServicio mascotaServicio;

    @Value("src/main/webapp/uploads")
    private String urlUploads;

    @Getter @Setter
    private List<TipoMascotas> tipoMascotas;


    @PostConstruct
    public void inicializar(){
        mascota = new Mascota();
        tipoMascotas = mascotaServicio.listarMascotas();
    }

    public void crearMascota(){
        try {

            mascotaServicio.publicarMascota(mascota);

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Mascota creada exitosamente");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean",msg);
        }

    }

}

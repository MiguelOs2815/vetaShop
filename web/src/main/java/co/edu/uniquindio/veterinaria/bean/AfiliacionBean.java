package co.edu.uniquindio.veterinaria.bean;

import co.edu.uniquindio.veterinaria.entidades.*;
import co.edu.uniquindio.veterinaria.servicios.AfiliacionServicio;
import co.edu.uniquindio.veterinaria.servicios.ProductoServicio;
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
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AfiliacionBean implements Serializable {

    @Getter
    @Setter
    private Afiliacion afiliacion;

    @Autowired
    private AfiliacionServicio afiliacionServicio;

    @Value("src/main/webapp/uploads")
    private String urlUploads;

    @Getter @Setter
    private List<AtencionMascotas> atencionMascotas;

    @Getter @Setter
    private List<TipoMascotas> tipoMascotas;

    @Getter @Setter
    private List<Asistencia> asistencias;
    @PostConstruct
    public void inicializar(){
        afiliacion = new Afiliacion();
        atencionMascotas = afiliacionServicio.listarAtencionMascotas();
        asistencias = afiliacionServicio.listarAsistencias();
        tipoMascotas = afiliacionServicio.listarMascotas();
    }

    public void crearAfiliacion(){
        try {

           afiliacionServicio.publicarAfiliacion(afiliacion);

           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Afiliacion creada exitosamente");
           FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean",msg);
        }

    }
}

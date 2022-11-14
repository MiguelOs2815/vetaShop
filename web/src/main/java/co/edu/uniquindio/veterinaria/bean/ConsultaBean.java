package co.edu.uniquindio.veterinaria.bean;

import co.edu.uniquindio.veterinaria.entidades.Consulta;
import co.edu.uniquindio.veterinaria.entidades.Historial;
import co.edu.uniquindio.veterinaria.entidades.Mascota;
import co.edu.uniquindio.veterinaria.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

@Component
@ViewScoped
public class ConsultaBean implements Serializable {

    @Getter @Setter
    private Consulta consulta;

    @Getter @Setter
    private Mascota mascota;

    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void init(){
        consulta = new Consulta();
        mascota = new Mascota();
    }


    public void registrarConsulta(){

        try {

                mascota = clienteServicio.obtenerMascota(11);
                consulta.setMascota(mascota);
                clienteServicio.crearConsulta(consulta);
                consulta=new Consulta();
                mascota= new Mascota();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registrar_consulta", facesMessage);

        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registrar_consulta", facesMessage);
        }
    }
}

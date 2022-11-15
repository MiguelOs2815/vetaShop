package co.edu.uniquindio.veterinaria.bean;

import co.edu.uniquindio.veterinaria.entidades.Afiliacion;
import co.edu.uniquindio.veterinaria.servicios.AfiliacionServicio;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped

public class ListaAfiliacionesBean implements Serializable {

    @Autowired
    private AfiliacionServicio afiliacionServicio;

    @Getter @Setter
    private List<Afiliacion> afiliaciones;

    @PostConstruct
    public void inicializar(){
        this.afiliaciones= afiliacionServicio.listarTodosAfiliacion();
    }
    public String irACompra(String id){
        return "/compraAfiliacion?faces-redirect=true&amp;afiliacion="+id;
    }
}

package co.edu.uniquindio.veterinaria.bean;

import co.edu.uniquindio.veterinaria.entidades.Afiliacion;
import co.edu.uniquindio.veterinaria.entidades.Cliente;
import co.edu.uniquindio.veterinaria.entidades.Comentario;
import co.edu.uniquindio.veterinaria.entidades.Producto;
import co.edu.uniquindio.veterinaria.servicios.AfiliacionServicio;
import co.edu.uniquindio.veterinaria.servicios.ClienteServicio;
import co.edu.uniquindio.veterinaria.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class CompraAfiliacionBean {
    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private AfiliacionServicio afiliacionServicio;

    @Value("#{param['afiliacion']}")
    private String codigoAfiliacion;

    @Getter
    @Setter
    private Afiliacion afiliacion;


    @Value("#{seguridadBean.usuarioSesion}")
    private Cliente usuarioSesion;

    @PostConstruct
    public void inicializar(){
        if(codigoAfiliacion!=null && !codigoAfiliacion.isEmpty()){
            try{
                Integer codigo = Integer.parseInt(codigoAfiliacion);
                afiliacion = afiliacionServicio.obtenerAfiliacion(codigo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

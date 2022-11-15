package co.edu.uniquindio.veterinaria.bean;

import co.edu.uniquindio.veterinaria.converter.MascotaConverter;
import co.edu.uniquindio.veterinaria.dto.ProductoCarrito;
import co.edu.uniquindio.veterinaria.entidades.*;
import co.edu.uniquindio.veterinaria.servicios.AdministradorServicio;
import co.edu.uniquindio.veterinaria.servicios.AfiliacionServicio;
import co.edu.uniquindio.veterinaria.servicios.ClienteServicio;
import co.edu.uniquindio.veterinaria.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter
    @Setter
    private boolean autenticado, autenticadoAdmin;

    @Getter @Setter
    private String email,password;

    @Autowired
    private ClienteServicio usuarioServicio;

    @Autowired
    private AfiliacionServicio afiliacionServicio;

    @Getter @Setter
    private Cliente usuarioSesion;

    @Getter @Setter
    private Administrador administradorSesion;


    @Setter @Getter
    private String medioPago ;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private AdministradorServicio adminServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private Double subtotal;

    //------------AFILIACION---------------------------

    @Getter @Setter
    private AfiliacionMascota afiliacionMascota;
    //------------CONSULTA---------------------------

    @Getter @Setter
    private Consulta consulta;

    @Getter @Setter
    private Mascota mascota;

    @Getter @Setter
    private List<Consulta> consultasUsuario;

    @Getter @Setter
    private List<Mascota> mascotasUsuario;

    //----------------------------------------------



    @PostConstruct
    public void inicializar(){
        this.subtotal = 0.0;
        this.productosCarrito = new ArrayList<>();
        this.consultasUsuario = new ArrayList<>();
        consulta = new Consulta();
        mascota = new Mascota();

    }

    public String iniciarSesion(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {

                usuarioSesion=usuarioServicio.login(email,password);
                autenticado=true;
                consultasUsuario = usuarioServicio.obtenerConsultas(usuarioSesion.getCodigo());
                mascotasUsuario = usuarioServicio.obtenerMascotas(usuarioSesion.getCodigo());
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean",fm);
            }
        }
        return null;

    }
    public String iniciarSesionAdmin(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                administradorSesion= adminServicio.logInAdmin(email,password);
                autenticadoAdmin= true;
                return "/index?faces-redirect=true";
            }catch (Exception e){
                FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-admin",fm);
            }
        }
        return null;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void agregarAlCarrito(Integer id, Double precio, String nombre, String imagen){
        ProductoCarrito pc = new ProductoCarrito(id, nombre,imagen,precio,1);
        if(!productosCarrito.contains(pc)){
            productosCarrito.add(pc);
            subtotal+=precio;
            FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart",fm);

        }

    }


    public void eliminarDelCarrito(int indice){
        subtotal-=productosCarrito.get(indice).getPrecio();
        productosCarrito.remove(indice);
    }

    public void actualizarSubtotal(){
        subtotal=0.0;
        for(ProductoCarrito p : productosCarrito){
            subtotal+= p.getPrecio()*p.getUnidades();
        }
    }
    public int calcularMaximoUnidades(int indice){

        Producto producto = null;
        try {
            producto = productoServicio.obtenerProducto(productosCarrito.get(indice).getId());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto.getUnidades();
    }

    public void comprar(){
        if(usuarioSesion!=null && !productosCarrito.isEmpty()) {
            try {
                System.out.println(medioPago);
                productoServicio.comprarProductos(usuarioSesion, productosCarrito, medioPago);
                productosCarrito.clear();
                subtotal=0.0;
                FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","La compra se realizo correctamente");
                FacesContext.getCurrentInstance().addMessage("compra-msj",fm);
            } catch (Exception e) {
                FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj",fm);
            }
        }
    }

    public boolean mostrarLog(){
        if(!autenticadoAdmin&&!autenticado){
            return true;
        }else{
            return false;
        }
    }

//    public void recuperarContrasena(){
//        if(email!=null){
//            try {
//                usuarioServicio.recuperarContrasena(usuarioServicio.obtenerPorEmail(email));
//                FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","El correo de recuperacion se envio correctamente");
//                FacesContext.getCurrentInstance().addMessage("rec-bean",fm);
//            } catch (Exception e) {
//                FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
//                FacesContext.getCurrentInstance().addMessage("rec-bean",fm);
//            }
//
//        }
//
//    }






    //------------------------------------CONSULTA--------------------------------------------


    public void registrarConsulta(){

        try {

            usuarioServicio.crearConsulta(consulta);
            consultasUsuario.add(consulta);
            consulta=new Consulta();
            mascota= new Mascota();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("mensaje_registrar_consulta", facesMessage);

        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registrar_consulta", facesMessage);
        }
    }

    public void comprarAfiliacion(Afiliacion afiliacionParam){


        try {
            Afiliacion afiliacion = afiliacionParam;

            usuarioServicio.afiliarMascota();


            mascota= new Mascota();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("mensaje_registrar_consulta", facesMessage);

        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registrar_consulta", facesMessage);
        }
    }

}
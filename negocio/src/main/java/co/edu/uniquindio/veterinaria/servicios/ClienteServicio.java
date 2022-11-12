package co.edu.uniquindio.veterinaria.servicios;

import co.edu.uniquindio.veterinaria.entidades.Cliente;
import co.edu.uniquindio.veterinaria.entidades.Consulta;
import co.edu.uniquindio.veterinaria.entidades.Mascota;
import co.edu.uniquindio.veterinaria.entidades.Producto;

import java.util.List;

public interface ClienteServicio {

    Cliente registrarUsuario(Cliente u) throws Exception;

    Cliente actualizarUsuario(Cliente u) throws Exception;

    void eliminarUsuario(String codigo)throws Exception;

    List<Cliente> listarUsuario();

//    List<Producto> listarFavoritos(String email) throws Exception;

    Cliente obtenerUsuario(String codigo) throws Exception;

    Cliente login(String email, String password) throws Exception;

    Cliente obtenerPorEmail(String email) throws Exception;

//    void recuperarContrasena(Cliente usuario);



    //-------------------------Consulta---------------------------

    public Consulta crearConsulta(Consulta consulta) throws Exception;

    public Consulta obtenerConsulta(Integer codigo) throws Exception;

    public Consulta actualizarConsulta(Consulta consulta) throws Exception;

    public void eliminarConsulta(Integer codigo) throws Exception;

    public List<Consulta> listarConsultas();

    public Mascota obtenerMascota(Integer codigo) throws Exception;

    public List<Consulta> obtenerConsultas(String codigo) throws Exception;

    public List<Mascota> obtenerMascotas(String codigo) throws Exception;
}

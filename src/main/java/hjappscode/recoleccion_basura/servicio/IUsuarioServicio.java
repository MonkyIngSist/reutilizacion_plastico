package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Usuario;

import java.util.List;

public interface IUsuarioServicio {

    public List<Usuario> listarUsuarios();

    public Usuario buscarUsuario(int id);

    public void insertarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);
}

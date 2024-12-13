package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Usuario;
import hjappscode.recoleccion_basura.repositorio.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class UsuarioServicio {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public boolean autenticarUsuario(String usuario, String contrasena) {
        String query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.contraseniaUsuario = :contrasena";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(query, Usuario.class);
        typedQuery.setParameter("usuario", usuario);
        typedQuery.setParameter("contrasena", contrasena);
        try {
            Usuario resultado = typedQuery.getSingleResult();
            return resultado != null;
        } catch (Exception e) {
            return false;
        }
    }
}

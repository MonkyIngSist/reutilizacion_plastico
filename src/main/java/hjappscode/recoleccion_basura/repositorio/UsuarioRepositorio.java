package hjappscode.recoleccion_basura.repositorio;

import hjappscode.recoleccion_basura.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
}

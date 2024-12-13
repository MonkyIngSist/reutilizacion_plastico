package hjappscode.recoleccion_basura.repositorio;

import hjappscode.recoleccion_basura.modelo.empresaExterna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface empresaExternaRepositorio extends JpaRepository<empresaExterna, Integer> {
}

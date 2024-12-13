package hjappscode.recoleccion_basura.repositorio;

import hjappscode.recoleccion_basura.modelo.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRepositorio extends JpaRepository<Camion, Integer> {
}

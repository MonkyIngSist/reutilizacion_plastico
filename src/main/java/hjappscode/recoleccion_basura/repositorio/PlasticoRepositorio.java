package hjappscode.recoleccion_basura.repositorio;

import hjappscode.recoleccion_basura.modelo.Plastico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlasticoRepositorio extends JpaRepository<Plastico, Integer> {

}

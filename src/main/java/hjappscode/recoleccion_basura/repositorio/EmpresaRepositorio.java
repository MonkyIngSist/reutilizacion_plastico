package hjappscode.recoleccion_basura.repositorio;

import hjappscode.recoleccion_basura.modelo.Empresa;
import hjappscode.recoleccion_basura.modelo.empresaExterna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, Integer> {



}

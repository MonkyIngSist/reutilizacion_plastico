package hjappscode.recoleccion_basura.repositorio;

import hjappscode.recoleccion_basura.modelo.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface ComprobanteRepositorio extends JpaRepository<Comprobante, Integer> {

    @Query("select s.rucEmpresa, s.nombreEmpresa, s.emailEmpresa from Empresa s JOIN s.empleado where s.idEmpresa =: id")
    List<Object[]> obtenerDetalles(@Param("id")Integer id);


}

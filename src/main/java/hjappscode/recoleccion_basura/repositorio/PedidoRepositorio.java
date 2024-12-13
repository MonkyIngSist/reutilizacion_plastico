package hjappscode.recoleccion_basura.repositorio;

import hjappscode.recoleccion_basura.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {
}

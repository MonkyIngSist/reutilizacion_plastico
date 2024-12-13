package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Pedido;
import hjappscode.recoleccion_basura.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServicio implements IPedidoServicio{

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Override
    public List<Pedido> obtenerPedidos() {
        return pedidoRepositorio.findAll();
    }

    @Override
    public Pedido obtenerPedido(int id) {
        return pedidoRepositorio.findById(id).orElse(null);
    }

    @Override
    public void agregarPedido(Pedido pedido) {
        pedidoRepositorio.save(pedido);
    }

    @Override
    public void eliminarPedido(Pedido pedido) {
        pedidoRepositorio.delete(pedido);
    }
}

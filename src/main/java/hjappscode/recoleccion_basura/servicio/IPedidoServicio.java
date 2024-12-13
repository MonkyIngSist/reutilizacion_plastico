package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Pedido;

import java.util.List;

public interface IPedidoServicio {

    public List<Pedido> obtenerPedidos();

    public Pedido obtenerPedido(int id);

    public void agregarPedido(Pedido pedido);

    public void eliminarPedido(Pedido pedido);

}

package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Comprobante;

import java.util.List;

public interface IComprobanteServicio {

    public List<Comprobante> listarComprobantes();

    public Comprobante buscarComprobante(int codigo);

    public void guardarComprobante(Comprobante comprobante);

    public void eliminarComprobante(Comprobante comprobante);
}

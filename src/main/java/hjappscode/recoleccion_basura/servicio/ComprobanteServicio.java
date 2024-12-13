package hjappscode.recoleccion_basura.servicio;


import hjappscode.recoleccion_basura.modelo.Comprobante;
import hjappscode.recoleccion_basura.repositorio.ComprobanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ComprobanteServicio implements IComprobanteServicio{
    @Autowired
    private ComprobanteRepositorio comprobanteRepositorio;

    @Override
    public List<Comprobante> listarComprobantes() {
        return comprobanteRepositorio.findAll();
    }

    @Override
    public Comprobante buscarComprobante(int codigo) {
        return comprobanteRepositorio.findById(codigo).orElse(null);
    }

    @Override
    public void guardarComprobante(Comprobante comprobante) {
        comprobanteRepositorio.save(comprobante);
    }

    @Override
    public void eliminarComprobante(Comprobante comprobante) {
        comprobanteRepositorio.delete(comprobante);
    }

}

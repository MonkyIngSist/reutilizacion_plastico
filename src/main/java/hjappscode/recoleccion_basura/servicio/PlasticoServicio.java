package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Plastico;
import hjappscode.recoleccion_basura.repositorio.PlasticoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlasticoServicio implements IPlasticoServicio{

    @Autowired
    private PlasticoRepositorio plasticoRepositorio;


    @Override
    public List<Plastico> listarPlasticos() {
        return plasticoRepositorio.findAll();
    }

    @Override
    public Plastico buscarPlasticoPorId(int id) {
        return plasticoRepositorio.findById(id).orElse(null);
    }

    @Override
    public void insertarPlastico(Plastico plastico) {
        plasticoRepositorio.save(plastico);
    }

    @Override
    public void eliminarPlastico(Plastico plastico) {
        plasticoRepositorio.delete(plastico);
    }


}

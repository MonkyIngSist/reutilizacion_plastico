package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Plastico;

import java.util.List;

public interface IPlasticoServicio {

    public List<Plastico> listarPlasticos();

    public Plastico buscarPlasticoPorId(int id);

    public void insertarPlastico(Plastico plastico);

    public void eliminarPlastico(Plastico plastico);

}

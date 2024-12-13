package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Camion;

import java.util.List;

public interface ICamionServicio {

    public List<Camion> listarCamiones();

    public Camion buscarCamion(int id);

    public void insertarCamion(Camion camion);

    public void eliminarCamion(Camion camion);
}

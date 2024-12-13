package hjappscode.recoleccion_basura.servicio;


import hjappscode.recoleccion_basura.modelo.Camion;
import hjappscode.recoleccion_basura.modelo.Empleado;
import hjappscode.recoleccion_basura.repositorio.CamionRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CamionServicio implements ICamionServicio {

    @Autowired
    private CamionRepositorio camionRepositorio;

    @Override
    public List<Camion> listarCamiones() {
        return camionRepositorio.findAll();
    }

    @Override
    public Camion buscarCamion(int id) {
        return camionRepositorio.findById(id).orElse(null);
    }

    @Override
    public void insertarCamion(Camion camion) {
        camionRepositorio.save(camion);
    }

    @Override
    public void eliminarCamion(Camion camion) {
        camionRepositorio.delete(camion);
    }


    @Autowired
    EntityManagerFactory emf = null;


    public List<Empleado> obtenerEmpleados() {
        EntityManager em = emf.createEntityManager();
        List<Empleado> empleados = null;
        try {
            empleados = em.createQuery("SELECT p FROM Empleado p where p.rolEmpleado = 'Camionero'", Empleado.class)
                    .getResultList();
        } finally {
            em.close();
        }
        return empleados;
    }
}

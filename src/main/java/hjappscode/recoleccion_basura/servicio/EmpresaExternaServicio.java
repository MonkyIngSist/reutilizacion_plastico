package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Empresa;
import hjappscode.recoleccion_basura.modelo.empresaExterna;
import hjappscode.recoleccion_basura.repositorio.EmpresaRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaExternaServicio implements IEmpresaExternaServicio{

    @Autowired
    private EntityManagerFactory emf;

    public empresaExterna obtenerEmpresaPorRuc(String rucEmpresa) {
        EntityManager em = emf.createEntityManager();
        empresaExterna empresa = null;
        try {
            // Query para buscar la empresa externa por el ruc
            empresa = em.createQuery("SELECT e FROM empresaExterna e WHERE e.rucExterna = :rucEmpresa", empresaExterna.class)
                    .setParameter("rucEmpresa", rucEmpresa)
                    .getSingleResult(); // Obtener el único resultado
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de excepciones, se puede mejorar según necesidades
        } finally {
            em.close();
        }
        return empresa;
    }

}

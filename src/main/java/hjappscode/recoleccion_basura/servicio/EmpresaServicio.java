    package hjappscode.recoleccion_basura.servicio;

    import hjappscode.recoleccion_basura.modelo.Empresa;
    import hjappscode.recoleccion_basura.repositorio.EmpresaRepositorio;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class EmpresaServicio implements IEmpresaServicio{

        @Autowired
        private EmpresaRepositorio empresaRepositorio;

        @Override
        public List<Empresa> listarEmpresa() {
            return empresaRepositorio.findAll();
        }

        @Override
        public Empresa buscarEmpresaPorId(int id) {
            return empresaRepositorio.findById(id).orElse(null);
        }

        @Override
        public void insertarEmpresa(Empresa empresa) {
            empresaRepositorio.save(empresa);
        }

        @Override
        public void eliminarEmpresa(Empresa empresa) {
            empresaRepositorio.delete(empresa);
        }
    }

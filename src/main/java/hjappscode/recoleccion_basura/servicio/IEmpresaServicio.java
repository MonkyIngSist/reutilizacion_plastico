package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Empresa;

import java.util.List;

public interface IEmpresaServicio {

    public List<Empresa> listarEmpresa();

    public Empresa buscarEmpresaPorId(int id);

    public void insertarEmpresa(Empresa empresa);

    public void eliminarEmpresa(Empresa empresa);
}

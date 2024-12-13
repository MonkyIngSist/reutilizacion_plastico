package hjappscode.recoleccion_basura.servicio;

import hjappscode.recoleccion_basura.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {

    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleado(int id);

    public void insertarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);
}

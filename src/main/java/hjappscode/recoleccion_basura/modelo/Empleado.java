package hjappscode.recoleccion_basura.modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer idEmpleado;
    String nombreEmpleado;
    String apellidoEmpleado;
    String telefonoEmpleado;
    String correoEmpleado;
    String direccionEmpleado;
    String rolEmpleado;
    String estadoEmpleado;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    List<Empresa> empresas = new ArrayList<>();
}

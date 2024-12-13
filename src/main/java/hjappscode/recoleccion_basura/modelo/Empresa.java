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
@AllArgsConstructor
@NoArgsConstructor

public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer idEmpresa;
    String rucEmpresa;
    String nombreEmpresa;
    String direccionEmpresa;
    String telefonoEmpresa;
    String emailEmpresa;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    List<Comprobante> comprabantes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    Empleado empleado;

    @Override
    public String toString() {
        return "Empresa{" +
                "rucEmpresa='" + rucEmpresa + '\'' +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", direccionEmpresa='" + direccionEmpresa + '\'' +
                '}';
    }
}

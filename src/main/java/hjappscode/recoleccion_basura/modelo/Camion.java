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

public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCamion;
    String nombreCamion;
    String placaCamion;
    String marcaCamion;
    String colorCamion;
    Double pesoMaximoCamion;

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    Empleado empleado;

    @OneToMany(mappedBy = "camion", cascade = CascadeType.ALL)
    List<Plastico> plasticos = new ArrayList<>();

}

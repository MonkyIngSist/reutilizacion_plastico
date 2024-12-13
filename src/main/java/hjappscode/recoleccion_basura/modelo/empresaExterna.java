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
@ToString
public class empresaExterna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEmpresaExterna;
    String rucExterna;
    String razonSocial;
    String direccion;
    String telefono;
    String email;

}

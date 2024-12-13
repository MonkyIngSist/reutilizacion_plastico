package hjappscode.recoleccion_basura.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Plastico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer idPlastico;
    String tipoPlastico;
    Date fechaPlastico;
    Double pesoPlastico;
    String observacionesPlastico;
    String lugarPlastico;
    @ManyToOne
    @JoinColumn(name = "idCamion")
    Camion camion;
}

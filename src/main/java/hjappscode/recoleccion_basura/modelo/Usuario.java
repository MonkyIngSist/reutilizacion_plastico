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

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer idUsuario;
    String usuario;
    String contraseniaUsuario;
    String rolUsuario; // RRHH - separadorPlastico - choferCamion - administrador - empleadoBoletas



}

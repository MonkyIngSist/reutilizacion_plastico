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

public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer idComprobante;
    Date fechaComprobante;
    Integer numeroOrdenCompra;

    @ManyToOne
    @JoinColumn(name = "idEmpresa")
    Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    Pedido pedido;



}

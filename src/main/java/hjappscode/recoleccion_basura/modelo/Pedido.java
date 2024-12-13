package hjappscode.recoleccion_basura.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPedido;
    Date fechaPedido;
    Double pesoPedido;
    Double precioPedido;
    Float compradoPedido;
    String tipoPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    List<Comprobante> comprabantes = new ArrayList<>();







}

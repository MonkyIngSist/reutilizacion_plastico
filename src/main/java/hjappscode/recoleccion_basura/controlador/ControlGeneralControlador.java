package hjappscode.recoleccion_basura.controlador;

import hjappscode.recoleccion_basura.ReutilizacionPlasticoApplication;
import hjappscode.recoleccion_basura.modelo.Empleado;
import hjappscode.recoleccion_basura.modelo.Pedido;
import hjappscode.recoleccion_basura.servicio.PedidoServicio;
import jakarta.annotation.PostConstruct;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class ControlGeneralControlador implements Initializable {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private static final Logger logger =
            LoggerFactory.getLogger(ControlGeneralControlador.class);

    @Autowired
    private PedidoServicio pedidoServicio;

    @FXML
    private Button btnVentanaEmpleados;

    @FXML
    public void ventanaEmpleados(ActionEvent actionEvent) {
        try {
            // Cargar el nuevo archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/empleados.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(loader.load());
            // Obtener el Stage actual
            Stage stage = (Stage) btnVentanaEmpleados.getScene().getWindow();
            // Configurar la nueva escena
            stage.setScene(scene);
            stage.setTitle("Empleados");
            stage.show();

        } catch (Exception e) {
            logger.error("Error al cargar la nueva ventana: " + e.getMessage(), e);
            mostrarAlerta("Error", "No se pudo cargar la siguiente ventana.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void ventanaFactura(ActionEvent actionEvent) {
        try {
            // Cargar el nuevo archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/comprobante.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(loader.load());
            // Obtener el Stage actual
            Stage stage = (Stage) btnVentanaEmpleados.getScene().getWindow();
            // Configurar la nueva escena
            stage.setScene(scene);
            stage.setTitle("Empleados");
            stage.show();

        } catch (Exception e) {
            logger.error("Error al cargar la nueva ventana: " + e.getMessage(), e);
            mostrarAlerta("Error", "No se pudo cargar la siguiente ventana.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void ventanaCamion(ActionEvent actionEvent) {
        try {
            // Cargar el nuevo archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/camion.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnVentanaEmpleados.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            logger.error("Error al cargar la nueva ventana: " + e.getMessage(), e);
            mostrarAlerta("Error", "No se pudo cargar la siguiente ventana.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void ventanaPlasticos(ActionEvent actionEvent) {
        try {
            // Cargar el nuevo archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/plastico.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(loader.load());
            // Obtener el Stage actual
            Stage stage = (Stage) btnVentanaEmpleados.getScene().getWindow();
            // Configurar la nueva escena
            stage.setScene(scene);
            stage.setTitle("Empleados");
            stage.show();

        } catch (Exception e) {
            logger.error("Error al cargar la nueva ventana: " + e.getMessage(), e);
            mostrarAlerta("Error", "No se pudo cargar la siguiente ventana.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String error, String s, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.initOwner(btnVentanaEmpleados.getScene().getWindow());
        alert.setTitle("Error");
        alert.setHeaderText(error);
        alert.setContentText(s);
        alert.showAndWait();
    }

    private ObservableList<Pedido> pedidosList = FXCollections.observableArrayList();

    @FXML
    private TableView<Pedido> tblPedido;

    @FXML
    private TableColumn<Pedido, Integer> idPedido;

    @FXML
    private TableColumn<Pedido, Date> fechaPedido;

    @FXML
    private TableColumn<Pedido, String> pesoPedido;

    @FXML
    private TableColumn<Pedido, Double> precioPedido;

    @FXML
    private TableColumn<Pedido, Float> vendidosn;

    @FXML
    private TableColumn<Pedido, String> tipoPedido;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configuraraColumnas();
        listarPedidos();
    }

    private void configuraraColumnas() {
        idPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        tipoPedido.setCellValueFactory(new PropertyValueFactory<>("tipoPedido"));
        fechaPedido.setCellValueFactory(new PropertyValueFactory<>("fechaPedido"));
        pesoPedido.setCellValueFactory(new PropertyValueFactory<>("pesoPedido"));
        precioPedido.setCellValueFactory(new PropertyValueFactory<>("precioPedido"));
        vendidosn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getCompradoPedido()));

    }

    private void listarPedidos() {
        try {
            pedidosList.clear();
            pedidosList.addAll(pedidoServicio.obtenerPedidos());
            tblPedido.setItems(pedidosList);
        } catch (Exception e) {
            logger.error("Error al listar pedidos: " + e.getMessage(), e);
        }
    }


}

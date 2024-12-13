package hjappscode.recoleccion_basura.controlador;

import hjappscode.recoleccion_basura.modelo.Camion;
import hjappscode.recoleccion_basura.modelo.Pedido;
import hjappscode.recoleccion_basura.modelo.Plastico;
import hjappscode.recoleccion_basura.servicio.CamionServicio;
import hjappscode.recoleccion_basura.servicio.PedidoServicio;
import hjappscode.recoleccion_basura.servicio.PlasticoServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class PlasticoControlador implements Initializable {

    @Autowired
    private PlasticoServicio plasticoServicio;

    @Autowired
    private CamionServicio camionServicio;
    @Autowired
    private PedidoServicio pedidoServicio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblPlastico.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnasCamiones();
        configurarColumnasPlastico();
        listarPlasticos();
        listarCamiones();
    }

    @FXML
    private TableView<Plastico> tblPlastico;

    @FXML
    private TableView<Camion> tblCamion;

    @FXML
    private TableColumn<Plastico, Integer> colIdCamion1;

    @FXML
    private TableColumn<Plastico, String> colColorCamion;

    @FXML
    private TableColumn<Plastico, String> colNombreCamion;

    @FXML
    private TableColumn<Plastico, String> colMarcaCamion;

    @FXML
    private TableColumn<Plastico, String> colPlacaCamion;

    @FXML
    private TableColumn<Plastico, Integer> colIdPlastico;

    @FXML
    private TableColumn<Plastico, String> colTipoPlastico;

    @FXML
    private TableColumn<Plastico, Date> colFechaPlastico;

    @FXML
    private TableColumn<Plastico, String> colPesoPlastico;

    @FXML
    private TableColumn<Plastico, String> colObservacionPlastico;

    @FXML
    private TableColumn<Plastico, String> colLugarPlastico;

    @FXML
    private TableColumn<Plastico, String> colIdCamion;

    @FXML
    private TextField txtTipoPlastico;

    @FXML
    private TextField txtPesoPlastico;

    @FXML
    private TextField txtCamion;

    @FXML
    private TextField txtPrecio;

    private ObservableList<Plastico> plasticosList = javafx.collections.FXCollections.observableArrayList();

    private ObservableList<Camion> camionesList = javafx.collections.FXCollections.observableArrayList();

    private void configurarColumnasPlastico() {
        colIdPlastico.setCellValueFactory(new PropertyValueFactory<>("idPlastico"));
        colTipoPlastico.setCellValueFactory(new PropertyValueFactory<>("tipoPlastico"));
        colFechaPlastico.setCellValueFactory(new PropertyValueFactory<>("fechaPlastico"));
        colPesoPlastico.setCellValueFactory(new PropertyValueFactory<>("pesoPlastico"));
        colObservacionPlastico.setCellValueFactory(new PropertyValueFactory<>("observacionesPlastico"));
        colLugarPlastico.setCellValueFactory(new PropertyValueFactory<>("lugarPlastico"));
        colIdCamion.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getCamion() != null ? cellData.getValue().getCamion().getPlacaCamion() : "Sin Placa"
                ));
    }

    private void configurarColumnasCamiones() {
        colIdCamion1.setCellValueFactory(new PropertyValueFactory<>("idCamion"));
        colColorCamion.setCellValueFactory(new PropertyValueFactory<>("colorCamion"));
        colNombreCamion.setCellValueFactory(new PropertyValueFactory<>("nombreCamion"));
        colMarcaCamion.setCellValueFactory(new PropertyValueFactory<>("marcaCamion"));
        colPlacaCamion.setCellValueFactory(new PropertyValueFactory<>("placaCamion"));

    }

    private void listarPlasticos() {
        plasticosList.clear();
        plasticosList.addAll(plasticoServicio.listarPlasticos());
        tblPlastico.setItems(plasticosList);
    }

    public void cargarInformacionPlastico() {
        var plastico = tblPlastico.getSelectionModel().getSelectedItem();
        if (plastico != null) {
            txtTipoPlastico.setText(plastico.getTipoPlastico());
            txtPesoPlastico.setText(String.valueOf(plastico.getPesoPlastico()));
        }
    }

    public void cargarInformacionCamion() {
        var camion = tblCamion.getSelectionModel().getSelectedItem();
        if (camion != null) {
            txtCamion.setText(camion.getPlacaCamion());
        }
    }

    private void listarCamiones() {
        camionesList.clear();
        camionesList.addAll(camionServicio.listarCamiones());
        tblCamion.setItems(camionesList);
    }

    private Integer txtIdPedido;

    private void recolectarDatosFormulario(Pedido pedido) {
        if (colIdCamion != null) {
            pedido.setIdPedido(txtIdPedido);
            pedido.setFechaPedido(new Date());
            pedido.setTipoPedido(txtTipoPlastico.getText());
            pedido.setPesoPedido(Double.parseDouble(txtPesoPlastico.getText()));
            pedido.setPrecioPedido(Double.parseDouble(txtPrecio.getText()));
            pedido.setCompradoPedido(0.0f);
        }
    }

    @FXML
    private void agregarProducto(ActionEvent event) {
        if (txtPrecio.getText().isEmpty()) {
            mostrarAlerta("Error Validacion", "Debe proporcionar todos los datos", Alert.AlertType.ERROR);
            return;
        }
        var pedido = new Pedido();
        recolectarDatosFormulario(pedido);
        pedido.setIdPedido(null);
        pedidoServicio.agregarPedido(pedido);
        mostrarAlerta("Tarea agregada", "La tarea se ha agregado correctamente.", Alert.AlertType.INFORMATION);
        limpiarPedido();
    }

    public void limpiarPedido() {
        txtTipoPlastico.clear();
        txtPesoPlastico.clear();
        txtCamion.clear();
        txtPrecio.clear();
        txtIdPedido = null;
    }

    @FXML
    private Button btnConfirmar;

    private void mostrarAlerta(String error, String s, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.initOwner(btnConfirmar.getScene().getWindow());
        alert.setTitle("Error");
        alert.setHeaderText(error);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private static final Logger logger =
            LoggerFactory.getLogger(ControlGeneralControlador.class);

    @FXML
    private Button btnRetroceder;

    public void regresarGeneral(ActionEvent actionEvent) {
        try {
            // Cargar el nuevo archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/controlGeneral.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(loader.load());
            // Obtener el Stage actual
            Stage stage = (Stage) btnRetroceder.getScene().getWindow();
            // Configurar la nueva escena
            stage.setScene(scene);
            stage.setTitle("Empleados");
            stage.show();

        } catch (Exception e) {
            logger.error("Error al cargar la nueva ventana: " + e.getMessage(), e);
            mostrarAlerta("Error", "No se pudo cargar la siguiente ventana.", Alert.AlertType.ERROR);
        }

    }


}

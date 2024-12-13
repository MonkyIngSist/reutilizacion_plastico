package hjappscode.recoleccion_basura.controlador;

import hjappscode.recoleccion_basura.modelo.Camion;
import hjappscode.recoleccion_basura.modelo.Empleado;
import hjappscode.recoleccion_basura.modelo.Pedido;
import hjappscode.recoleccion_basura.modelo.Plastico;
import hjappscode.recoleccion_basura.servicio.CamionServicio;
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
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CamionControlador implements Initializable {

    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Autowired
    private CamionServicio camionServicio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configuraraColumnas();
        listarCamiones();
        cargarComboBox();
    }

    @FXML
    private Button btnRegresar;

    private static final Logger logger =
            LoggerFactory.getLogger(ControlGeneralControlador.class);

    private void mostrarAlerta(String error, String s, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.initOwner(btnRegresar.getScene().getWindow());
        alert.setTitle("Error");
        alert.setHeaderText(error);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void regresarVentanaPrincipal() {
        try {
            // Cargar el nuevo archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/controlGeneral.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(loader.load());
            // Obtener el Stage actual
            Stage stage = (Stage) btnRegresar.getScene().getWindow();
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
    private TableView<Camion> tblCamion;

    @FXML
    private TableColumn<Camion, Integer> colId;

    @FXML
    private TableColumn<Camion, String> colNombre;

    @FXML
    private TableColumn<Camion, String> colPlaca;

    @FXML
    private TableColumn<Camion, String> colMarca;

    @FXML
    private TableColumn<Camion, String> colColor;

    @FXML
    private TableColumn<Camion, Double> colPesoMax;

    @FXML
    private TableColumn<Camion, String> colEmpleado;

    @FXML
    private TableColumn<Camion, String> colUsuario;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtPesoMax;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox cbxEmpleado;

    private ObservableList<Camion> camionesList = javafx.collections.FXCollections.observableArrayList();

    private void configuraraColumnas() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idCamion"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCamion"));
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placaCamion"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marcaCamion"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("colorCamion"));
        colPesoMax.setCellValueFactory(new PropertyValueFactory<>("pesoMaximoCamion"));
        colEmpleado.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getEmpleado() != null ? cellData.getValue().getEmpleado().getNombreEmpleado() : "Sin Nombre"
                ));
    }

    private void listarCamiones() {
        camionesList.clear();
        camionesList.addAll(camionServicio.listarCamiones());
        tblCamion.setItems(camionesList);
    }

    @FXML
    private void agregarCamion(ActionEvent actionEvent) {
        if (txtMarca.getText().isEmpty()) {
            mostrarAlerta("Error Validacion", "Debe proporcionar todos los datos", Alert.AlertType.ERROR);
            return;
        } else {
            var camion = new Camion();
            recolectarDatosFormulario(camion);
            camion.setIdCamion(null);
            camionServicio.insertarCamion(camion);
            mostrarAlerta("Tarea agregada", "La tarea se ha agregado correctamente.", Alert.AlertType.INFORMATION);
            limpiarPedido();
            listarCamiones();
        }
    }

    private void limpiarPedido() {
        txtNombre.clear();
        txtPlaca.clear();
        txtMarca.clear();
        txtPesoMax.clear();
        txtColor.clear();
    }

    private Integer txtIdCamion;

    private void recolectarDatosFormulario(Camion camion) {
        if (colId != null) ;
        camion.setIdCamion(txtIdCamion);
        camion.setNombreCamion(txtNombre.getText());
        camion.setPlacaCamion(txtPlaca.getText());
        camion.setMarcaCamion(txtMarca.getText());
        camion.setColorCamion(txtColor.getText());
        camion.setPesoMaximoCamion(Double.parseDouble(txtPesoMax.getText()));
        Empleado empleadoSeleccionado = (Empleado) cbxEmpleado.getSelectionModel().getSelectedItem();
        if (empleadoSeleccionado != null) {
            camion.setEmpleado(empleadoSeleccionado);
        } else {
            mostrarAlerta("Error", "Debe seleccionar un empleado válido.", Alert.AlertType.ERROR);
        }
    }


    public void eliminarCamion() {
        var camion = tblCamion.getSelectionModel().getSelectedItem();
        if (camion != null) {
            camionServicio.eliminarCamion(camion);
            mostrarAlerta("Tarea eliminada", "La tarea se ha eliminado correctamente.", Alert.AlertType.INFORMATION);
            limpiarPedido();
            listarCamiones();
        } else {
            mostrarAlerta("Error Validacion", "Debe seleccionar un empleado para eliminarlo", Alert.AlertType.ERROR);
        }
    }

    public void modificarCamion() {
        // Verificar si hay un elemento seleccionado en la tabla
        var camionSeleccionado = tblCamion.getSelectionModel().getSelectedItem();
        if (camionSeleccionado == null) {
            mostrarAlerta("Error Validación", "Debe seleccionar un camión para modificarlo", Alert.AlertType.ERROR);
            return;
        }

        // Validar campos obligatorios
        if (txtNombre.getText().isEmpty() || txtMarca.getText().isEmpty() || txtPlaca.getText().isEmpty() || txtPesoMax.getText().isEmpty()) {
            mostrarAlerta("Error Validación", "Debe proporcionar todos los datos obligatorios", Alert.AlertType.ERROR);
            txtNombre.requestFocus();
            return;
        }

        try {
            // Actualizar datos del camión seleccionado
            recolectarDatosFormulario(camionSeleccionado); // Reutilizar el método para actualizar el objeto
            camionServicio.insertarCamion(camionSeleccionado); // Llamar al método adecuado para modificar

            mostrarAlerta("Camión modificado", "El camión se ha modificado correctamente.", Alert.AlertType.INFORMATION);
            listarCamiones(); // Actualizar la lista en la tabla
            limpiarPedido();
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al intentar modificar el camión: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void cargarComboBox() {
        List<Empleado> empleados = camionServicio.obtenerEmpleados(); // Ahora devuelve List<Empleado>
        cbxEmpleado.getItems().clear();
        cbxEmpleado.getItems().addAll(empleados);

        cbxEmpleado.setConverter(new StringConverter<Empleado>() {
            @Override
            public String toString(Empleado empleado) {
                return empleado != null ? empleado.getNombreEmpleado() : "";
            }

            @Override
            public Empleado fromString(String string) {
                return null; // Este método no se utiliza directamente
            }
        });
    }

    public void cargarInformacionFormulario() {
        var camion = tblCamion.getSelectionModel().getSelectedItem();
        if (camion != null) {
            txtNombre.setText(camion.getNombreCamion());
            txtPlaca.setText(camion.getPlacaCamion());
            txtMarca.setText(camion.getMarcaCamion());
            txtPesoMax.setText(String.valueOf(camion.getPesoMaximoCamion()));
            txtColor.setText(camion.getColorCamion());
            txtIdCamion = camion.getIdCamion();
            cbxEmpleado.getSelectionModel().select(camion.getEmpleado());
        }
    }
}




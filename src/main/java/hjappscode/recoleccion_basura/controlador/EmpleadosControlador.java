package hjappscode.recoleccion_basura.controlador;

import hjappscode.recoleccion_basura.modelo.Empleado;
// import hjappscode.recoleccion_basura.presentacion.ReutilizacionPlasticoFx;
import hjappscode.recoleccion_basura.servicio.EmpleadoServicio;
import jakarta.annotation.PostConstruct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.annotations.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EmpleadosControlador implements Initializable {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @FXML
    public Button btnRegresar;

    private static final Logger logger =
            LoggerFactory.getLogger(ControlGeneralControlador.class);

    @PostConstruct
    public void inizializar() {
        if (empleadoServicio == null) {
            logger.error("EmpleadoServicio no está inyectado correctamente.");
        } else {
            logger.info("EmpleadoServicio inyectado correctamente.");
        }
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

    private void mostrarAlerta(String error, String s, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.initOwner(btnRegresar.getScene().getWindow());
        alert.setTitle("Error");
        alert.setHeaderText(error);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private ComboBox cbxRol;

    @FXML
    private ComboBox cbxEstado;

    @FXML
    private TableView<Empleado> tblEmpleados;

    @FXML
    private TableColumn<Empleado, Integer> idEmpleado;

    @FXML
    private TableColumn<Empleado, String> nombreEmpleado;

    @FXML
    private TableColumn<Empleado, String> apellidosEmpleado;

    @FXML
    private TableColumn<Empleado, String> telefonoEmpleado;

    @FXML
    private TableColumn<Empleado, String> correoEmpleado;

    @FXML
    private TableColumn<Empleado, String> direccionEmpleado;

    @FXML
    private TableColumn<Empleado, String> rolEmpleado;

    @FXML
    private TableColumn<Empleado, String> estadoEmpleado;

    private Integer txtIdEmpleadoInterno;

    private ObservableList<Empleado> empleadosList = FXCollections.observableArrayList();

    private void configuraraColumnas() {
        idEmpleado.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        nombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombreEmpleado"));
        apellidosEmpleado.setCellValueFactory(new PropertyValueFactory<>("apellidoEmpleado"));
        telefonoEmpleado.setCellValueFactory(new PropertyValueFactory<>("telefonoEmpleado"));
        correoEmpleado.setCellValueFactory(new PropertyValueFactory<>("correoEmpleado"));
        direccionEmpleado.setCellValueFactory(new PropertyValueFactory<>("direccionEmpleado"));
        rolEmpleado.setCellValueFactory(new PropertyValueFactory<>("rolEmpleado"));
        estadoEmpleado.setCellValueFactory(new PropertyValueFactory<>("estadoEmpleado"));
    }

    private void listarEmpleados() {
        empleadosList.clear();
        empleadosList.addAll(empleadoServicio.listarEmpleados());
        tblEmpleados.setItems(empleadosList);
    }

    public void agregarEmp() {
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
            mostrarAlerta("Error Validacion", "Debe proporcionar todos los datos", Alert.AlertType.ERROR);
            return;
        } else {
            var empleado = new Empleado();
            recolectarDatosFormulario(empleado);
            empleado.setIdEmpleado(null);
            empleadoServicio.insertarEmpleado(empleado);
            mostrarAlerta("Tarea agregada", "La tarea se ha agregado correctamente.", Alert.AlertType.INFORMATION);
            limpiarEmp();
            listarEmpleados();
        }
    }

    private void recolectarDatosFormulario(Empleado empleado) {

        if (idEmpleado != null) ;
        empleado.setIdEmpleado(txtIdEmpleadoInterno);
        empleado.setNombreEmpleado(txtNombre.getText());
        empleado.setApellidoEmpleado(txtApellido.getText());
        empleado.setTelefonoEmpleado(txtTelefono.getText());
        empleado.setCorreoEmpleado(txtCorreo.getText());
        empleado.setDireccionEmpleado(txtDireccion.getText());
        empleado.setRolEmpleado(cbxRol.getValue().toString());
        empleado.setEstadoEmpleado(cbxEstado.getValue().toString());
    }

    public void eliminarEmp() {
        var empleado = tblEmpleados.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            empleadoServicio.eliminarEmpleado(empleado);
            mostrarAlerta("Tarea eliminada", "La tarea se ha eliminado correctamente.", Alert.AlertType.INFORMATION);
            limpiarEmp();
            listarEmpleados();
        } else {
            mostrarAlerta("Error Validacion", "Debe seleccionar un empleado para eliminarlo", Alert.AlertType.ERROR);
        }
    }

    public void modificarEmp() {
        if (idEmpleado == null) {
            mostrarAlerta("Error Validacion", "Debe seleccionar un empleado para modificarlo", Alert.AlertType.ERROR);
            return;
        }
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
            mostrarAlerta("Error Validacion", "Debe proporcionar los datos", Alert.AlertType.ERROR);
            txtNombre.requestFocus();
            return;
        }
        var empleado = new Empleado();
        recolectarDatosFormulario(empleado);
        empleadoServicio.insertarEmpleado(empleado);
        mostrarAlerta("Tarea modificada", "La tarea se ha modificado correctamente.", Alert.AlertType.INFORMATION);
        listarEmpleados();

    }

    public void limpiarEmp() {
        txtIdEmpleadoInterno = null;
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtDireccion.clear();
        cbxEstado.getSelectionModel().clearSelection();
        cbxEstado.getSelectionModel().clearSelection();
    }

    public void cargarInformacionFormulario() {
        var empleado = tblEmpleados.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            txtIdEmpleadoInterno = empleado.getIdEmpleado();
            txtNombre.setText(empleado.getNombreEmpleado());
            txtApellido.setText(empleado.getApellidoEmpleado());
            txtTelefono.setText(empleado.getTelefonoEmpleado());
            txtCorreo.setText(empleado.getCorreoEmpleado());
            txtDireccion.setText(empleado.getDireccionEmpleado());
            cbxRol.getSelectionModel().select(empleado.getRolEmpleado());
            cbxEstado.getSelectionModel().select(empleado.getEstadoEmpleado());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (empleadoServicio == null) {
            logger.error("EmpleadoServicio no está inyectado correctamente.");
        } else {
            logger.info("EmpleadoServicio inyectado correctamente.");
            tblEmpleados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            configuraraColumnas();
            listarEmpleados();
        }
    }
}

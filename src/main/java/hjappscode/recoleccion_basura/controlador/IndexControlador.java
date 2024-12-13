package hjappscode.recoleccion_basura.controlador;

import hjappscode.recoleccion_basura.ReutilizacionPlasticoApplication;
import hjappscode.recoleccion_basura.servicio.UsuarioServicio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


@Component
public class IndexControlador implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @FXML
    private void handleLogin(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String contrasena = txtPassword.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, ingrese el usuario y la contraseña.", Alert.AlertType.WARNING);
            return;
        }

        boolean autenticado = usuarioServicio.autenticarUsuario(usuario, contrasena);
        if (autenticado) {
            logger.info("Inicio de sesión exitoso para el usuario: " + usuario);
            mostrarAlerta("Éxito", "Inicio de sesión exitoso.", Alert.AlertType.INFORMATION);
            // Cambiar a la siguiente vista o cargar la interfaz principal
            cambiarVentana();
        } else {
            mostrarAlerta("Error de autenticación", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
        }
    }

    private void cambiarVentana() {
        try {
            FXMLLoader loader = new FXMLLoader(ReutilizacionPlasticoApplication.class.getResource("/templates/controlGeneral.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Control General");
            stage.show();

        } catch (Exception e) {
            logger.error("Error al cargar la nueva ventana: " + e.getMessage(), e);
            logger.error("Error al cargar el archivo FXML desde la ruta", e);
            mostrarAlerta("Error", "No se pudo cargar la siguiente ventana.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String error, String s, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.initOwner(txtUsuario.getScene().getWindow());
        alert.setTitle("Error");
        alert.setHeaderText(error);
        alert.setContentText(s);
        alert.showAndWait();
    }
}

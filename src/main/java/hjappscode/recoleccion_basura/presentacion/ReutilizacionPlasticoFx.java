package hjappscode.recoleccion_basura.presentacion;

import hjappscode.recoleccion_basura.ReutilizacionPlasticoApplication;
import hjappscode.recoleccion_basura.servicio.UsuarioServicio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
@SpringBootApplication(scanBasePackages = "hjappscode.recoleccion_basura")
public class ReutilizacionPlasticoFx extends Application {


    private ConfigurableApplicationContext applicationContext;

    public void init(){
        this.applicationContext = new SpringApplicationBuilder(ReutilizacionPlasticoApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ReutilizacionPlasticoApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @Override
    public void stop(){
        applicationContext.close();
        Platform.exit();
    }





}

package hjappscode.recoleccion_basura;

import hjappscode.recoleccion_basura.presentacion.ReutilizacionPlasticoFx;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "hjappscode.recoleccion_basura")
public class ReutilizacionPlasticoApplication {

	public static void main(String[] args) {
	 	// SpringApplication.run(ReutilizacionPlasticoApplication.class, args);
		Application.launch(ReutilizacionPlasticoFx.class, args);
	}

}

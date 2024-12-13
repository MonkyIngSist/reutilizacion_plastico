package hjappscode.recoleccion_basura.controlador;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import hjappscode.recoleccion_basura.modelo.Empresa;
import hjappscode.recoleccion_basura.modelo.Pedido;
import hjappscode.recoleccion_basura.modelo.empresaExterna;
import hjappscode.recoleccion_basura.repositorio.ComprobanteRepositorio;
import hjappscode.recoleccion_basura.servicio.ComprobanteServicio;
import hjappscode.recoleccion_basura.servicio.EmpresaExternaServicio;
import hjappscode.recoleccion_basura.servicio.EmpresaServicio;
import hjappscode.recoleccion_basura.servicio.PedidoServicio;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class ComprobanteControlador implements Initializable {

    @Autowired
    private ComprobanteServicio comprobanteServicio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarEmpresa();
    }

    @FXML
    private TextArea txaFacturaText;

    @FXML
    private Button btnGenerar;

    @FXML
    private TextField txtRucEmpresa;

    @FXML
    private TextField txtIdPedido;

    @FXML
    private TableColumn<Pedido, Integer> colId;

    @FXML
    private TableColumn<Pedido, String> colTipoPedido;

    @FXML
    private TableColumn<Pedido, Double> colPeso;

    @FXML
    private TableColumn<Pedido, Double> colPrecio;

    @FXML
    private TableColumn<Pedido, String> colCamion;

    @Autowired
    private PedidoServicio pedidoServicio;

    private Empresa empresa;

    @Autowired
    private EmpresaServicio empresaServicio;

    private ObservableList<Pedido> pedidosList = FXCollections.observableArrayList();

    @FXML
    private TableView<Pedido> tblPedido;

    private static final Logger logger =
            LoggerFactory.getLogger(ControlGeneralControlador.class);

    private void listarPedidos() {
        try {
            pedidosList.clear();
            pedidosList.addAll(pedidoServicio.obtenerPedido(Integer.parseInt(txtIdPedido.getText())));
            tblPedido.setItems(pedidosList);
        } catch (Exception e) {
            logger.error("Error al listar pedidos: " + e.getMessage(), e);
        }
    }

    private void configuraraColumnas() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        colTipoPedido.setCellValueFactory(new PropertyValueFactory<>("tipoPedido"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("pesoPedido"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPedido"));
        colCamion.setCellValueFactory(new PropertyValueFactory<>("precioPedido"));

    }

    @FXML
    private Label lblRUC;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblDireccion;


    private void listarEmpresa() {
        try {
            empresa = empresaServicio.buscarEmpresaPorId(1);
            System.out.println("Empresa obtenida: " + empresa);

            // Verificar que la empresa no sea nula antes de acceder a sus propiedades.
            if (empresa != null) {
                lblRUC.setText(empresa.getRucEmpresa());
                lblNombre.setText(empresa.getNombreEmpresa());
                lblDireccion.setText(empresa.getDireccionEmpresa());
            } else {
                lblRUC.setText("No disponible");
                lblNombre.setText("No disponible");
                lblDireccion.setText("No disponible");
            }
        } catch (Exception e) {
            // Manejo de errores: registro de excepciones.
            logger.error("Error al listar la empresa: " + e.getMessage(), e);

            // Mensajes por defecto en caso de error.
            lblRUC.setText("Error");
            lblNombre.setText("Error");
            lblDireccion.setText("Error");
        }
    }

    @FXML
    private Label rucExterno;

    @FXML
    private Label nombreExterno;

    @FXML
    private Label direccionExterno;


    private empresaExterna empresaExterna;

    @Autowired
    private EmpresaExternaServicio empresaExternaServicio;

    private void listarEmpresaExterna() {
        try {
            // Verificar que el RUC no esté vacío
            String ruc = txtRucEmpresa.getText().trim();
            if (ruc.isEmpty()) {
                logger.error("El RUC no puede estar vacío");
                rucExterno.setText("RUC vacío");
                nombreExterno.setText("RUC vacío");
                direccionExterno.setText("RUC vacío");
                return;
            }

            // Obtener la empresa externa por el RUC
            empresaExterna = empresaExternaServicio.obtenerEmpresaPorRuc(ruc);

            if (empresaExterna != null) {
                // Si la empresa es encontrada, asignar sus datos a los campos
                System.out.println("Empresa obtenida: " + empresaExterna);

                rucExterno.setText(empresaExterna.getRucExterna());
                nombreExterno.setText(empresaExterna.getRazonSocial());
                direccionExterno.setText(empresaExterna.getDireccion());
            } else {
                // Si no se encuentra la empresa, mostrar mensaje de error
                rucExterno.setText("No encontrada");
                nombreExterno.setText("No encontrada");
                direccionExterno.setText("No encontrada");
            }

        } catch (Exception e) {
            // Manejo de otros errores generales
            logger.error("Error al listar la empresa externa: " + e.getMessage(), e);
            rucExterno.setText("Error");
            nombreExterno.setText("Error");
            direccionExterno.setText("Error");
        }
    }

    public void enlisarPedido(ActionEvent actionEvent) {
        listarPedidos();
        configuraraColumnas();
        listarEmpresaExterna();
    }

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public void regresarGeneral(ActionEvent actionEvent) {
        try {
            // Cargar el nuevo archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/controlGeneral.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(loader.load());
            // Obtener el Stage actual
            Stage stage = (Stage) btnGenerar.getScene().getWindow();
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
        alert.initOwner(btnGenerar.getScene().getWindow());
        alert.setTitle("Error");
        alert.setHeaderText(error);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    private Pane pnPanelFactura;

    public void savePaneAsPDF() {
        WritableImage writableImage = new WritableImage((int) pnPanelFactura.getWidth(), (int) pnPanelFactura.getHeight());
        pnPanelFactura.snapshot(null, writableImage);

        BufferedImage bufferedImage = new BufferedImage((int) writableImage.getWidth(), (int) writableImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < writableImage.getWidth(); x++) {
            for (int y = 0; y < writableImage.getHeight(); y++) {
                bufferedImage.setRGB(x, y, writableImage.getPixelReader().getArgb(x, y));
            }
        }

        Document document = new Document(PageSize.A4);
        try {
            // Obtener la ruta del escritorio
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            // Crear una carpeta llamada "Facturas" si no existe
            File folder = new File(desktopPath + "/Facturas");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Generar un nombre único para el archivo si ya existe uno
            String fileName = "Factura.pdf";
            File file = new File(folder, fileName);
            int counter = 1;
            while (file.exists()) {
                fileName = "Factura" + counter + ".pdf";
                file = new File(folder, fileName);
                counter++;
            }

            // Guardar el archivo en la carpeta
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);

            document.open();
            Image img = Image.getInstance(bufferedImage, null);
            img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            document.add(img);

            document.close();
            System.out.println("PDF guardado en: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

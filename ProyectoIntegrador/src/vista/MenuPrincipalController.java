package vista;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private Button buttonCrud;

    @FXML
    private Button buttonFactura;

    @FXML
    private Button buttonReporte;

    @FXML
    void ingresoCrud(MouseEvent event) {
        abrirVentana("Producto.fxml", "Gestión de Productos");
    }

    @FXML
    void ingresoFactura(MouseEvent event) {
        abrirVentana("FacturaVista.fxml", "Gestión de Facturas");
    }

    @FXML
    void ingresoReporte(MouseEvent event) {
        // Puedes ajustar este método para abrir una vista específica para los reportes si la tienes.
        // abrirVentana("ReporteView.fxml", "Gestión de Reportes");
        System.out.println("Funcionalidad de reportes aún no implementada");
    }

    private void abrirVentana(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package vista;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CesionController {

    @FXML
    private Button buttonIngreso;

    @FXML
    private void cambiarInterfazSiguiente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FacturaVista.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickIngreso(MouseEvent event) {
        // Manejar el evento de clic en el botón de ingreso
    }
}

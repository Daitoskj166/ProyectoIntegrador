package vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import datos.DatosUsuario;

public class CesionController {

    @FXML
    private Button buttonIngreso;

    @FXML
    private TextField textContraseña;

    @FXML
    private TextField textUser;

    private DatosUsuario datosUsuario = new DatosUsuario();

    @FXML
    void facturavista(ActionEvent event) {
      
    }

    @FXML
    void clickIngreso(MouseEvent event) {
        String username = textUser.getText();
        String password = textContraseña.getText();
        
        if (datosUsuario.validarUsuario(username, password)) {
         
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FacturaVista.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) buttonIngreso.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
           
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de Autenticación");
            alert.setHeaderText(null);
            alert.setContentText("Usuario o contraseña incorrectos");
            alert.showAndWait();
        }
    }
}
package vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Datos.Factura;

public class FacturaVistaController {

    @FXML
    private TableView<Factura> tableFacturas;

    @FXML
    private TableColumn<Factura, String> ColumnIdFactura;

    @FXML
    private TableColumn<Factura, String> ColumnIdUsuario;

    @FXML
    private TableColumn<Factura, String> ColumnIdVenta;

    @FXML
    private TableColumn<Factura, String> ColumnIva;

    @FXML
    private TableColumn<Factura, String> ColumnReferenciaProducto;

    @FXML
    private TableColumn<Factura, String> ColumnSubtotal;

    @FXML
    private Button buttonActualizar;

    @FXML
    private Button buttonCrear;

    @FXML
    private TextField textIdFactura;

    @FXML
    private TextField textIdUsuario;

    @FXML
    private TextField textIdVenta;

    @FXML
    private TextField textIva;

    @FXML
    private TextField textReferenciaProducto;

    @FXML
    private TextField textSubtotal;

    private ObservableList<Factura> facturas;

    @FXML
    void clickActualizar(MouseEvent event) {
        actualizarFactura();
    }

    @FXML
    void clickCrear(MouseEvent event) {
        crearFactura();
    }

    @FXML
    private void initialize() {
        configurarColumnas();
        facturas = FXCollections.observableArrayList();
        tableFacturas.setItems(facturas);
        cargarFacturas();
    }

    private void configurarColumnas() {
        ColumnIdFactura.setCellValueFactory(new PropertyValueFactory<>("idFactura"));
        ColumnIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        ColumnIdVenta.setCellValueFactory(new PropertyValueFactory<>("idVenta"));
        ColumnIva.setCellValueFactory(new PropertyValueFactory<>("iva"));
        ColumnReferenciaProducto.setCellValueFactory(new PropertyValueFactory<>("referenciaProducto"));
        ColumnSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    private void cargarFacturas() {
        facturas.clear();
        Connection conn = getConnection();
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            String query = "SELECT idFactura, idUsuario, idVenta, iva, referenciaProducto, subtotal FROM FACTURA";
            rs = st.executeQuery(query);

            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getString("idFactura"),
                        rs.getString("idUsuario"),
                        rs.getString("idVenta"),
                        rs.getString("iva"),
                        rs.getString("referenciaProducto"),
                        rs.getString("subtotal")
                );
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void crearFactura() {
        Connection conn = getConnection();
        try {
            // Crear una nueva venta antes de insertar la factura
            String idVenta = generarIdVenta(); // Generar un nuevo idVenta único
            crearVenta(idVenta);

            String query = "INSERT INTO FACTURA (idFactura, idUsuario, idVenta, iva, referenciaProducto, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            String idFactura = textIdFactura.getText();
            String idUsuario = textIdUsuario.getText();
            String iva = textIva.getText();
            String referenciaProducto = textReferenciaProducto.getText();
            String subtotal = textSubtotal.getText();

            System.out.println("Valores a insertar: " + idFactura + ", " + idUsuario + ", " + idVenta + ", " + iva + ", " + referenciaProducto + ", " + subtotal);

            pst.setString(1, idFactura);
            pst.setString(2, idUsuario);
            pst.setString(3, idVenta);
            pst.setString(4, iva);
            pst.setString(5, referenciaProducto);
            pst.setString(6, subtotal);
            pst.executeUpdate();
            cargarFacturas(); // Refresca la tabla después de la inserción
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void actualizarFactura() {
        Connection conn = getConnection();
        try {
            String query = "UPDATE FACTURA SET idUsuario = ?, idVenta = ?, iva = ?, referenciaProducto = ?, subtotal = ? WHERE idFactura = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, textIdUsuario.getText());
            pst.setString(2, textIdVenta.getText());
            pst.setString(3, textIva.getText());
            pst.setString(4, textReferenciaProducto.getText());
            pst.setString(5, textSubtotal.getText());
            pst.setString(6, textIdFactura.getText());
            pst.executeUpdate();
            cargarFacturas(); // Refresca la tabla después de la actualización
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void crearVenta(String idVenta) {
        Connection conn = getConnection();
        try {
            String query = "INSERT INTO VENTA (idVenta, fechaVenta) VALUES (?, SYSDATE)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, idVenta);
            pst.executeUpdate();
            System.out.println("Venta con id " + idVenta + " creada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generarIdVenta() {
        return "v_" + (int) (Math.random() * 10000); // Generar un idVenta único
    }

    private Connection getConnection() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // Ajusta esto según tu configuración
            String user = "pasabocaAntojitos";
            String password = "pasabocaAntojitos";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

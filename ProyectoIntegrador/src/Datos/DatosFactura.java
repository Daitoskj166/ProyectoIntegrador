package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DatosFactura {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcll";
    private static final String USER = "pasabocaAntojitos";
    private static final String PASSWORD = "pasabocaAntojitos";

    public LinkedList<Factura> getDatos() {
        LinkedList<Factura> data = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM FACTURA");
             ResultSet result = st.executeQuery()) {
        	
            while (result.next()) {
            	Factura factura = new Factura(
                        result.getString("idFactura"),
                        result.getString("subtotal"),
                        result.getString("iva"),
                        result.getString("referenciaProducto"),
                        result.getString("idUsuario"),
                        result.getString("idVenta")
                );
                data.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void guardarFactura(Factura factura) {
        String sql = "INSERT INTO FACTURA (idFactura, subtotal, iva, referenciaProducto, idUsuario, idVenta) VALUES (?, ?, ?, ?, ?, ?)";
       
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, factura.getIdFactura());
            pstmt.setString(2, factura.getSubtotal());
            pstmt.setString(3, factura.getIva());
            pstmt.setString(4, factura.getReferenciaProducto());
            pstmt.setString(5, factura.getIdUsuario());
            pstmt.setString(6, factura.getIdVenta());
            
            pstmt.executeUpdate();
            System.out.println("Factura guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el factura en la base de datos: " + e.getMessage());
        }
    
    }

    public void eliminarFactura(String idFactura) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("DELETE FROM FACTURA WHERE idFactura = ?")) {

            st.setString(1, idFactura);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean actualizarFactura(Factura factura) {
        String sql = "UPDATE FACTURA SET idUsuario = ?, subtotal = ?, iva = ?, referenciaProducto = ? WHERE idFactura = ? AND idVenta = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, factura.getIdFactura());
            pstmt.setString(2, factura.getSubtotal());
            pstmt.setString(3, factura.getIva());
            pstmt.setString(4, factura.getReferenciaProducto());
            pstmt.setString(5, factura.getIdUsuario());
            pstmt.setString(6, factura.getIdVenta());

            pstmt.executeUpdate();
            System.out.println("Factura actualizada correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la factura en la base de datos: " + e.getMessage());
        }
		return false;
    }
}
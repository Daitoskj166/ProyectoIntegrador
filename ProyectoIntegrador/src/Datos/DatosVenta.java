package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DatosVenta {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "pasabocasAntojitos";
    private static final String PASSWORD = "pasabocasAntojitos";

    public LinkedList<Venta> getDatos() {
        LinkedList<Venta> data = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM VENTA");
             ResultSet result = st.executeQuery()) {
       
            while (result.next()) {
            	Venta venta = new Venta(
                        result.getString("idVenta"),
                        result.getString("fechaVenta"),
                        result.getString("notas"),
                        result.getString("idUsuario"),
                        result.getString("referenciaProducto"),
                        result.getString("codigoVendedor"),
                        result.getString("metodoPago"),
                        result.getString("precioVenta")
     
                );
                data.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void guardarVenta(Venta venta) {
        String sql = "INSERT INTO VENTA (idVenta, fechaVenta, notas, idUsuario, referenciaProducto, codigoVendedor, metodoPago, precioVenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, venta.getIdVenta());
            pstmt.setString(2, venta.getFechaVenta());
            pstmt.setString(3, venta.getNotas());
            pstmt.setString(4, venta.getIdUsuario());
            pstmt.setString(5, venta.getReferenciaProducto());
            pstmt.setString(6, venta.getCodigoVendedor());
            pstmt.setString(7, venta.getMetodoPago());
            pstmt.setString(8, venta.getPrecioVenta());
            
            pstmt.executeUpdate();
            System.out.println("Venta guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar la venta en la base de datos: " + e.getMessage());
        }
    
    }

    public void eliminarVenta(String idVenta) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("DELETE FROM VENTA WHERE idVenta = ?")) {

            st.setString(1, idVenta);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarVenta(Venta venta) {
        String sql = "UPDATE VENTA SET idVenta = ?, fechaVenta = ?, notas = ?, idUsuario = ?, metodoPago = ?, precioVenta = ? WHERE referenciaProducto = ? AND codigoVendedor = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, venta.getIdVenta());
            pstmt.setString(2, venta.getFechaVenta());
            pstmt.setString(3, venta.getNotas());
            pstmt.setString(4, venta.getIdUsuario());
            pstmt.setString(5, venta.getMetodoPago());
            pstmt.setString(6, venta.getPrecioVenta());
            pstmt.setString(7, venta.getReferenciaProducto());
            pstmt.setString(8, venta.getCodigoVendedor());

            pstmt.executeUpdate();
            System.out.println("Venta actualizada correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la venta en la base de datos: " + e.getMessage());
        }
    }
}
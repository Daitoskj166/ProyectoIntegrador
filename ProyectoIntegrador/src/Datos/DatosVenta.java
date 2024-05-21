Datos Venta
package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

    public void guardarFactura(Venta venta) {
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

    public void actualizarFactura(Factura factura) {
        String sql = "UPDATE FACTURA SET idFactura = ?, subtotal = ?, iva = ?, referenciaProducto = ? WHERE idUsuario = ?, WHERE idVenta = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, factura.getIdFactura());
            pstmt.setString(2, factura.getSubtotal());
            pstmt.setString(3, factura.getIva());
            pstmt.setString(4, factura.getReferenciaProducto());
            pstmt.setString(5, factura.getIdUsuario());
            pstmt.setString(6, factura.getIdVenta());
            

            pstmt.executeUpdate();
            System.out.println("Producto actualizado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el factura en la base de datos: " + e.getMessage());
        	}
    }
}
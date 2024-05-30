package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DatosProducto {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "pasabocasAntojitos";
    private static final String PASSWORD = "pasabocasAntojitos";

    public LinkedList<Producto> getDatos() {
        LinkedList<Producto> data = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM PRODUCTO");
             ResultSet result = st.executeQuery()) {
        	
            while (result.next()) {
            	Producto producto = new Producto(
                        result.getString("referenciaProducto"),
                        result.getString("nombre"),
                        result.getString("cantidad"),
                        result.getString("valorUnitario"),
                        result.getString("valorTotal"),
                        result.getString("idUsuario")
                );
                data.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void guardarProducto(Producto producto) {
        String sql = "INSERT INTO PRODUCTO (referenciaProducto, nombre, cantidad, valorUnitario, valorTotal, idUsuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getReferenciaProducto());
            pstmt.setString(2, producto.getNombre());
            pstmt.setString(3, producto.getCantidad());
            pstmt.setString(4, producto.getValorUnitario());
            pstmt.setString(5, producto.getValorTotal());
            pstmt.setString(6, producto.getIdUsuario());
            
            pstmt.executeUpdate();
            System.out.println("Producto guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el producto en la base de datos: " + e.getMessage());
        }
    
    }

    public void eliminarProducto(String referenciaProducto) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("DELETE FROM PRODUCTO WHERE referenciaProducto = ?")) {

            st.setString(1, referenciaProducto);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE PRODUCTO SET idUsuario = ?, nombre = ?, cantidad = ?, valorUnitario = ?, valorTotal = ? WHERE idUsuarioreferenciaProducto = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, producto.getReferenciaProducto());
            pstmt.setString(2, producto.getNombre());
            pstmt.setString(3, producto.getCantidad());
            pstmt.setString(4, producto.getValorUnitario());
            pstmt.setString(5, producto.getValorTotal());
            pstmt.setString(6, producto.getIdUsuario());

            pstmt.executeUpdate();
            System.out.println("Producto actualizado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto en la base de datos: " + e.getMessage());
        }
    }
}
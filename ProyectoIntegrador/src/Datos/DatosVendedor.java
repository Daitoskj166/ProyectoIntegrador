package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DatosVendedor {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "pasabocaAntojitos";
    private static final String PASSWORD = "pasabocaAntojitos";

    public LinkedList<vendedor> getDatos() {
        LinkedList<vendedor> data = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM VENDEDOR");
             ResultSet result = st.executeQuery()) {
        	
            while (result.next()) {
            	vendedor vendedor = new vendedor(
                        result.getString("codigoVendedor"),
                        result.getString("nombre"),
                        result.getString("apellido"),
                        result.getString("telefono"),
                        result.getString("direccion"),
                        result.getString("fechaContratacion"),
                        result.getString("salario"),
                        result.getString("comision"),
                        result.getString("estado"),
                        result.getString("idUsuario")
     
                );
                data.add(vendedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void guardarVendedor(vendedor vendedor) {
        String sql = "INSERT INTO VENDEDOR (codigoVendedor, nombre, apellido, telefono, fechaContratacion, salario, comision, estado, idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vendedor.getCodigoVendedor());
            pstmt.setString(2, vendedor.getNombre());
            pstmt.setString(3, vendedor.getApellido());
            pstmt.setString(4, vendedor.getTelefono());
            pstmt.setString(5, vendedor.getFechaContratacion());
            pstmt.setString(6, vendedor.getSalario());
            pstmt.setString(7, vendedor.getComision());
            pstmt.setString(8, vendedor.getEstado());
            pstmt.setString(9, vendedor.getIdUsuario());
            
            pstmt.executeUpdate();
            System.out.println("Vendedor guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el vendedor en la base de datos: " + e.getMessage());
        }
    
    }

    public void eliminarVendedor(String codigoVendedor) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("DELETE FROM VENDEDOR WHERE codigoVendedor = ?")) {

            st.setString(1, codigoVendedor);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarVendedor(vendedor vendedor) {
        String sql = "UPDATE VENDEDOR SET idUsuario = ?, nombre = ?, apellido = ?, telefono = ?, fechaContratacion = ?, salario = ?, comision = ?, estado = ? WHERE codigoVendedor = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, vendedor.getCodigoVendedor());
            pstmt.setString(2, vendedor.getNombre());
            pstmt.setString(3, vendedor.getApellido());
            pstmt.setString(4, vendedor.getTelefono());
            pstmt.setString(5, vendedor.getFechaContratacion());
            pstmt.setString(6, vendedor.getSalario());
            pstmt.setString(7, vendedor.getComision());
            pstmt.setString(8, vendedor.getEstado());
            pstmt.setString(9, vendedor.getIdUsuario());

            pstmt.executeUpdate();
            System.out.println("Vendedor actualizado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el vendedor en la base de datos: " + e.getMessage());
        }
    }
}
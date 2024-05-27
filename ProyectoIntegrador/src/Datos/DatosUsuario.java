package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DatosUsuario {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "pasabocasAntojitos";
    private static final String PASSWORD = "pasabocasAntojitos";

    public LinkedList<Usuario> getDatos() {
        LinkedList<Usuario> data = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM USUARIO");
             ResultSet result = st.executeQuery()) {
        	
            while (result.next()) {
            	Usuario usuario = new Usuario(
                        result.getString("idUsuario"),
                        result.getString("nombreUsuario"),
                        result.getString("contraseña")
                    
                );
                data.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (idUsuario, nombreUsuario, contraseña) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getIdUsuario());
            pstmt.setString(2, usuario.getNombreUsuario());
            pstmt.setString(3, usuario.getContraseña());
    
            
            pstmt.executeUpdate();
            System.out.println("Usuario guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el usuario en la base de datos: " + e.getMessage());
        }
    
    }

    public void eliminarUsuario(String idUsuario) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("DELETE FROM USUARIO WHERE idUsuario = ?")) {

            st.setString(1, idUsuario);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE USUARIO SET idUsuario = ?, nombreUsuario = ?, contraseña = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, usuario.getIdUsuario());
            pstmt.setString(2, usuario.getNombreUsuario());
            pstmt.setString(3, usuario.getContraseña());
       
            

            pstmt.executeUpdate();
            System.out.println("Usuario actualizado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario en la base de datos: " + e.getMessage());
        	}
    }
}

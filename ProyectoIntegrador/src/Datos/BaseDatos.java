package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class BaseDatos {
	private String conectionstr = "jdbc:oracle:thin:@192.168.254.215:1521:orcl";
	private String username = "proyectoAntojitos1";
	private String password = "proyectoAntojitos1";
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.conectionstr, this.username, this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public LinkedList<proyectoAntojitos1> getDatos(){
		LinkedList<proyectoAntojitos1> data = new LinkedList<proyectoAntojitos1>();
		Connection conn = this.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String query = "select * from proyectoAntojitos1";
			ResultSet result = st.executeQuery(query);
			while(result.next()) {
				data.add(new proyectoAnotojitos(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public proyectoAntojitos1 login(String username, String password) {
		MyLogin user=null;
		Connection conn = this.getConnection();
		try {
			String query = "select * from proyectoAntojitos1 where username=? and password=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet result = st.executeQuery();
			while(result.next()) {
				user=new MyLogin(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
		
	}

}

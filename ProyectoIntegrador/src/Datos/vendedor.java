package Datos;

public class vendedor {
	
	private String codigoVendedor;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	private String fechaContratacion;
	private String salario;
	private String comision;
	private String estado;
	private String idUsuario;
	
	public vendedor(String codigoVendedor, String nombre, String apellido, String telefono, String direccion, String fechaContratacion, String salario, String comision, String estado, String idUsuario) {
		
		this.codigoVendedor = codigoVendedor;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechaContratacion = fechaContratacion;
		this.salario = salario;
		this.comision = comision;
		this.estado = estado;
		this.idUsuario = idUsuario;
		
	}

}

package Datos;

public class Producto {
	
	private String referenciaProducto;
	private String nombre;
	private String cantidad;
	private String valorUnitario;
	private String valorTotal;
	private String idUsuario;
	
	public Producto(String referenciaProducto, String nombre, String cantidad, String valorUnitario, String valorTotal, String idUsuario) {
		
		this.referenciaProducto = referenciaProducto;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.idUsuario = idUsuario;
	
	}

}


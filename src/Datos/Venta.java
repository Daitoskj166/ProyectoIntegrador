package Datos;

public class Venta {
	
	private String idVenta;
	private String fechaVenta;
	private String metodoPago;
	private String notas;
	private String idUsuario;
	private String referenciaProducto;
	private String codigoVendedor;
	private String precioVenta;

	public Venta(String idVenta, String fechaVenta, String notas, String idUsuario, String referenciaProducto, String codigoVendedor, String metodoPago, String precioVenta) {
		
		this.idVenta = idVenta;
		this.fechaVenta = fechaVenta;
		this.metodoPago = metodoPago;
		this.notas = notas;
		this.idUsuario = idUsuario;
		this.referenciaProducto = referenciaProducto;
		this.codigoVendedor = codigoVendedor;
		this.precioVenta = precioVenta;
	}

	public String getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(String idVenta) {
		this.idVenta = idVenta;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getReferenciaProducto() {
		return referenciaProducto;
	}

	public void setReferenciaProducto(String referenciaProducto) {
		this.referenciaProducto = referenciaProducto;
	}

	public String getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(String codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}

	public String getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}

	
	

}

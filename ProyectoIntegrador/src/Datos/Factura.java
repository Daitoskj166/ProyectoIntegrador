package Datos;

public class Factura {
	
	private String idFactura;
	private String subtotal;
	private String iva;
	private String referenciaProducto;
	private String idUsuario;
	private String idVenta;

	public Factura(String idFactura, String subtotal, String iva, String referenciaProducto, String idUsuario, String idVenta) {
		
		this.idFactura = idFactura;
		this.subtotal = subtotal;
		this.iva = iva;
		this.referenciaProducto = referenciaProducto;
		this.idUsuario = idUsuario;
		this.idVenta = idVenta;
	}

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getReferenciaProducto() {
		return referenciaProducto;
	}

	public void setReferenciaProducto(String referenciaProducto) {
		this.referenciaProducto = referenciaProducto;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(String idVenta) {
		this.idVenta = idVenta;
	}
	
	

}

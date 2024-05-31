package Datos;

public class Factura {
    private String idFactura;
    private String idUsuario;
    private String idVenta;
    private String iva;
    private String referenciaProducto;
    private String subtotal;

    public Factura(String idFactura, String idUsuario, String idVenta, String iva, String referenciaProducto, String subtotal) {
        this.idFactura = idFactura;
        this.idUsuario = idUsuario;
        this.idVenta = idVenta;
        this.iva = iva;
        this.referenciaProducto = referenciaProducto;
        this.subtotal = subtotal;
    }

    // Getters y setters
    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
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

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}

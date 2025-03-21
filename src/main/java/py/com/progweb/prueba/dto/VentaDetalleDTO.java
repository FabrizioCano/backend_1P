package py.com.progweb.prueba.dto;
import py.com.progweb.prueba.model.Producto;
public class VentaDetalleDTO {
    private Long idVentaDetalle;
    private Producto producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double totalDetalle;

    public VentaDetalleDTO(Long idVentaDetalle, Producto producto, Integer cantidad, Double precioUnitario, Double precioTotalDetalle) {
        this.idVentaDetalle = idVentaDetalle;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.totalDetalle = precioTotalDetalle;
    }

    public Long getIdVentaDetalle() {
        return idVentaDetalle;
    }

    public void setIdVentaDetalle(Long idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        this.totalDetalle = cantidad * this.precioUnitario; 
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.totalDetalle = this.cantidad * precioUnitario;
    }

    public Double getTotalDetalle() {
        return totalDetalle;
    }
}

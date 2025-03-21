package py.com.progweb.prueba.dto;

public class VentaDetalleDTO {
    private Long idVentaDetalle;
    private Integer idProducto;
    private String nombreProducto;
    private Integer idCategoria;
    private String nombreCategoria;
    private Integer cantidad;
    private Double precioUnitario;
    private Double totalDetalle;

    public VentaDetalleDTO(Long idVentaDetalle, Integer idProducto, String nombreProducto, Integer idCategoria, String nombreCategoria, Integer cantidad, Double precioUnitario, Double precioTotalDetalle) {
        this.idVentaDetalle = idVentaDetalle;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
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

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
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

package py.com.progweb.prueba.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "venta_cabecera")
@JsonRootName(value = "venta_cabecera")
@NamedQueries({
    @NamedQuery(name = "VentaCabecera.findAll", query = "SELECT v FROM VentaCabecera v"),
    @NamedQuery(name = "VentaCabecera.findByIdVenta", query = "SELECT v FROM VentaCabecera v WHERE v.idVenta = :idVenta"),
    @NamedQuery(name = "VentaCabecera.findByFecha", query = "SELECT v FROM VentaCabecera v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "VentaCabecera.findByTotal", query = "SELECT v FROM VentaCabecera v WHERE v.total = :total")})
public class VentaCabecera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "fecha_venta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VentaDetalle> detalles;


    public VentaCabecera() {}

    public VentaCabecera(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<VentaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<VentaDetalle> detalles) {
        this.detalles = detalles;
    }

    public void addDetalle(VentaDetalle detalle) {
        this.detalles.add(detalle);
    }

    public void removeDetalle(VentaDetalle detalle) {
        this.detalles.remove(detalle);
    }

    public void clearDetalles() {
        this.detalles.clear();
    }

    private Double calcularTotal() {
        Double total = 0.0;
        for (VentaDetalle detalle : this.detalles) {
            total += detalle.getSubtotal();
        }
        return total;
    }

    public void calcularTotalVenta() {
        this.total = this.calcularTotal();
    }
}

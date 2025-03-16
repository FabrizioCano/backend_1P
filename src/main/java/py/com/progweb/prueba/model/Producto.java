package py.com.progweb.prueba.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll",
                query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto",
                query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByNombre",
                query = "SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(:nombre)"),
    @NamedQuery(name = "Producto.findByCategoria",
                query = "SELECT p FROM Producto p WHERE p.categoria IS NOT NULL AND p.categoria.idCategoria = :idCategoria"),
    @NamedQuery(name = "Producto.findByNombreAndCategoria",
                query = "SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(:nombre) AND p.categoria IS NOT NULL AND p.categoria.idCategoria = :idCategoria")
})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto", length = 50)
    private Integer idProducto;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne
    private Categoria categoria;

    @Column(name = "precio_venta")
    private double precioVenta;

    @Column(name = "cantidad_existente")
    private int cantidadExistente;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    
    public Producto(String nombre, Categoria categoria, double precioVenta, int cantidadExistente) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioVenta = precioVenta;
        this.cantidadExistente = cantidadExistente;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setIdCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidadExistente() {
        return cantidadExistente;
    }

    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        return !((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto)));
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", categoria=" + categoria + ", precioVenta=" + precioVenta + ", cantidadExistente=" + cantidadExistente + '}';
    }

}

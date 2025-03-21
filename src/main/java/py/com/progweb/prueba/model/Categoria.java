package py.com.progweb.prueba.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonRootName;
@Entity
@Table(name = "categoria")
@JsonRootName(value = "categoria")
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre")
})
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategoria", length = 50)
    private Integer idCategoria;

    @Column(name = "nombre", length = 100)
    private String nombre;

    public Categoria() {
    }

    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

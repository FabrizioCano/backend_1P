package py.com.progweb.prueba.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name = "cliente")
@JsonRootName(value = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByApellido", query = "SELECT c FROM Cliente c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Cliente.findByCedula", query = "SELECT c FROM Cliente c WHERE c.cedula = :cedula"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente" ,length = 50)
    private Long idCliente;

    @Column(name = "nombre",length = 50)
    private String nombre;

    @Column(name = "apellido",length = 50)
    private String apellido;

    @Column(name = "cedula",length = 50, unique = true)
    private String cedula;

    @Column(name = "email",length = 50, unique = true)
    private String email;


    public Cliente() {}

    public Cliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
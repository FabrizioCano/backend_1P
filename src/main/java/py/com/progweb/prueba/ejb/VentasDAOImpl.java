package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VentaCabecera;
import py.com.progweb.prueba.model.VentaDetalle;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VentasDAOImpl implements VentasDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager entityManager;

    public void registrarVenta(VentaCabecera venta) {
        entityManager.persist(venta);
    }

    public List<VentaCabecera> listarVentas() {
        return entityManager.createNamedQuery("VentaCabecera.findAll", VentaCabecera.class).getResultList();
    }

    public VentaCabecera findVentaPorId(Long id) {
        return entityManager.find(VentaCabecera.class, id);
    }

    public void registrarDetalle(VentaDetalle detalle) {
        entityManager.persist(detalle);
    }

    
}

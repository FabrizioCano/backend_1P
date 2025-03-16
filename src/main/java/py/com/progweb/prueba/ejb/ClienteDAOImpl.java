package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClienteDAOImpl implements ClienteDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager entityManager;
    
    @Override
    public List<Cliente> findAllClientes() {
        return entityManager.createNamedQuery("Cliente.findAll", Cliente.class).getResultList(); 
    }

    @Override
    public Cliente findClienteById(Cliente cliente) {
        return entityManager.find(Cliente.class, cliente.getIdCliente());
    }

    @Override
    public List<Cliente> findClienteByNombre(Cliente cliente) {
        return entityManager.createNamedQuery("Cliente.findByNombre", Cliente.class)
                .setParameter("nombre", cliente.getNombre())
                .getResultList();
    }

    @Override
    public List<Cliente> findClienteByApellido(Cliente cliente) {
        return entityManager.createNamedQuery("Cliente.findByApellido", Cliente.class)
                .setParameter("apellido", cliente.getApellido())
                .getResultList();
    }

    @Override
    public Cliente findClienteByCedula(Cliente cliente) {
        return entityManager.createNamedQuery("Cliente.findByCedula", Cliente.class)
                .setParameter("cedula", cliente.getCedula())
                .getSingleResult();
    }

    @Override
    public Cliente findClienteByEmail(Cliente cliente) {
        return entityManager.createNamedQuery("Cliente.findByEmail", Cliente.class)
                .setParameter("email", cliente.getEmail())
                .getSingleResult();
    }

    @Override
    public void insertCliente(Cliente cliente) {
        entityManager.persist(cliente);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        entityManager.merge(cliente);
    }

    @Override
    public void deleteCliente(Cliente cliente) {
        entityManager.remove(entityManager.merge(cliente));
    }
}

package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;
import java.util.List;

public interface ClienteDAO {
    
    public List<Cliente> findAllClientes();
    public Cliente findClienteById(Cliente cliente);
    public List<Cliente> findClienteByNombre(Cliente cliente);
    public List<Cliente> findClienteByApellido(Cliente cliente);
    public Cliente findClienteByCedula(Cliente cliente);
    public Cliente findClienteByEmail(Cliente cliente);

    public void insertCliente(Cliente cliente);
    public void updateCliente(Cliente cliente);
    public void deleteCliente(Cliente cliente);
    
}

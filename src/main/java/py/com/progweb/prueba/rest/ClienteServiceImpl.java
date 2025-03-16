package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.ejb.ClienteDAO;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import java.util.List;


@Stateless
public class ClienteServiceImpl implements ClienteService{

    @EJB
    private ClienteDAO clienteDAO;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteDAO.findAllClientes();
    }

    @Override
    public Cliente getClienteById(Long idCliente) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        return clienteDAO.findClienteById(cliente);
    }

    @Override
    public List<Cliente> getClienteByNombre(String nombre) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        return clienteDAO.findClienteByNombre(cliente);
    }

    @Override
    public List<Cliente> getClienteByApellido(String apellido) {
        Cliente cliente = new Cliente();
        cliente.setApellido(apellido);
        return clienteDAO.findClienteByApellido(cliente);
    }

    @Override
    public Cliente getClienteByCedula(String cedula) {
        Cliente cliente = new Cliente();
        cliente.setCedula(cedula);
        return clienteDAO.findClienteByCedula(cliente);
    }

    @Override
    public Cliente getClienteByEmail(String email) {
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        return clienteDAO.findClienteByEmail(cliente);
    }

    @Override
    public void addCliente(Cliente cliente) {
        clienteDAO.insertCliente(cliente);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        clienteDAO.updateCliente(cliente);
    }

    @Override
    public void deleteCliente(Long idCliente) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        clienteDAO.deleteCliente(cliente);
    }
}
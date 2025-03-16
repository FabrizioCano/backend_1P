package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import java.util.List;

public interface ClienteService {
    public List<Cliente> getAllClientes();
    public Cliente getClienteById(Long idCliente);
    public List<Cliente> getClienteByNombre(String nombre);
    public List<Cliente> getClienteByApellido(String apellido);
    public Cliente getClienteByCedula(String cedula);
    public Cliente getClienteByEmail(String email);
    public void addCliente(Cliente cliente);
    public void updateCliente(Cliente cliente);
    public void deleteCliente(Long idCliente);
}

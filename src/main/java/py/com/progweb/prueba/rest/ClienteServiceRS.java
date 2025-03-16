package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ejb.EJB;

@Path("/clientes")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class ClienteServiceRS {

    @EJB
    private ClienteService clienteService;

    @GET
    @Path("/all")
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GET
    @Path("/id/{id}")
    public Response getClienteById(@PathParam("id") Long idCliente) {
        Cliente cliente = clienteService.getClienteById(idCliente);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cliente).build();
    }

    @GET
    @Path("/nombre/{nombre}")
    public List<Cliente> getClienteByNombre(@PathParam("nombre") String nombre) {
        return clienteService.getClienteByNombre(nombre);
    }

    @GET
    @Path("/apellido/{apellido}")
    public List<Cliente> getClienteByApellido(@PathParam("apellido") String apellido) {
        return clienteService.getClienteByApellido(apellido);
    }

    @GET
    @Path("/cedula/{cedula}")
    public Response getClienteByCedula(@PathParam("cedula") String cedula) {
        Cliente cliente = clienteService.getClienteByCedula(cedula);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cliente).build();
    }

    @GET
    @Path("/email/{email}")
    public Response getClienteByEmail(@PathParam("email") String email) {
        Cliente cliente = clienteService.getClienteByEmail(email);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cliente).build();
    }

    @POST
    @Path("/add")
    public Response addCliente(Cliente cliente) {
        clienteService.addCliente(cliente);
        return Response.ok(cliente).build();
    }

    @PUT
    @Path("/update/{id}")
    public Response updateCliente(@PathParam("id") Long idCliente, Cliente cliente) {
        Cliente c = clienteService.getClienteById(idCliente);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        cliente.setIdCliente(idCliente);
        clienteService.updateCliente(cliente);
        return Response.ok(cliente).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteCliente(@PathParam("id") Long idCliente) {
        clienteService.deleteCliente(idCliente);
        String mensaje = "Cliente con ID " + idCliente + " eliminado correctamente.";
        return Response.status(Response.Status.OK)
               .entity(mensaje)
               .build();
    }


}

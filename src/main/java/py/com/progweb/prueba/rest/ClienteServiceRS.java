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
    public Response getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        if (clientes == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No existe una lista de clientes")
                    .build();
        }
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{id}")
    public Response getClienteById(@PathParam("id") Long idCliente) {
        Cliente cliente = clienteService.getClienteById(idCliente);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente con id " + idCliente + " no encontrado")
                    .build();
        }
        return Response.ok(cliente).build();
    }

    @GET
    @Path("/{nombre}")
    public Response getClienteByNombre(@PathParam("nombre") String nombre) {
        List<Cliente> clientes = clienteService.getClienteByNombre(nombre);
        if (clientes == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente con nombre " + nombre + " no encontrado")
                    .build();
        }
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{apellido}")
    public Response getClienteByApellido(@PathParam("apellido") String apellido) {
        List<Cliente> clientes = clienteService.getClienteByApellido(apellido);
        if (clientes == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente con apellido " + apellido + " no encontrado").build();
        }
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{cedula}")
    public Response getClienteByCedula(@PathParam("cedula") String cedula) {
        Cliente cliente = clienteService.getClienteByCedula(cedula);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente con cedula " + cedula + " no encontrado")
                    .build();
        }
        return Response.ok(cliente).build();
    }

    @GET
    @Path("/{email}")
    public Response getClienteByEmail(@PathParam("email") String email) {
        Cliente cliente = clienteService.getClienteByEmail(email);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente con email " + email + " no encontrado")
                    .build();
        }
        return Response.ok(cliente).build();
    }

    @POST
    public Response addCliente(Cliente cliente) {
        try {
            clienteService.addCliente(cliente);
            return Response.ok().entity(cliente).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear el cliente").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCliente(@PathParam("id") Long idCliente, Cliente cliente) {
        try {
            Cliente c = clienteService.getClienteById(idCliente);
            if (c == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Error al modificar el cliente").build();
            }
            cliente.setIdCliente(idCliente);
            clienteService.updateCliente(cliente);
            return Response.ok(cliente).build();
        }catch(Exception e){
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el cliente").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") Long idCliente) {
        try {
            Cliente cliente = clienteService.getClienteById(idCliente);
            if (cliente == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Cliente con ID " + idCliente + " no encontrado.")
                        .build();
            }

            clienteService.deleteCliente(cliente);
            String mensaje = "Cliente " + cliente.getNombre() + " " + cliente.getApellido() + " eliminado correctamente.";
            return Response.status(Response.Status.OK)
                    .entity(mensaje)
                    .build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar el cliente").build();
        }
    }

}

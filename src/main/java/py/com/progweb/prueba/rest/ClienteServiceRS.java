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

    @POST
    @Path("/add")
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
    @Path("/update/{id}")
    public Response updateCliente(@PathParam("id") Long idCliente, Cliente clienteModificado) {
        try {
            Cliente clienteExistente = clienteService.getClienteById(idCliente);
            if (clienteExistente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Cliente con ID " + idCliente + " no encontrado").build();
            }

            // Actualizar solo los campos no nulos
            if (clienteModificado.getNombre() != null) {
                clienteExistente.setNombre(clienteModificado.getNombre());
            }
            if (clienteModificado.getApellido() != null) {
                clienteExistente.setApellido(clienteModificado.getApellido());
            }
            if (clienteModificado.getCedula() != null) {
                clienteExistente.setCedula(clienteModificado.getCedula());
            }
            if (clienteModificado.getEmail() != null) {
                clienteExistente.setEmail(clienteModificado.getEmail());
            }

            clienteService.updateCliente(clienteExistente);

            return Response.ok(clienteExistente).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el cliente")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteCliente(@PathParam("id") Long idCliente) {
        try {
            Cliente cliente = clienteService.getClienteById(idCliente);
            if (cliente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Cliente con ID " + idCliente + " no encontrado.")
                        .build();
            }

            clienteService.deleteCliente(cliente);
            String mensaje = "Cliente " + cliente.getNombre() + " " + cliente.getApellido()
                    + " eliminado correctamente.";
            return Response.status(Response.Status.OK)
                    .entity(mensaje)
                    .build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar el cliente")
                    .build();
        }
    }

}

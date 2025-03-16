package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.dto.VentaCabeceraDTO;
import py.com.progweb.prueba.model.VentaDetalle;

import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ejb.EJB;

@Path("/ventas")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class VentaServiceRS {

    @EJB
    private VentaService ventaService;
    @EJB
    private ClienteService clienteService;

    @POST
    @Path("/realizarVenta/{clienteid}")
    public Response realizarVenta(@PathParam("clienteid") Long clienteId, List<VentaDetalle> detalles) {
        try {
            Cliente cliente = clienteService.getClienteById(clienteId);
            if (cliente == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Cliente no encontrado").build();
            }

            ventaService.realizarVenta(cliente, detalles);
            return Response.ok("Venta registrada correctamente").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/")
    public Response listarVentas(@QueryParam("fecha") String fecha,
            @QueryParam("clienteId") Long clienteId) {
        List<VentaCabeceraDTO> ventas = ventaService.listarVentas(fecha, clienteId);
        if (ventas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron ventas registradas.")
                    .build();
        }

        return Response.ok(ventas).build();
    }
}

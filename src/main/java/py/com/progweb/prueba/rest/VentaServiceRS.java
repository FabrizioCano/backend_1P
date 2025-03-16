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

    @POST
    @Path("/realizarVenta/{clienteid}")
    public Response realizarVenta(@PathParam("clienteid") Long clienteId, List<VentaDetalle> detalles) {
        try {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(clienteId);

            ventaService.realizarVenta(cliente, detalles);
            return Response.ok("Venta registrada correctamente").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/all")
    public List<VentaCabeceraDTO> listarVentas(@QueryParam("fecha") String fecha,@QueryParam("clienteId") Long clienteId) {
        return ventaService.listarVentas(fecha, clienteId);
    }
}

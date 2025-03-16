package py.com.progweb.prueba.rest;

import java.util.List;
import py.com.progweb.prueba.model.Producto;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ejb.EJB;

@Path("/productos")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class ProductoServiceRS {   
    
    @EJB
    private ProductoService productoService;
    
    @GET
    public Response listarProductos(@QueryParam("nombre") String nombre, @QueryParam("idCategoria") Integer idCategoria) {
        List<Producto> productos = productoService.listarProductos(nombre, idCategoria);
        if (productos == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(productos).build();
    }
    
    @GET
    @Path("/{id}")
    public Response encontrarProductoPorId(@PathParam("id") Integer id) {
        Producto producto = productoService.encontrarProductoPorId(id);
        if (producto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(producto).build();
    }

    
    @POST
    public Response registrarProducto(Producto producto) {
        productoService.registrarProducto(producto);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/{id}")
    public Response modificarProducto(@PathParam("id") Integer id, Producto productoModificado) {
        try {
            Producto producto = productoService.encontrarProductoPorId(id);
            if (producto != null) {
                productoModificado.setIdProducto(id);
                productoService.modificarProducto(productoModificado);
                return Response.ok().entity(productoModificado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    public Response eliminarProductoPorId(@PathParam("id") Integer id) {
        try {
            productoService.eliminarProducto(new Producto(id));
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
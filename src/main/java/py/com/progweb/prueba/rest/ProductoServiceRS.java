package py.com.progweb.prueba.rest;

import java.util.List;
import py.com.progweb.prueba.model.Categoria;
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
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
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
    
    @GET
    @Path("/nombre")
    public List<Producto> encontrarProductoPorNombre(Producto producto) {
        return productoService.encontrarProductoPorNombre(producto);
    }
    
    @GET
    @Path("/categoria")
    public List<Producto> encontrarProductoPorCategoria(Categoria categoria) {
        return productoService.encontrarProductoPorCategoria(categoria);
    }
    
    @POST
    public Response registrarProducto(Producto producto) {
        productoService.registrarProducto(producto);
        return Response.ok().build();
    }
    
    @PUT
    public Response modificarProducto(Producto producto) {
        productoService.modificarProducto(producto);
        return Response.ok().build();
    }
    
    @DELETE
    public Response eliminarProducto(Producto producto) {
        productoService.eliminarProducto(producto);
        return Response.ok().build();
    }
}
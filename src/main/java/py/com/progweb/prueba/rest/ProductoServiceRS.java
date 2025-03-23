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

    @EJB
    CategoriaService categoriaService;

    @GET
    @Path("/all")
    public Response listarProductos(@QueryParam("nombre") String nombre,
            @QueryParam("idCategoria") Integer idCategoria) {
        List<Producto> productos = productoService.listarProductos(nombre, idCategoria);
        if (productos == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No existe una lista de productos con los parametros " + nombre + " y " + idCategoria)
                    .build();
        }
        return Response.ok(productos).build();
    }

    @GET
    @Path("/{id}")
    public Response encontrarProductoPorId(@PathParam("id") Integer id) {
        Producto producto = productoService.encontrarProductoPorId(id);
        if (producto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Producto con ID " + id + " no encontrado.")
                    .build();
        }
        return Response.ok(producto).build();
    }

    @POST
    @Path("/add")
    public Response registrarProducto(Producto producto) {
        try {
            if (producto.getCategoria() == null || producto.getCategoria().getIdCategoria() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("La categoría no puede ser nula").build();
            }

            // Buscar la categoría en la base de datos usando el ID
            Categoria categoria = categoriaService.encontrarCategoriaPorId(producto.getCategoria().getIdCategoria());
            if (categoria == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Categoría no encontrada").build();
            }
            // Asignar el objeto completo de la categoría al producto
            producto.setCategoria(categoria);

            // Registrar el producto en la base de datos
            productoService.registrarProducto(producto);

            return Response.ok().entity(producto).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear el producto").build();
        }
    }

    @PUT
    @Path("/update/{id}")
    public Response modificarProducto(@PathParam("id") Integer id, Producto productoModificado) {
        try {
            Producto producto = productoService.encontrarProductoPorId(id);
            if (producto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Producto con id " + id + " no encontrado")
                        .build();
            }

            // Actualizar solo los campos que no sean null en productoModificado
            if (productoModificado.getNombre() != null) {
                producto.setNombre(productoModificado.getNombre());
            }
            if (productoModificado.getPrecioVenta() != 0) {
                producto.setPrecioVenta(productoModificado.getPrecioVenta());
            }
            if (productoModificado.getCantidadExistente() != 0) {
                producto.setCantidadExistente(productoModificado.getCantidadExistente());
            }
            if (productoModificado.getCategoria() != null
                    && productoModificado.getCategoria().getIdCategoria() != null) {
                Categoria nuevaCategoria = categoriaService
                        .encontrarCategoriaPorId(productoModificado.getCategoria().getIdCategoria());
                if (nuevaCategoria == null) {
                    return Response.status(Response.Status.NOT_FOUND).entity("Categoría no encontrada").build();
                }
                producto.setCategoria(nuevaCategoria);
            }

            productoService.modificarProducto(producto);

            return Response.ok().entity(producto).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el producto")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response eliminarProductoPorId(@PathParam("id") Integer id) {
        try {
            Producto producto = productoService.encontrarProductoPorId(id);
            if (producto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Producto con ID " + id + " no encontrado.")
                        .build();
            }
            productoService.eliminarProducto(producto);
            String mensaje = "Producto " + producto.getNombre() + " eliminado.";
            return Response.ok().entity(mensaje).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar el producto")
                    .build();
        }
    }
}
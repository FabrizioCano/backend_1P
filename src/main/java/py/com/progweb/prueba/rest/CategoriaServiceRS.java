package py.com.progweb.prueba.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import py.com.progweb.prueba.model.Categoria;

@Path("/categorias")
@Consumes("application/json")
@Produces("application/json")
public class CategoriaServiceRS {
    @Inject
    private CategoriaService categoriaService;

    @GET
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarCategoriaPorId(@PathParam("id") int id) {
        Categoria categoria = categoriaService.encontrarCategoriaPorId(id);

        if (categoria == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Categoría con ID " + id + " no encontrada.")
                    .build();
        }

        return Response.ok(categoria).build();
    }

    @POST
    public Response agregarCategoria(Categoria categoria) {
        try {
            categoriaService.registrarCategoria(categoria);
            return Response.ok().entity(categoria).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response modificarCategoria(@PathParam("id") int id, Categoria categoriaModificada) {
        try {
            Categoria categoria = categoriaService.encontrarCategoriaPorId(id);
            if (categoria != null) {
                categoriaModificada.setIdCategoria(id);
                categoriaService.modificarCategoria(categoriaModificada);
                return Response.ok().entity(categoriaModificada).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Categoría con ID " + id + " no encontrada.")
                    .build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminarCategoriaPorId(@PathParam("id") int id) {
        try {
            categoriaService.eliminarCategoria(new Categoria(id));
            String mensaje="Categoría con ID " + id + " eliminada.";
            return Response.ok().entity(mensaje).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/consultaNombre")
    public List<Categoria> encontrarCategoriaPorNombre(@QueryParam("nombre") String nombre) {
        Categoria c = new Categoria();
        c.setNombre(nombre);
        return categoriaService.encontrarCategoriaPorNombre(c);
    }
}

package py.com.progweb.prueba.ejb;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.progweb.prueba.model.Categoria;
import py.com.progweb.prueba.model.Producto;

@Stateless
public class ProductoDaoImpl implements ProductoDao {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public List<Producto> findAllProductos() {
        return em.createNamedQuery("Producto.findAll", Producto.class).getResultList();
    }

    @Override
    public void insertProducto(Producto producto) {
        em.persist(producto);
    }

    @Override
    public void updateProducto(Producto producto) {
        em.merge(producto);
    }

    @Override
    public Producto findProductoById(Integer id) {
        return em.find(Producto.class, id); 
    }

    @Override
    public List<Producto> findProductoByNombre(String nombre) {
        List<Producto> productos = em.createNamedQuery("Producto.findByNombre", Producto.class)
                .setParameter("nombre", nombre)
                .getResultList();

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos con el nombre: " + nombre);
        }

        return productos;
    }

    @Override
    public List<Producto> findProductoByCategoria(Integer idCategoria) {
        List<Producto> productos = em.createNamedQuery("Producto.findByCategoria", Producto.class)
                .setParameter("idCategoria", idCategoria)
                .getResultList();

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos con la categoria: " + idCategoria);
        }

        return productos;
    }

    @Override
    public List<Producto> findProductoByNombreAndCategoria(String nombre, Integer idCategoria) {
        List<Producto> productos = em.createNamedQuery("Producto.findByNombreAndCategoria", Producto.class)
                .setParameter("nombre", nombre)
                .setParameter("idCategoria", idCategoria)
                .getResultList();

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos con el nombre: " + nombre + " y la categoria: " + idCategoria);
        }

        return productos;
    }

    @Override
    public void deleteProducto(Producto producto) {
        em.remove(em.merge(producto));
    }
}
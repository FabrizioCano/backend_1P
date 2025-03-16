package py.com.progweb.prueba.ejb;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public List<Producto> findProductoByNombre(Producto producto) {
        List<Producto> productos = em.createNamedQuery("Producto.findByNombre", Producto.class)
                .setParameter("nombre", producto.getNombre())
                .getResultList();
        
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos con el nombre: " + producto.getNombre());
        }

        return productos;
    }

    @Override
    public List<Producto> findProductoByCategoria(Categoria categoria) {
        List<Producto> productos = em.createNamedQuery("Producto.findByCategoria", Producto.class)
                .setParameter("idCategoria", categoria.getIdCategoria())
                .getResultList();

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos en la categoría: " + categoria.getNombre());
        }

        return productos;
    }

    @Override
    public void deleteProducto(Producto producto) {
        em.remove(em.merge(producto));
    }
}
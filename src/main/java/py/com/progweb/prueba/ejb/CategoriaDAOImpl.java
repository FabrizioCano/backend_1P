package py.com.progweb.prueba.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.progweb.prueba.model.Categoria;

@Stateless
public class CategoriaDAOImpl implements CategoriaDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public List<Categoria> findAllCategorias() {
        return em.createNamedQuery("Categoria.findAll", Categoria.class).getResultList();
    }

    @Override
    public void insertCategoria(Categoria categoria) {
        em.persist(categoria);
    }

    @Override
    public void updateCategoria(Categoria categoria) {
        em.merge(categoria);
    }

    @Override
    public Categoria findCategoriaById(Integer id) {
        Categoria categoria = em.find(Categoria.class, id);
        if (categoria == null) {
            System.out.println("No se encontró la categoría con ID: " + id);
        }
        return categoria;
    }

    @Override
    public List<Categoria> findCategoriaByNombre(Categoria categoria) {
        List<Categoria> categorias = em.createNamedQuery("Categoria.findByNombre", Categoria.class)
                .setParameter("nombre", categoria.getNombre())
                .getResultList();

        if (categorias.isEmpty()) {
            System.out.println("No se encontraron categorías con el nombre: " + categoria.getNombre());
        }

        return categorias;
    }

    @Override
    public void deleteCategoria(Categoria categoria) {
        em.remove(em.merge(categoria));
    }
}
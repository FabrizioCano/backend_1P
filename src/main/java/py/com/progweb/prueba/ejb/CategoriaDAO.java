package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Categoria;
import java.util.List;

public interface CategoriaDAO {

    public List<Categoria> findAllCategorias();

    public Categoria findCategoriaById(Integer id);

    public List<Categoria> findCategoriaByNombre(Categoria categoria);

    public void insertCategoria(Categoria categoria);

    public void updateCategoria(Categoria categoria);

    public void deleteCategoria(Categoria categoria);
}

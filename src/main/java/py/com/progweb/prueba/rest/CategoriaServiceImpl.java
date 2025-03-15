package py.com.progweb.prueba.rest;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.progweb.prueba.ejb.CategoriaDAO;
import py.com.progweb.prueba.model.Categoria;

@Stateless
public class CategoriaServiceImpl implements CategoriaService {
    @Inject
    private CategoriaDAO categoriaDAO;
    @Resource
    private SessionContext sessionContext;

    public List<Categoria> listarCategorias() {
        return categoriaDAO.findAllCategorias();
    }

    @Override
    public Categoria encontrarCategoriaPorId(Integer id) {
        return categoriaDAO.findCategoriaById(id);
    }

    public List<Categoria> encontrarCategoriaPorNombre(Categoria categoria) {
        return categoriaDAO.findCategoriaByNombre(categoria);
    }

    public void registrarCategoria(Categoria categoria) {
        categoriaDAO.insertCategoria(categoria);
    }

    public void modificarCategoria(Categoria categoria) {
        categoriaDAO.updateCategoria(categoria);
    }

    public void eliminarCategoria(Categoria categoria) {
        categoriaDAO.deleteCategoria(categoria);
    }

}

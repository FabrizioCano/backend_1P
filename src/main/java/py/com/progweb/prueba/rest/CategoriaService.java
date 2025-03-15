package py.com.progweb.prueba.rest;

import java.util.List;
import javax.ejb.Local;
import py.com.progweb.prueba.model.Categoria;

@Local
public interface CategoriaService {
    public List<Categoria> listarCategorias();
    public Categoria encontrarCategoriaPorId(Integer id);
    public List<Categoria> encontrarCategoriaPorNombre(Categoria categoria);
    public void registrarCategoria(Categoria categoria);
    public void modificarCategoria(Categoria categoria);
    public void eliminarCategoria(Categoria categoria);
}
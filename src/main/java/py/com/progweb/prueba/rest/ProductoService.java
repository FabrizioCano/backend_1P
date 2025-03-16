package py.com.progweb.prueba.rest;

import java.util.List;
import javax.ejb.Local;
import py.com.progweb.prueba.model.Producto;

@Local
public interface ProductoService {
    public List<Producto> listarProductos(String nombre, Integer idCategoria);
    public Producto encontrarProductoPorId(Integer id);
    // public List<Producto> encontrarProductoPorNombre(Producto producto);
    // public List<Producto> encontrarProductoPorCategoria(Categoria categoria);
    public void registrarProducto(Producto producto);
    public void modificarProducto(Producto producto);
    public void eliminarProducto(Producto producto);
}

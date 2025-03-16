package py.com.progweb.prueba.ejb;

import java.util.List;

import py.com.progweb.prueba.model.Categoria;
import py.com.progweb.prueba.model.Producto;

public interface ProductoDao {
    public Producto findProductoById(Integer id);
    public List<Producto> findAllProductos();
    public List<Producto> findProductoByNombre(Producto producto);
    public List<Producto> findProductoByCategoria(Categoria categoria);
    public void insertProducto(Producto producto);
    public void updateProducto(Producto producto);
    public void deleteProducto(Producto producto);
}

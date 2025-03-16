package py.com.progweb.prueba.rest;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.progweb.prueba.ejb.ProductoDao;
import py.com.progweb.prueba.model.Categoria;
import py.com.progweb.prueba.model.Producto;

@Stateless
public class ProductoServiceImpl implements ProductoService {

    @Inject
    private ProductoDao productoDao;

    @Resource
    private SessionContext sessionContext;

    @Override
    public List<Producto> listarProductos() {
        return productoDao.findAllProductos();
    }

    @Override
    public Producto encontrarProductoPorId(Integer id) {
        return productoDao.findProductoById(id);
    }

    @Override
    public List<Producto> encontrarProductoPorNombre(Producto producto) {
        return productoDao.findProductoByNombre(producto);
    }
    
    @Override
    public List<Producto> encontrarProductoPorCategoria(Categoria categoria) {
        return productoDao.findProductoByCategoria(categoria);
    }

    @Override
    public void registrarProducto(Producto producto) {
        productoDao.insertProducto(producto);
    }

    @Override
    public void modificarProducto(Producto producto) {
        productoDao.updateProducto(producto);
    }

    @Override
    public void eliminarProducto(Producto producto) {
        productoDao.deleteProducto(producto);
    }
}

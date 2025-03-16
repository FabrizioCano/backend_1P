package py.com.progweb.prueba.rest;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.progweb.prueba.ejb.ProductoDao;
import py.com.progweb.prueba.model.Producto;

@Stateless
public class ProductoServiceImpl implements ProductoService {

    @Inject
    private ProductoDao productoDao;

    @Resource
    private SessionContext sessionContext;

    @Override
    public List<Producto> listarProductos(String nombre, Integer idCategoria) {
        if (nombre != null && idCategoria != null) {
            return productoDao.findProductoByNombreAndCategoria(nombre, idCategoria);
        } else if (nombre != null) {
            return productoDao.findProductoByNombre(nombre);
        } else if (idCategoria != null) {
            return productoDao.findProductoByCategoria(idCategoria);
        } else {
            return productoDao.findAllProductos();
        }
    }

    @Override
    public Producto encontrarProductoPorId(Integer id) {
        return productoDao.findProductoById(id);
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

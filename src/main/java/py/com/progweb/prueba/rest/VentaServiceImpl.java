package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Producto;
import py.com.progweb.prueba.ejb.ProductoDao;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.ejb.VentasDAO;
import py.com.progweb.prueba.model.VentaCabecera;
import py.com.progweb.prueba.dto.VentaCabeceraDTO;

import py.com.progweb.prueba.model.VentaDetalle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ejb.EJB;
import java.util.List;

@Stateless
public class VentaServiceImpl implements VentaService {

    @EJB
    private VentasDAO ventasDAO;
    
    @EJB
    private ProductoDao productoDAO;

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager entityManager;

    @Override
public List<VentaCabeceraDTO> listarVentas(String fecha, Long clienteId) {
    String queryString = "SELECT new py.com.progweb.prueba.dto.VentaCabeceraDTO(v.idVenta, v.fecha, v.total, v.cliente.idCliente, v.cliente.nombre, v.cliente.apellido) " +
                         "FROM VentaCabecera v WHERE 1=1";
    
    if (fecha != null) {
        queryString += " AND FUNCTION('DATE', v.fecha) = :fecha";
    }
    if (clienteId != null) {
        queryString += " AND v.cliente.idCliente = :clienteId";
    }

    TypedQuery<VentaCabeceraDTO> query = entityManager.createQuery(queryString, VentaCabeceraDTO.class);
    
    if (fecha != null) {
        query.setParameter("fecha", java.sql.Date.valueOf(fecha));
    }
    if (clienteId != null) {
        query.setParameter("clienteId", clienteId);
    }

    return query.getResultList();
}




    @Override
    public void realizarVenta(Cliente cliente, List<VentaDetalle> detalles) throws Exception {
        double totalVenta = 0.0;
        
        // Validar stock de productos
        for (VentaDetalle detalle : detalles) {
            Producto producto = productoDAO.findProductoById(detalle.getProducto().getIdProducto());
            if (producto == null) {
                throw new Exception("Producto no encontrado: " + detalle.getProducto().getIdProducto());
            }
            if (producto.getCantidadExistente() < detalle.getCantidad()) {
                throw new Exception("No hay suficiente stock para el producto: " + producto.getNombre());
            }

            // Asignar el precio en la base de datos
            detalle.setProducto(producto);
            detalle.setPrecio(producto.getPrecioVenta());
            totalVenta += detalle.getSubtotal();


            producto.setCantidadExistente(producto.getCantidadExistente() - detalle.getCantidad());
        }

        // Registrar la venta
        VentaCabecera venta = new VentaCabecera();
        venta.setCliente(cliente);
        venta.setFecha(new java.util.Date());
        venta.setTotal(totalVenta); 

        ventasDAO.registrarVenta(venta);
        entityManager.flush();

        // Persistir los detalles asociados a la venta
        for (VentaDetalle detalle : detalles) {
            detalle.setVenta(venta);
            ventasDAO.registrarDetalle(detalle);
        }

        // Actualizar el total de la venta en la base de datos
        entityManager.merge(venta);

        // Actualizar inventario
        for (VentaDetalle detalle : detalles) {
            Producto producto = productoDAO.findProductoById(detalle.getProducto().getIdProducto());
            productoDAO.updateProducto(producto);
        }
    }
}

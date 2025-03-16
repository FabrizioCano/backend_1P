package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VentaCabecera;
import py.com.progweb.prueba.model.VentaDetalle;
import java.util.List;

public interface VentasDAO {

    public void registrarVenta(VentaCabecera venta);
    public List<VentaCabecera> listarVentas();
    public VentaCabecera findVentaPorId(Long id);
    public void registrarDetalle(VentaDetalle detalle);
}
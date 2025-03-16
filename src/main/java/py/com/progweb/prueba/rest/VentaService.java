package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.VentaDetalle;
import py.com.progweb.prueba.dto.VentaCabeceraDTO;

import java.util.List;

public interface VentaService {
    public List<VentaCabeceraDTO> listarVentas(String fecha, Long clienteId);
    public void realizarVenta(Cliente cliente, List<VentaDetalle> detalles) throws Exception;
}

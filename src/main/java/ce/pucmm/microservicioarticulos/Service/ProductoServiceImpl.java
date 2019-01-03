package ce.pucmm.microservicioarticulos.Service;


import ce.pucmm.microservicioarticulos.Model.Producto;
import ce.pucmm.microservicioarticulos.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("productoService")
@Transactional
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public void crearProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void actualizarProducto(Producto producto) {
        crearProducto(producto);
    }

    public void borrarProductoPorId(Producto producto) {
        producto.setDeleted(true);
        actualizarProducto(producto);
    }

    public void borrarTodosLosProductos() {
        productoRepository.deleteAll();
    }

    public List<Producto> buscarTodosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarPorId(long id) {
        return productoRepository.findById(id).get();
    }

    public Producto findByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    public boolean productoExiste(Producto producto) {
        return productoRepository.findByNombre(producto.getNombre()) != null;
    }
}

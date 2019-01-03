package ce.pucmm.microservicioarticulos.Service;


import ce.pucmm.microservicioarticulos.Model.Producto;

import java.util.List;

public interface ProductoService {

    void crearProducto(Producto producto);

    void actualizarProducto(Producto producto);

    void borrarProductoPorId(Producto producto);

    void borrarTodosLosProductos();

    List<Producto> buscarTodosProductos();

    Producto buscarPorId(long id);

    Producto findByNombre(String nombre);

    boolean productoExiste(Producto producto);
}

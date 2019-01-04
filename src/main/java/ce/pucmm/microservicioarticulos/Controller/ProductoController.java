package ce.pucmm.microservicioarticulos.Controller;


import ce.pucmm.microservicioarticulos.Model.Producto;
import ce.pucmm.microservicioarticulos.Service.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/productos")
public class ProductoController {

    private static String UPLOADED_FOLDER =   System.getProperty("user.home")+"/Desktop/";


    @Autowired
    private ProductoServiceImpl productoService;


    @RequestMapping("/todos")
    public List<Producto> verTodosLosProductos() {

        return productoService.buscarTodosProductos();
    }


    @PostMapping("/crear/")
    public void crearProducto(@RequestParam("foto") MultipartFile foto, @RequestParam("nombre") String nombre, @RequestParam("precio") String precio,
                                @RequestParam("existencia") String existencia,
                                HttpServletResponse httpResponse) throws IOException  {


        Producto producto = new Producto();

        try {

            // Get the file and save it somewhere
            byte[] bytes = foto.getBytes();
            producto.setImagen(bytes);
            Path path = Paths.get(UPLOADED_FOLDER + foto.getOriginalFilename());
            Files.write(path, bytes);



        } catch (IOException e) {
            e.printStackTrace();
        }

        producto.setNombre(nombre);
        producto.setPrecio(Float.parseFloat(precio));
        producto.setStock(Integer.parseInt(existencia));
        productoService.crearProducto(producto);


        httpResponse.sendRedirect("http://localhost:8080/productos/");
    }



    @PostMapping("/modificar/")
    public void modificarProducto(@RequestParam("nombre2") String nombre, @RequestParam("id2") String id, @RequestParam("precio2") String precio,
                                    @RequestParam("existencia2") String existencia,
                                    @RequestParam("foto2") MultipartFile foto, HttpServletResponse httpResponse) throws IOException  {

        Producto producto = productoService.buscarPorId(Long.parseLong(id));
        producto.setNombre(nombre);
        producto.setPrecio(Float.parseFloat(precio));
        producto.setStock(Integer.parseInt(existencia));
        try {

            // Get the file and save it somewhere
            byte[] bytes = foto.getBytes();
            producto.setImagen(bytes);
            Path path = Paths.get(UPLOADED_FOLDER + foto.getOriginalFilename());
            Files.write(path, bytes);


        } catch (IOException e) {
            e.printStackTrace();
        }
        productoService.actualizarProducto(producto);


        httpResponse.sendRedirect("http://localhost:8080/productos/");
    }



    @PostMapping(value = "/eliminar/{id}")
    public void eliminarProducto(@PathVariable String id, HttpServletResponse httpResponse) throws IOException   {
        productoService.borrarProductoPorId(productoService.buscarPorId(Integer.parseInt(id)));

        httpResponse.sendRedirect("http://localhost:8080/productos/");



    }


}

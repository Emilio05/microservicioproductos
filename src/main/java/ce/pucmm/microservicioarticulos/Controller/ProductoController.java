package ce.pucmm.microservicioarticulos.Controller;


import ce.pucmm.microservicioarticulos.Model.Producto;
import ce.pucmm.microservicioarticulos.Service.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private static String UPLOADED_FOLDER = "C://Users//EmilioFerreiras//Desktop//";


    @Autowired
    private ProductoServiceImpl productoService;


    @RequestMapping("/todos")
    public List<Producto> verTodosLosProductos() {

        Producto producto = new Producto("jabon", 10, 100, null);
        productoService.crearProducto(producto);
        return productoService.buscarTodosProductos();
    }


    @PostMapping("/")
    public String crearProducto(@RequestParam("foto") MultipartFile foto, @RequestParam("nombre") String nombre, @RequestParam("precio") String precio,
                                @RequestParam("existencia") String existencia,
                                RedirectAttributes redirectAttributes) {


        Producto producto = new Producto();

        try {

            // Get the file and save it somewhere
            byte[] bytes = foto.getBytes();
            producto.setImagen(bytes);
            Path path = Paths.get(UPLOADED_FOLDER + foto.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + foto.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        producto.setNombre(nombre);
        producto.setPrecio(Float.parseFloat(precio));
        producto.setStock(Integer.parseInt(existencia));
        productoService.crearProducto(producto);
        return "redirect:/productos/";
    }


    @RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
    public String ver(Model model, @PathVariable String id) {
        Producto producto = productoService.buscarPorId(Long.parseLong(id));

        model.addAttribute("producto", producto);
        return "verproducto";
    }

    @PostMapping("/modificar/")
    public String modificarProducto(@RequestParam("nombre2") String nombre, @RequestParam("id2") String id, @RequestParam("precio2") String precio,
                                    @RequestParam("existencia2") String existencia,
                                    @RequestParam("foto2") MultipartFile foto, RedirectAttributes redirectAttributes) {

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

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + foto.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        productoService.actualizarProducto(producto);
        return "redirect:/productos/";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String borrarProducto(@PathVariable String id) {
        Producto producto = productoService.buscarPorId(Long.parseLong(id));
        productoService.borrarProductoPorId(producto);
        return "redirect:/productos/";

    }

}

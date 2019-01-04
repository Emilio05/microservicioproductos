package ce.pucmm.microservicioarticulos.Model;




import org.hibernate.annotations.Loader;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Base64;
import javax.persistence.*;

@Entity(name = "Producto")
@Table(name = "producto")
@Where(clause = "deleted = 0")
public class Producto implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "stock")
    private int stock;

    @Column(name = "precio")
    private double precio;

    @Loader
    @Column(name = "imagen", columnDefinition = "BLOB")
    private byte[] imagen;


    private boolean deleted = false;


    public String getImagen() {
        return Base64.getEncoder().encodeToString(imagen);

    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


    public Producto(){

    }

    public Producto(String nombre, int stock, double precio, byte[] imagen) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

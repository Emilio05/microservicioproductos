package ce.pucmm.microservicioarticulos.Repository;

import ce.pucmm.microservicioarticulos.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto findByNombre(String nombre);

}

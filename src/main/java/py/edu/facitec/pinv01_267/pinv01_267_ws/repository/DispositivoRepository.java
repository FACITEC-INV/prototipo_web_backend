package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {

    Optional<Dispositivo> findFirstByUbicacion(String ubicacion);

    /**
     * Buscar los dispositivos por un término de búsqueda.
     * Compara el termino buscado con el atributo río.
     * 
     * @param term
     * @return {DispositivoAdminDto} Lista de dispositivos.
     */
    List<Dispositivo> findByRioContainingIgnoreCase(String term);
}

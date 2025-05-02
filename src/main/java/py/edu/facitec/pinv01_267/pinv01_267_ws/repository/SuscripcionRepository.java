package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Suscripcion;

@RepositoryRestResource(path = "suscripciones")
public interface SuscripcionRepository extends CrudRepository<Suscripcion, Long> {

}

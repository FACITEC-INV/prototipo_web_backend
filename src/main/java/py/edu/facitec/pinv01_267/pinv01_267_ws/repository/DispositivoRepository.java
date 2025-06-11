package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Dispositivo;

@RepositoryRestResource(path = "dispositivos", collectionResourceRel = "dispositivos")
public interface DispositivoRepository extends CrudRepository<Dispositivo, UUID> {

}

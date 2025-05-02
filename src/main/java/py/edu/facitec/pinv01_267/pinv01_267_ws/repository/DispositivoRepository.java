package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.Dispositivo;

public interface DispositivoRepository extends CrudRepository<Dispositivo, UUID> {

}

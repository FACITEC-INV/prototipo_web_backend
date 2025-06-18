package py.edu.facitec.pinv01_267.pinv01_267_ws.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}

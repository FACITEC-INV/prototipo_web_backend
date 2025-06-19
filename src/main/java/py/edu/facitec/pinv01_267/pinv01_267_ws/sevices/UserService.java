package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.UserDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.exception.EntityNotFoundException;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.User;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.UserRepository;

@Service
public class UserService extends BaseService<User, UserDto>{
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService() {
        super(User.class, UserDto.class);
    }

    public UserDto save(UserDto userDto) {
        User user = convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return convertToDto(
            userRepository.save(user)
        );
    }

    public List<UserDto> findAll() {
        return userRepository
            .findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: "+username));
        return convertToDto(user);
    }

}

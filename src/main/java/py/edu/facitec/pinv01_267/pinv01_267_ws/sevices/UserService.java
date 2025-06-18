package py.edu.facitec.pinv01_267.pinv01_267_ws.sevices;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import py.edu.facitec.pinv01_267.pinv01_267_ws.dto.UserDto;
import py.edu.facitec.pinv01_267.pinv01_267_ws.exception.EntityNotFoundException;
import py.edu.facitec.pinv01_267.pinv01_267_ws.model.User;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

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

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}

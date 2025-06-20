package py.edu.facitec.pinv01_267.pinv01_267_ws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import py.edu.facitec.pinv01_267.pinv01_267_ws.model.User;
import py.edu.facitec.pinv01_267.pinv01_267_ws.repository.UserRepository;

@Configuration
public class SuperUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.root.username}")
    private String rootUsername;

    @Value("${app.root.password}")
    private String rootPassword;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername(rootUsername).isEmpty()) {
            User rootUser = new User();
            rootUser.setUsername(rootUsername);
            rootUser.setPassword(passwordEncoder.encode(rootPassword));
            rootUser.setFullName("Super User");
            rootUser.setRole("ROOT");

            userRepository.save(rootUser);
            System.out.println("✅ ROOT user created from application.yml");
        } else {
            System.out.println("ℹ️ ROOT user already exists.");
        }
    }
}


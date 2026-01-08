package py.edu.facitec.pinv01_267.pinv01_267_ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Pinv01267WsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Pinv01267WsApplication.class, args);
		System.out.println(">>>> RUN");
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Pinv01267WsApplication.class);
    }

}

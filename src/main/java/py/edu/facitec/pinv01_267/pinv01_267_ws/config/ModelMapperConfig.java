package py.edu.facitec.pinv01_267.pinv01_267_ws.config;

import org.modelmapper.ModelMapper;

import java.util.UUID;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        // UUID -> String
        Converter<UUID, String> uuidToString = new Converter<>() {
            public String convert(MappingContext<UUID, String> context) {
                return context.getSource() == null ? null : context.getSource().toString();
            }
        };

        // String -> UUID
        Converter<String, UUID> stringToUuid = new Converter<>() {
            public UUID convert(MappingContext<String, UUID> context) {
                return context.getSource() == null ? null : UUID.fromString(context.getSource());
            }
        };

        // Register converters
        modelMapper.addConverter(uuidToString);
        modelMapper.addConverter(stringToUuid);

        return modelMapper;
    }
}

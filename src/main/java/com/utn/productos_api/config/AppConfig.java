package com.utn.productos_api.config;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configuración para que el mapeo sea más estricto
        // y solo mapee propiedades que coincidan exactamente.
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}

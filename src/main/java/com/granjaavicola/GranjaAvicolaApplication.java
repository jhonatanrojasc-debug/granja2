package com.granjaavicola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class GranjaAvicolaApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(GranjaAvicolaApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GranjaAvicolaApplication.class);
    }

    public static void main(String[] args) {
        log.info("Iniciando Sistema de Granja Avicola");
        SpringApplication.run(GranjaAvicolaApplication.class, args);
    }
}
package pe.edu.I202222350.cl1_jpa_data_sihuinta_andy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Configuration
public class TomcatJndiConfig {
     @Value("${DB_WORLD_URL}")
    private String dbWorld;
    @Value("${DB_WORLD_USER}")
    private String dbWorldUser;
    @Value("${DB_WORLD_PASS}")
    private String dbWorldPass;
    @Value("${DB_WORLD_DRIVER}")
    private String dbWorldDriver;


    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbWorld);
        config.setUsername(dbWorldUser);
        config.setPassword(dbWorldPass);
        config.setDriverClassName(dbWorldDriver);

        // configuracion de HikariCP
        config.setMaximumPoolSize(240); // numero maximo de conexiones en el pool
        config.setMinimumIdle(4); // numero minimo de conexiones que deben mantenerse inactivas en el pool
        config.setIdleTimeout(240000); // 4 minutos, tiempo en milisegundos despues del cual una conexion inactiva puede ser eliminada
        config.setConnectionTimeout(45000); // 45 segundos, tiempo en milisegundos que el pool espera para obtener una conexion antes de lanzar una excepcion

        return new HikariDataSource(config);


    }
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatFactoryCustomizer(HikariDataSource hikariDataSource) {
        return factory -> factory.addContextCustomizers(context -> {
            try {
                Context initCtx = new InitialContext();
                initCtx.bind("java:comp/env/jdbc/Myworld", hikariDataSource);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        });
    }




}

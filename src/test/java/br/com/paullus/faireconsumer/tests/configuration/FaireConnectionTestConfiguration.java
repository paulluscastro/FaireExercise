/**
 * 
 */
package br.com.paullus.faireconsumer.tests.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import br.com.paullus.faireconsumer.connection.FaireConnection;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Profile("test")
@Configuration
public class FaireConnectionTestConfiguration {
    @Bean
    @Primary
    public FaireConnection faireConnection() {
        return Mockito.mock(FaireConnection.class);
    }
}

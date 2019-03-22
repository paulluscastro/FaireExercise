/**
 * 
 */
package br.com.paullus.faireconsumer.connection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public interface IFaireConnection {
	RestTemplate getRestTemplate();
    HttpEntity<String> getHttpEntity();
    HttpHeaders getHeaders();
}

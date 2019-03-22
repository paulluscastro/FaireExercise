/**
 * 
 */
package br.com.paullus.faireconsumer.connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Service
public class FaireConnection implements IFaireConnection {
	@Value("${key}")
	private String key;
    private static final Logger logger = LoggerFactory.getLogger(FaireConnection.class);

    private static ObjectMapper getObjectMapper()
    {
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    	return objectMapper;
    }
	public RestTemplate getRestTemplate() {
    	RestTemplate restTemplate = new RestTemplate();
    	List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    	MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
    	jsonMessageConverter.setObjectMapper(getObjectMapper());
    	messageConverters.add(jsonMessageConverter);
    	restTemplate.setMessageConverters(messageConverters);
    	return restTemplate;
    }
    public HttpEntity<String> getHttpEntity(){
    	if (key == null || key.trim().equals(""))
    		logger.warn("X-FAIRE-ACCESS-TOKEN was not provided");
    	else
    		logger.info("X-FAIRE-ACCESS-TOKEN key '" + key + "'");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-FAIRE-ACCESS-TOKEN", key);
        return new HttpEntity<String>("parameters", headers);
    }
}

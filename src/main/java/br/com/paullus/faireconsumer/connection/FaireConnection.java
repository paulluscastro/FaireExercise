/**
 * 
 */
package br.com.paullus.faireconsumer.connection;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
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
	@Value("${key:}")
	private String key;
	@Value("${useProxy:}")
    private String useProxy;
	@Value("${proxy.server:}")
    private String proxyServer;
	@Value("${proxy.port:}")
    private String proxyPort;
	@Value("${proxy.user:}")
    private String proxyUser;
	@Value("${proxy.password:-1}")
    private String proxyPassword;
    private static final Logger logger = LoggerFactory.getLogger(FaireConnection.class);
    
    private ClientHttpRequestFactory createWithoutPassword(int port) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        InetSocketAddress address = new InetSocketAddress(proxyServer, port);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
        factory.setProxy(proxy);
        return factory;
    }
    private ClientHttpRequestFactory createWithPassword(int port) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials( 
                new AuthScope(proxyServer, port),
                new UsernamePasswordCredentials(proxyUser, proxyPassword));
        final HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.useSystemProperties();
        clientBuilder.setProxy(new HttpHost(proxyServer, port));
        clientBuilder.setDefaultCredentialsProvider(credsProvider);
        clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
        final CloseableHttpClient client = clientBuilder.build();

        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(client);
        return factory;
    }
    private ClientHttpRequestFactory createProxyRequestFactory() {
    	int port = -1;
        try {
        	port = Integer.parseInt(proxyPort);
        } catch (NumberFormatException e) {
            logger.error("Unable to parse the proxy port number");
        }
        if (proxyPassword == null || proxyPassword.equals(""))
        	return createWithoutPassword(port);
        else
        	return createWithPassword(port);
    }
    private boolean getUseProxy() {
    	return useProxy.equalsIgnoreCase("true"); 
    }
    private static ObjectMapper getObjectMapper()
    {
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    	return objectMapper;
    }
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = getUseProxy() ? new RestTemplate(createProxyRequestFactory()) : new RestTemplate();
    	List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    	MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
    	jsonMessageConverter.setObjectMapper(getObjectMapper());
    	messageConverters.add(jsonMessageConverter);
    	restTemplate.setMessageConverters(messageConverters);
    	return restTemplate;
    }
    public HttpHeaders getHeaders(){
    	if (key == null || key.trim().equals(""))
    		logger.warn("X-FAIRE-ACCESS-TOKEN was not provided");
    	else
    		logger.info("X-FAIRE-ACCESS-TOKEN key '" + key + "'");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-FAIRE-ACCESS-TOKEN", key);
        return headers;
    }
    public HttpEntity<String> getHttpEntity(){
        return new HttpEntity<String>("parameters", getHeaders());
    }
}

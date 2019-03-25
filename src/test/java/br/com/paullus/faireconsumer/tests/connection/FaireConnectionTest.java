/**
 * 
 */
package br.com.paullus.faireconsumer.tests.connection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import br.com.paullus.faireconsumer.application.FaireConsumerConfiguration;
import br.com.paullus.faireconsumer.connection.FaireConnection;
import br.com.paullus.faireconsumer.connection.IFaireConnection;
/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FaireConsumerConfiguration.class})
public class FaireConnectionTest {

    // Simulating that the argument is being passed 
	@BeforeClass
	public static void setKeyArgument() {
		System.setProperty("key", "Teste");
	}
	
	@Autowired
	private IFaireConnection connection;

    @Test
    public void test_connection_is_correct_implementation() {
        //assert correct type
        assertThat(connection, instanceOf(FaireConnection.class));
    }

    @Test
    public void test_connection_headers_gets_token_from_argument() {
        //assert that has the token header
        assertTrue(connection.getHeaders().containsKey("X-FAIRE-ACCESS-TOKEN"));
        //assert that the token header is not null
        assertNotNull(connection.getHeaders().get("X-FAIRE-ACCESS-TOKEN"));
        //assert that the token header is equals to the value we setted at BeforeClass
        assertEquals(connection.getHeaders().get("X-FAIRE-ACCESS-TOKEN").size(), 1);
        assertEquals(connection.getHeaders().get("X-FAIRE-ACCESS-TOKEN").get(0), "Teste");
    }

    @Test
    public void test_connection_creates_rest_template() {
        //assert correct type
        assertThat(connection.getRestTemplate(), instanceOf(RestTemplate.class));
    }
}

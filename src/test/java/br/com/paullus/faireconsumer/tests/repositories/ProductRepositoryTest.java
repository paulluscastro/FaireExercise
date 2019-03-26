/**
 * 
 */
package br.com.paullus.faireconsumer.tests.repositories;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.github.javafaker.Faker;

import br.com.paullus.faireconsumer.connection.IFaireConnection;
import br.com.paullus.faireconsumer.dtos.ProductOptionOutputDTO;
import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;
import br.com.paullus.faireconsumer.repositories.IProductRepository;
import br.com.paullus.faireconsumer.repositories.ProductRepository;
import br.com.paullus.faireconsumer.tests.faker.FakeDtoFactory;

import static org.mockito.Mockito.when;

import java.util.UUID;

/**
 * @author paullus
 *
 */
public class ProductRepositoryTest {
	@Mock
	private IFaireConnection connection;

    @InjectMocks
    private IProductRepository productRepository = new ProductRepository();

    @Test
    public void when_list_is_called_should_return_mocked_list() throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
    	FakeDtoFactory<ProductOutputDTO> productFaker = new FakeDtoFactory<ProductOutputDTO>()
    			.addRule("id", () -> "po_" + UUID.randomUUID().toString())
    			.addRule("product_id", () -> "p_")
    			.addRule("active", () -> 1)
    			.addRule("name", () -> 1)
    			.addRule("sku", () -> 1)
    			.addRule("available_quantity", () -> 1)
    			.addRule("backordered_until", () -> 1)
    			.addRule("created_at", () -> 1)
    			.addRule("updated_at", () -> 1);
    	when(productRepository.list())
    		.thenReturn(productFaker.generate(3));
    }
}

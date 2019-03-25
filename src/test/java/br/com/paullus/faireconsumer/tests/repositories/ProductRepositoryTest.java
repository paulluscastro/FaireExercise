/**
 * 
 */
package br.com.paullus.faireconsumer.tests.repositories;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.paullus.faireconsumer.connection.IFaireConnection;
import br.com.paullus.faireconsumer.repositories.IProductRepository;
import br.com.paullus.faireconsumer.repositories.ProductRepository;

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
    public void when_list_is_called_should_return_mocked_list() {
    }
}

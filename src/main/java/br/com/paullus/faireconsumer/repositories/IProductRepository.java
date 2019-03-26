package br.com.paullus.faireconsumer.repositories;
import java.util.List;

import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;

/**
 * 
 */

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public interface IProductRepository {
	List<ProductOutputDTO> list();
}

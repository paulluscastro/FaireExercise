/**
 * 
 */
package br.com.paullus.faireconsumer.application;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.paullus.faireconsumer.dtos.BestBuyerOutputDTO;
import br.com.paullus.faireconsumer.dtos.BestSellerOutputDTO;
import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;
import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;
import br.com.paullus.faireconsumer.dtos.WorstSellerOutputDTO;
import br.com.paullus.faireconsumer.services.IOrderService;
import br.com.paullus.faireconsumer.services.IProductService;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@SpringBootApplication
public class FaireConsumerApplication implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(FaireConsumerApplication.class);
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderService orderService;
    
	public static void main(String args[]) {
		SpringApplication.run(FaireConsumerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String teaDropsId = "b_d2481b88";
		List<ProductOutputDTO> products = productService.listByBrand(teaDropsId);
		logger.info("***************************** LISTING ALL PRODUCTS *****************************");
		products.stream().forEach(p -> logger.info(p.toString()));
		// logger.info("***************************** LISTING ALL ORDERS *****************************");
		List<OrderOutputDTO> orders =  orderService.list();
		// orders.stream().forEach(o -> logger.info(o.toString()));
		logger.info("Processing orders... ");
		orders.stream().forEach(o -> orderService.process(o));
		logger.info("******************************************************************************");
		BestSellerOutputDTO bestSeller = orderService.getBestSeller(); 
		logger.info("Best seller Product Option: '" + bestSeller.getProduct().getName() + " - " + bestSeller.getOption().getName() + "' [" + DecimalFormat.getInstance(Locale.US).format(bestSeller.getAmmount()) + " items sold]");
		OrderOutputDTO  largest = orderService.getLargestOrder();
		logger.info("'" + largest.getAddress().getCompany_name() + "' made the largest order (by total dolars): " + DecimalFormat.getCurrencyInstance(Locale.US).format(largest.getTotalOrder()));
		BestSellerOutputDTO topOutOfStock = orderService.getMostRequiredOutOfStock(); 
		BestBuyerOutputDTO topBuyer = orderService.getTopBuyer(); 
		logger.info("Best buyers are from " + topBuyer.getState().toUpperCase() + ": " + topBuyer.getAmmount() + " orders in total");
		logger.info("Buyers want but we don't have '" + topOutOfStock.getProduct().getName() + " - " + topOutOfStock.getOption().getName() + "': " + DecimalFormat.getInstance(Locale.US).format(topOutOfStock.getAmmount()) + " units ordered out of stock!!!");
		WorstSellerOutputDTO worstSeller = orderService.getNotSoldWithHighestStock();
		if (worstSeller.getOption() == null)
			logger.info("Great news!!! All options were ordered at least once. People want our products.");
		else
			logger.info("Nobody ordered '" + worstSeller.getProduct().getName() + " - " + worstSeller.getOption().getName() + "' and we have " + DecimalFormat.getInstance(Locale.US).format(worstSeller.getAmmount()) + " units of it in stock!!!" );
	}
}

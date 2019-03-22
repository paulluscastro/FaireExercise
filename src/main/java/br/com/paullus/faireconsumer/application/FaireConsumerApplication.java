/**
 * 
 */
package br.com.paullus.faireconsumer.application;

import java.math.BigDecimal;
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
import org.springframework.context.annotation.ComponentScan;

import br.com.paullus.faireconsumer.dtos.BestBuyerOutputDTO;
import br.com.paullus.faireconsumer.dtos.BestSellerOutputDTO;
import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;
import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;
import br.com.paullus.faireconsumer.services.IOrderService;
import br.com.paullus.faireconsumer.services.IProductService;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@ComponentScan("br.com.paullus.faireconsumer")
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
		logger.info("***************************** LISTING ALL ORDERS *****************************");
		List<OrderOutputDTO> orders =  orderService.list();
		orders.stream().forEach(o -> logger.info(o.toString()));
		logger.info("***************************** PROCESSING  ORDERS *****************************");
		orders.stream().forEach(o -> orderService.process(o));
		logger.info("******************************************************************************");
		BestSellerOutputDTO bestSeller = orderService.getBestSeller(); 
		logger.info("Best seller Product Option: " + bestSeller.getOption().getName() + " [" + DecimalFormat.getInstance(Locale.US).format(bestSeller.getAmmount()) + " items sold]");
		OrderOutputDTO  largest = orderService.getLargestOrder(); 
		logger.info("Largest order (by total dolars): " + largest.getId() + " [" + DecimalFormat.getCurrencyInstance(Locale.US).format(largest.getTotalOrder()) + "]");
		BestBuyerOutputDTO topBuyer = orderService.getTopBuyer(); 
		logger.info("Best buyers are from " + topBuyer.getState() + ": " + topBuyer.getAmmount() + " orders in total");
	}
}

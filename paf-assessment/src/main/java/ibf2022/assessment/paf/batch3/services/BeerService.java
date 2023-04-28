package ibf2022.assessment.paf.batch3.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.assessment.paf.batch3.models.Order;
import ibf2022.assessment.paf.batch3.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	private OrderRepository orderRepo;

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(Order o) {
		// TODO: Task 5 
		o.setOrderId(generateOrderId());
		orderRepo.insertOrder(o);	
		
		return o.getOrderId();
	}

	private String generateOrderId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().substring(0,8);
	}
}
